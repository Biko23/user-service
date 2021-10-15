package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.*;
import com.flyhub.saccox.userservice.model.ApiResponseModel;
import com.flyhub.saccox.userservice.repository.*;
import com.flyhub.saccox.userservice.visualobject.VisualObject;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Service
@Slf4j
public class SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private UserLoginProcedureRepository userLoginProcedureRepository;

    @Autowired
    private SystemUserFunctionalGroupsProcedureRepository systemUserFunctionalGroupsProcedureRepository;

    @Autowired
    private SystemUserFunctionalGroupMappingService systemUserFunctionalGroupMappingService;

    @Autowired
    private FunctionalGroupService functionalGroupService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public Map<String, String> handleValidationExceptions(Errors errors) {
        Map<String, String> errorsMessages = new HashMap<>();
        errors.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorsMessages.put(fieldName, errorMessage);
        });
        return errorsMessages;
    }

    public ApiResponseModel systemUserSignup(MultipartFile file,
                                         String first_name,
                                         String middle_name,
                                         String last_name,
                                         String primary_phone,
                                         String primary_email,
                                         String password,
                                         String question,
                                         String answer) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Inside systemUserSignup method of SystemUserService");

        SystemUserEntity systemUserEntity = new SystemUserEntity();
        if (file != null) {
            String fileType = file.getContentType();
            System.out.println("fileType");
            System.out.println(fileType);
            if (fileType.equals("image/png") || fileType.equals("image/jpg") || fileType.equals("image/jpeg")) {
                long fileSize = file.getSize();
                System.out.println(fileSize);
                fileSize = fileSize/1024;
                System.out.println(fileSize);
                if (fileSize < 1024) {
                    systemUserEntity.setImageSmall(file.getBytes());
                    System.out.println(systemUserEntity.getImageSmall());
                }else {
                    System.out.println("Sorry file of size " + fileSize/1024 + " Mbs is too big!");
                    throw new CustomNotAuthorisedException("Sorry file of size " + fileSize/1024 + " Mbs is too big. Please upload an image of less than 1MB");
                }
            }else {
                System.out.println("Sorry this file type is not accepted.");
                throw new CustomNotAuthorisedException("Sorry this file type is not accepted. Please upload an image of 'png', 'jpg' or 'jpeg'");
            }
        }
        systemUserEntity.setFirstName(first_name);
        systemUserEntity.setMiddleName(middle_name);
        systemUserEntity.setLastName(last_name);
        systemUserEntity.setFullName(systemUserEntity.getFirstName() + " " + systemUserEntity.getMiddleName() + " " + systemUserEntity.getLastName());
        systemUserEntity.setPrimaryEmail(primary_email);
        systemUserEntity.setPrimaryPhone(primary_phone);
        systemUserEntity.setPassword(password);
        systemUserEntity.setQuestion(question);
        systemUserEntity.setAnswer(answer);
        // get a salt value using the SecureRandom class
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(12);
        systemUserEntity.setSaltValue(salt);
        // hash the password with the salt
        PBEKeySpec pbeKeySpec = new PBEKeySpec(systemUserEntity.getPassword().toCharArray(), salt, 10, 512);
        SecretKeyFactory secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = secretKey.generateSecret(pbeKeySpec).getEncoded();
        //converting to string to store into database
        String hashedPassword = Base64.getMimeEncoder().encodeToString(hash);
        systemUserEntity.setPassword(hashedPassword);
        SystemUserEntity systemUser = systemUserRepository.save(systemUserEntity);
        //get the admin functional group
        FunctionalGroupEntity adminFunctionalGroup = functionalGroupService.findInternalAdminFunctionalGroup();
        SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMapping = new SystemUserFunctionalGroupMappingEntity();
        // assign internal admin group to system user
        systemUserFunctionalGroupMapping.setFunctionalGroupGlobalId(adminFunctionalGroup.getFunctionalGroupGlobalId());
        systemUserFunctionalGroupMapping.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
        systemUserFunctionalGroupMappingService.saveSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMapping);

        ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, VisualObject.class);
//        ResponseEntity<VisualObject> systemUserFunctionalGroupModuleMappingResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, VisualObject.class);
        SystemUserEntity tokenObject = new SystemUserEntity();
        tokenObject.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
        tokenObject.setTenantGlobalId(systemUser.getTenantGlobalId());
        tokenObject.setTenantName(systemUser.getTenantName());
        tokenObject.setBranchGlobalId(systemUser.getBranchGlobalId());
        tokenObject.setRefreshToken(systemUser.getRefreshToken());
        ApiResponseModel tokenResponse = restTemplate.postForObject("http://localhost:9100/api/v1/auth/tokens", tokenObject, ApiResponseModel.class);
        return tokenResponse;
    }

    @Transactional
    public VisualObject saveSystemUser(SystemUserEntity systemUserEntity) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Inside saveSystemUser method of SystemUserService");
        if (systemUserEntity.getMemberGlobalId() != null) {
        SystemUserEntity userAsMember = systemUserRepository.findByMemberGlobalId(systemUserEntity.getMemberGlobalId());

            // update the is_active and is_staff
            // use to update member in the system user table given the member id
            updateSystemUser(userAsMember.getSystemUserGlobalId(), userAsMember);
            SystemUserEntity tokenObject = new SystemUserEntity();

            tokenObject.setSystemUserGlobalId(userAsMember.getSystemUserGlobalId());
            tokenObject.setTenantGlobalId(userAsMember.getTenantGlobalId());
            tokenObject.setTenantName(userAsMember.getTenantName());
            tokenObject.setBranchGlobalId(userAsMember.getBranchGlobalId());
            tokenObject.setRefreshToken(userAsMember.getRefreshToken());

            VisualObject tokenResponse = restTemplate.postForObject("http://localhost:9100/api/v1/auth/tokens", tokenObject, VisualObject.class);
            return tokenResponse;
        } else {
            // get a salt value using the SecureRandom class
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = secureRandom.generateSeed(12);
            systemUserEntity.setSaltValue(salt);
            // hash the password with the salt
            PBEKeySpec pbeKeySpec = new PBEKeySpec(systemUserEntity.getPassword().toCharArray(), salt, 10, 512);
            SecretKeyFactory secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = secretKey.generateSecret(pbeKeySpec).getEncoded();
            //converting to string to store into database
            String hashedPassword = Base64.getMimeEncoder().encodeToString(hash);
            systemUserEntity.setPassword(hashedPassword);
            SystemUserEntity systemUser = systemUserRepository.save(systemUserEntity);
            //get the admin functional group
            FunctionalGroupEntity adminFunctionalGroup = functionalGroupService.findInternalAdminFunctionalGroup();
            SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMapping = new SystemUserFunctionalGroupMappingEntity();
            // assign internal admin group to system user
            systemUserFunctionalGroupMapping.setFunctionalGroupGlobalId(adminFunctionalGroup.getFunctionalGroupGlobalId());
            systemUserFunctionalGroupMapping.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
            systemUserFunctionalGroupMapping.setIsActive(1);
            systemUserFunctionalGroupMappingService.saveSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMapping);
            //posting to auth
            ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, VisualObject.class);
            SystemUserFunctionalGroupMappingEntity authSystemUserFunctionalGroupMapping = new SystemUserFunctionalGroupMappingEntity();
            // assign internal admin group to system user
            authSystemUserFunctionalGroupMapping.setFunctionalGroupGlobalId(adminFunctionalGroup.getFunctionalGroupGlobalId());
            authSystemUserFunctionalGroupMapping.setSystemUserGlobalId(systemUserResponse.getBody().getData().getSystemUserGlobalId());
            authSystemUserFunctionalGroupMapping.setIsActive(1);
            ResponseEntity<VisualObject> systemUserFunctionalGroupResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-user-functional-group-mappings", authSystemUserFunctionalGroupMapping, VisualObject.class);
            SystemUserEntity tokenObject = new SystemUserEntity();

            tokenObject.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
            tokenObject.setTenantGlobalId(systemUser.getTenantGlobalId());
            tokenObject.setTenantName(systemUser.getTenantName());
            tokenObject.setBranchGlobalId(systemUser.getBranchGlobalId());
            tokenObject.setRefreshToken(systemUser.getRefreshToken());
            System.out.println("tokenObject");
            System.out.println(tokenObject);

            VisualObject tokenResponse = restTemplate.postForObject("http://localhost:9100/api/v1/auth/tokens", tokenObject, VisualObject.class);
            System.out.println("tokenResponse");
            System.out.println(tokenResponse);
            return tokenResponse;
        }
    }

    @Transactional
    public List<UserLoginProcedureEntity> userLoginProcedure(SystemUserEntity systemUserEntity) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Inside userLoginProcedure method of SystemUserService");
        // fetch user with corresponding username
        SystemUserEntity systemUser = systemUserRepository.findByPrimaryPhoneOrPrimaryEmail(systemUserEntity.getUserName(),systemUserEntity.getUserName());
        //Obtain the salt from the database and hash the input password
        PBEKeySpec pbeKeySpec = new PBEKeySpec(systemUserEntity.getPassword().toCharArray(), systemUser.getSaltValue(), 10, 512);
        SecretKeyFactory secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hashedInputPassword = secretKey.generateSecret(pbeKeySpec).getEncoded();

        String hashedInputPasswordToString = Base64.getMimeEncoder().encodeToString(hashedInputPassword);
        List<UserLoginProcedureEntity> user = userLoginProcedureRepository.userLoginProcedure(systemUserEntity.getUserName(), hashedInputPasswordToString);
        if (!user.isEmpty()) {
            return user;
        } else {
            throw new CustomNotFoundException("SystemUser with phone - " + systemUserEntity.getUserName() + " - not found");
        }
    }

    public SystemUserEntity giveMemberOnlineAccess(SystemUserEntity systemUserEntity) {
        log.info("Inside giveMemberOnlineAccess method of SystemUserService");
        SystemUserEntity savedMemberResponse = systemUserRepository.save(systemUserEntity);
        ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", savedMemberResponse, VisualObject.class);

        VisualObject functionalGroupResponse = restTemplate.getForObject("http://localhost:9100/api/v1/user/functional-groups/member-online-group", VisualObject.class);

        SystemUserFunctionalGroupMappingEntity memberToSystemUserFunctionalGroupMapping = new SystemUserFunctionalGroupMappingEntity();

        memberToSystemUserFunctionalGroupMapping.setFunctionalGroupGlobalId(functionalGroupResponse.getData().getFunctionalGroupGlobalId());
        memberToSystemUserFunctionalGroupMapping.setSystemUserGlobalId(savedMemberResponse.getSystemUserGlobalId());

        ResponseEntity<SystemUserFunctionalGroupMappingEntity> systemUserFunctionalGroupMappingResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/user/system-user-functional-group-mappings", memberToSystemUserFunctionalGroupMapping, SystemUserFunctionalGroupMappingEntity.class);
        return savedMemberResponse;
    }

    @Transactional
    public List<SystemUserEntity> findAllStaff() {
        log.info("Inside findAllSystemUsers method of SystemUserService");
        List<SystemUserEntity> staff = new ArrayList<SystemUserEntity>();
        staff.addAll(systemUserRepository.findAllStaff());
        if (staff.isEmpty()) {
            throw new CustomNoContentException("System Users not found");
        }

        return staff;
    }

    public List<SystemUserFunctionalGroupsProcedureEntity> systemUserFunctionalGroupsProcedure() {
        log.info("Inside systemUserFunctionalGroupsProcedure method of SystemUserService");
        List<SystemUserFunctionalGroupsProcedureEntity> systemUserFunctionalGroups = systemUserFunctionalGroupsProcedureRepository.systemUserFunctionalGroupsProcedure();
        if (!systemUserFunctionalGroups.isEmpty()) {
            return systemUserFunctionalGroups;
        } else {
            throw new CustomNotFoundException("System User Functional Group Mappings not found");
        }
    }

    public SystemUserEntity findBySystemUserGlobalId(UUID systemUserGlobalId) {
        log.info("Inside findBySystemUserGlobalId method of SystemUserService");
        SystemUserEntity login = systemUserRepository.findBySystemUserGlobalId(systemUserGlobalId);
        if (login != null) {
            return login;
        } else {
            throw new CustomNotFoundException("SystemUser - " + systemUserGlobalId + " - not found");
        }
    }

    public boolean findByPrimaryPhoneOrSecondaryPhone(String phoneNumber) {
        log.info("Inside findByPrimaryPhoneOrSecondaryPhone method of SystemUserService");
        SystemUserEntity userWithPrimaryPhoneOrSecondaryPhone = systemUserRepository.findByPrimaryPhoneOrSecondaryPhone(phoneNumber, phoneNumber);
        if (userWithPrimaryPhoneOrSecondaryPhone != null) {
            return true;
        } else {
            return false;
//			throw new CustomNotFoundException("System User with phone number not found");
        }
    }

    public boolean findByPrimaryEmailOrSecondaryEmail(String email) {
        log.info("Inside findByPrimaryPhoneOrSecondaryEmail method of SystemUserService");
        SystemUserEntity userWithPrimaryEmailOrSecondaryEmail = systemUserRepository.findByPrimaryEmailOrSecondaryEmail(email, email);
        if (userWithPrimaryEmailOrSecondaryEmail != null) {
            return true;
        } else {
            return false;
//			throw new CustomNotFoundException("System User with email not found");
        }
    }

    public List<SystemUserEntity> findAllSystemUsers() {
        log.info("Inside findAllSystemUsers method of SystemUserService");
        List<SystemUserEntity> systemUsers = new ArrayList<SystemUserEntity>();
        systemUsers.addAll(systemUserRepository.findAll());

        if (systemUsers.isEmpty()) {
            throw new CustomNoContentException("SystemUsers not found");
        }

        return systemUsers;
    }

    @Transactional
    public SystemUserEntity patchSystemUser(UUID systemUserGlobalId, JsonPatch jsonPatch)
            throws JsonPatchException, JsonProcessingException {
        log.info("Inside patchSystemUser method of SystemUserService");
        if (systemUserGlobalId.equals(0L)) {
            throw new CustomInvalidInputException("SystemUser id - " + systemUserGlobalId + " - is not valid");
        }

        Optional<SystemUserEntity> login = Optional.ofNullable(systemUserRepository.findBySystemUserGlobalId(systemUserGlobalId));

        if (login.isPresent()) {
            SystemUserEntity loginEntity = this.applyPatchToSystemUserEntity(jsonPatch, login.get());
            return systemUserRepository.save(loginEntity);
        } else {
            throw new CustomNotFoundException("FunctionalGroup with id - " + systemUserGlobalId + " - not found");
        }
    }

    private SystemUserEntity applyPatchToSystemUserEntity(JsonPatch jsonPatch, SystemUserEntity systemUserEntity)
            throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(systemUserEntity, JsonNode.class));
        return objectMapper.treeToValue(patched, SystemUserEntity.class);
    }

    public SystemUserEntity updateSystemUser(UUID systemUserGlobalUuid, SystemUserEntity systemUserEntity) {
        log.info("Inside updateSystemUser method of SystemUserService");
        if (systemUserGlobalUuid.equals(0L)) {
            throw new CustomInvalidInputException("System User id - " + systemUserGlobalUuid + " - is not valid");
        }

        Optional<SystemUserEntity> userOptional = Optional.ofNullable(systemUserRepository.findBySystemUserGlobalId(systemUserGlobalUuid));

        if (userOptional.isPresent()) {
            systemUserEntity.setSystemUserGlobalId(systemUserGlobalUuid);
            systemUserEntity.setMemberGlobalId(systemUserEntity.getMemberGlobalId());
            systemUserEntity.setFirstName(systemUserEntity.getFirstName());
            systemUserEntity.setMiddleName(systemUserEntity.getMiddleName());
            systemUserEntity.setLastName(systemUserEntity.getLastName());
            systemUserEntity.setGender(systemUserEntity.getGender());
            systemUserEntity.setPrimaryEmail(systemUserEntity.getPrimaryEmail());
            systemUserEntity.setPrimaryPhone(systemUserEntity.getPrimaryPhone());
            systemUserEntity.setPassword(systemUserEntity.getPassword());
            systemUserEntity.setIsActive(1);
            systemUserEntity.setIsStaff(1);
            return systemUserRepository.save(systemUserEntity);
        } else {
            throw new CustomNotFoundException("System User with id - " + systemUserGlobalUuid + " - not found");
        }
    }

    public void deleteBySystemUserGlobalId(UUID systemUserGlobalId) {
        log.info("Inside deleteBySystemUserGlobalId method of SystemUserService");
        if (systemUserGlobalId.equals(0L)) {
            throw new CustomInvalidInputException("SystemUser id - " + systemUserGlobalId + " - is not valid");
        }

        systemUserRepository.deleteById(systemUserGlobalId);
    }

    public void deleteAllSystemUsers() {
        log.info("Inside deleteAllSystemUsers method of SystemUserService");
        systemUserRepository.deleteAll();
    }

}

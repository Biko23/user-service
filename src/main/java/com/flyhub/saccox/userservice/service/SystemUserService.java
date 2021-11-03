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
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.List;

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
    private TenantOnlineMembersProcedureRepository tenantOnlineMembersProcedureRepository;

    @Autowired
    private SystemUserFunctionalGroupMappingService systemUserFunctionalGroupMappingService;

    @Autowired
    private FunctionalGroupService functionalGroupService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void writeMyFile(MultipartFile file, Path dir) {
        Path filepath = Paths.get(dir.toString(), file.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static File createThumbnail(File inputImgFile, int thumbnail_width, int thumbnail_height){
        File outputFile=null;
        try {
            BufferedImage img = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_INT_RGB);
            img.createGraphics().drawImage(ImageIO.read(inputImgFile).getScaledInstance(thumbnail_width, thumbnail_height, Image.SCALE_SMOOTH),0,0,null);
            outputFile=new File(inputImgFile.getParentFile()+File.separator+"thumbnail_"+inputImgFile.getName());
            ImageIO.write(img, "jpg", outputFile);
            inputImgFile.delete();
            return outputFile;
        } catch (IOException e) {
            System.out.println("Exception while generating thumbnail "+e.getMessage());
            return null;
        }
    }

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
                                         String answer) throws Exception {
        log.info("Inside systemUserSignup method of SystemUserService");
        SystemUserEntity systemUserEntity = new SystemUserEntity();
        if (file != null) {
            String fileType = file.getContentType();
            System.out.println("fileType");
            System.out.println(fileType);
            if (fileType.equals("image/png") || fileType.equals("image/jpg") || fileType.equals("image/jpeg")) {
                long fileSize = file.getSize();
                long fileSizeKb = fileSize/1024;
                long fileSizeMb = fileSizeKb/1024;
                if (fileSizeMb < 1) {
                    systemUserEntity.setImageLarge(file.getBytes());
                    Path systemUserPictures = Paths.get("src", "main", "resources", "static", "img");
                    String absolutePath = systemUserPictures.toFile().getAbsolutePath();
                    System.out.println(absolutePath);
//                    Assert.aser
//                    Assert.assertTrue(absolutePath.endsWith("src/test/resources"));
                    writeMyFile(file, systemUserPictures);
                    String myPicture = absolutePath + "\\" + file.getOriginalFilename();
                    File myThumbnail = createThumbnail(new File(myPicture), 400, 400);
                    systemUserEntity.setImageSmall(Files.readAllBytes(myThumbnail.toPath()));
                    myThumbnail.delete();
                }else {
                    System.out.println("Sorry file of size " + fileSizeMb + " Mbs is too big!");
                    throw new CustomNotAuthorisedException("Sorry file of size " + fileSizeMb + " Mbs is too big. Please upload an image of less than 1MB");
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
        systemUserEntity.setIsActive(1);
        systemUserEntity.setIsStaff(1);
        systemUserEntity.setIsSystemAdmin(1);
        systemUserEntity.setEverLoggedIn(0);
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
        ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, VisualObject.class);
        //get the admin functional group
        FunctionalGroupEntity adminFunctionalGroup = functionalGroupService.findInternalAdminFunctionalGroup();
        SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMapping = new SystemUserFunctionalGroupMappingEntity();
        // assign internal admin group to system user
        systemUserFunctionalGroupMapping.setFunctionalGroupGlobalId(adminFunctionalGroup.getFunctionalGroupGlobalId());
        systemUserFunctionalGroupMapping.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
        systemUserFunctionalGroupMapping.setIsActive(1);
        systemUserFunctionalGroupMappingService.saveSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMapping);
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
        System.out.println(systemUserEntity);
        if (systemUserEntity.getMemberGlobalId() != null ) { // works for online member being registered as staff
            System.out.println(systemUserEntity);
            SystemUserEntity userAsMember = systemUserRepository.findByMemberGlobalId(systemUserEntity.getMemberGlobalId());
            System.out.println(userAsMember);
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

            // hash the password with the salt
            PBEKeySpec pbeKeySpec = new PBEKeySpec(systemUserEntity.getPassword().toCharArray(), salt, 10, 512);
            SecretKeyFactory secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = secretKey.generateSecret(pbeKeySpec).getEncoded();

            //converting to string to store into database
            String hashedPassword = Base64.getMimeEncoder().encodeToString(hash);

            systemUserEntity.setSaltValue(salt);
            systemUserEntity.setPassword(hashedPassword);
            systemUserEntity.setIsStaff(1);
            systemUserEntity.setIsActive(1);
            systemUserEntity.setIsSystemAdmin(1);
            systemUserEntity.setEverLoggedIn(0);

            System.out.println("before saving user");
            System.out.println(systemUserEntity);
            SystemUserEntity systemUser = systemUserRepository.save(systemUserEntity);
            System.out.println("saved user");
            System.out.println(systemUser);
            //posting to auth
            ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, VisualObject.class);

            //get the admin functional group
            FunctionalGroupEntity adminFunctionalGroup = functionalGroupService.findInternalAdminFunctionalGroup();

            // assign internal admin group to system user
            SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMapping = new SystemUserFunctionalGroupMappingEntity();
            systemUserFunctionalGroupMapping.setFunctionalGroupGlobalId(adminFunctionalGroup.getFunctionalGroupGlobalId());
            systemUserFunctionalGroupMapping.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
            systemUserFunctionalGroupMapping.setIsActive(1);
            systemUserFunctionalGroupMappingService.saveSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMapping);

            SystemUserEntity tokenObject = new SystemUserEntity();

            tokenObject.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
            tokenObject.setTenantGlobalId(systemUser.getTenantGlobalId());
            tokenObject.setTenantName(systemUser.getTenantName());
            tokenObject.setBranchGlobalId(systemUser.getBranchGlobalId());
            tokenObject.setRefreshToken(systemUser.getRefreshToken());

            VisualObject tokenResponse = restTemplate.postForObject("http://localhost:9100/api/v1/auth/tokens", tokenObject, VisualObject.class);

            return tokenResponse;
        }
    }

    @Transactional
    public List<UserLoginProcedureEntity> userLoginProcedure(SystemUserEntity systemUserEntity) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Inside userLoginProcedure method of SystemUserService");
        // fetch user with corresponding username
        SystemUserEntity systemUser = systemUserRepository.findByPrimaryPhoneOrPrimaryEmail(systemUserEntity.getUserName(), systemUserEntity.getUserName());
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

    public SystemUserEntity giveMemberOnlineAccess(SystemUserEntity systemUserEntity) throws InvalidKeySpecException, NoSuchAlgorithmException {
        log.info("Inside giveMemberOnlineAccess method of SystemUserService");
        //before saving add the salt, is_active and is_staff
        System.out.println("systemUserEntity");
        System.out.println(systemUserEntity);
        // get a salt value using the SecureRandom class
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(12);
        // hash the password with the salt
        PBEKeySpec pbeKeySpec = new PBEKeySpec("123456789".toCharArray(), salt, 10, 512);
        SecretKeyFactory secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = secretKey.generateSecret(pbeKeySpec).getEncoded();
        //converting to string to store into database
        String hashedPassword = Base64.getMimeEncoder().encodeToString(hash);
        SystemUserEntity saveMember = new SystemUserEntity();
        saveMember.setFirstName(systemUserEntity.getFirstName());
        saveMember.setLastName(systemUserEntity.getLastName());
        saveMember.setMiddleName(systemUserEntity.getMiddleName());
        saveMember.setMemberGlobalId(systemUserEntity.getMemberGlobalId());
        saveMember.setPrimaryEmail(systemUserEntity.getPrimaryEmail());
        saveMember.setPrimaryPhone(systemUserEntity.getPrimaryPhone());
        saveMember.setPassword(hashedPassword);
        saveMember.setSaltValue(salt);
        saveMember.setTenantGlobalId(systemUserEntity.getTenantGlobalId());
        saveMember.setTenantName(systemUserEntity.getTenantName());
        saveMember.setIsActive(1);
        saveMember.setEverLoggedIn(0);

        //save system user in user
        SystemUserEntity savedMemberResponse = systemUserRepository.save(saveMember);
        //save system user in auth
        ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", savedMemberResponse, VisualObject.class);

        //get member online functional group
        FunctionalGroupEntity functionalGroupResponse=functionalGroupService.findMemberOnlineAccessFunctionalGroup();

        // set system user functional group mapping in user
        SystemUserFunctionalGroupMappingEntity memberToSystemUserFunctionalGroupMapping = new SystemUserFunctionalGroupMappingEntity();
        memberToSystemUserFunctionalGroupMapping.setFunctionalGroupGlobalId(functionalGroupResponse.getFunctionalGroupGlobalId());
        memberToSystemUserFunctionalGroupMapping.setSystemUserGlobalId(savedMemberResponse.getSystemUserGlobalId());
        memberToSystemUserFunctionalGroupMapping.setIsActive(1);
        systemUserFunctionalGroupMappingService.saveSystemUserFunctionalGroupMapping(memberToSystemUserFunctionalGroupMapping);

        // set system user functional group mapping in auth
//        SystemUserFunctionalGroupMappingEntity authSystemUserFunctionalGroupMapping = new SystemUserFunctionalGroupMappingEntity();
//        authSystemUserFunctionalGroupMapping.setFunctionalGroupGlobalId(functionalGroupResponse.getFunctionalGroupGlobalId());
//        authSystemUserFunctionalGroupMapping.setSystemUserGlobalId(systemUserResponse.getBody().getData().getSystemUserGlobalId());
//        authSystemUserFunctionalGroupMapping.setIsActive(1);
//        ResponseEntity<VisualObject> systemUserFunctionalGroupResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-user-functional-group-mappings", authSystemUserFunctionalGroupMapping, VisualObject.class);
        return savedMemberResponse;
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

    @Transactional
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
    public List<TenantOnlineMembersProcedureEntity> findAllOnlineMembers() {
        log.info("Inside findAllOnlineMembers method of SystemUserService");
        List<TenantOnlineMembersProcedureEntity> onlineMembers = tenantOnlineMembersProcedureRepository.tenantOnlineMembersProcedure();

        if (onlineMembers.isEmpty()) {
            throw new CustomNoContentException("Online Members not found");
        }

        return onlineMembers;
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
            throws JsonPatchException,JsonProcessingException {
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

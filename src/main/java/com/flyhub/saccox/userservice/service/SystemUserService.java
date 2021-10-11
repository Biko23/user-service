package com.flyhub.saccox.userservice.service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.*;
import com.flyhub.saccox.userservice.repository.SystemUserFunctionalGroupsProcedureRepository;
import com.flyhub.saccox.userservice.repository.SystemUserRepository;
import com.flyhub.saccox.userservice.repository.UserLoginProcedureRepository;
import com.flyhub.saccox.userservice.visualobject.VisualObject;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public VisualObject systemUserSignup(MultipartFile file,
                                         String first_name,
                                         String middle_name,
                                         String last_name,
                                         String primary_phone,
                                         String primary_email,
                                         String password,
                                         String question,
                                         String answer) throws IOException {
        log.info("Inside systemUserSignup method of SystemUserService");
        SystemUserEntity systemUserEntity = new SystemUserEntity();
        systemUserEntity.setImageSmall(file.getBytes());
        systemUserEntity.setFirstName(first_name);
        systemUserEntity.setMiddleName(middle_name);
        systemUserEntity.setLastName(last_name);
        systemUserEntity.setPrimaryEmail(primary_email);
        systemUserEntity.setPrimaryPhone(primary_phone);
        systemUserEntity.setPassword(password);
        systemUserEntity.setQuestion(question);
        systemUserEntity.setAnswer(answer);
        SystemUserEntity systemUser = systemUserRepository.save(systemUserEntity);
        //get the admin functional group
        VisualObject functionalGroupResponse = restTemplate.getForObject("http://localhost:9100/api/v1/user/functional-groups/internal-admin-group", VisualObject.class);
        SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMapping = new SystemUserFunctionalGroupMappingEntity();
        // assign internal admin group to system user
        systemUserFunctionalGroupMapping.setFunctionalGroupGlobalId(functionalGroupResponse.getData().getFunctionalGroupGlobalId());
        systemUserFunctionalGroupMapping.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
        ResponseEntity<SystemUserFunctionalGroupMappingEntity> systemUserFunctionalGroupMappingResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/user/system-user-functional-group-mappings", systemUserFunctionalGroupMapping, SystemUserFunctionalGroupMappingEntity.class);

        ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, VisualObject.class);
        SystemUserEntity tokenObject = new SystemUserEntity();
        UUID tenantGlobalId = UUID.randomUUID();
        String tenantName = "Tenant Name";
        UUID branchGlobalId = UUID.randomUUID();
        UUID refreshToken = UUID.randomUUID();
        tokenObject.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
        tokenObject.setTenantGlobalId(tenantGlobalId);
        tokenObject.setTenantName(tenantName);
        tokenObject.setBranchGlobalId(branchGlobalId);
        tokenObject.setRefreshToken(refreshToken);
        VisualObject tokenResponse = restTemplate.postForObject("http://localhost:9100/api/v1/auth/tokens", tokenObject, VisualObject.class);
        System.out.println("tokenResponse");
        System.out.println(tokenResponse);
        return tokenResponse;
    }

    public VisualObject saveSystemUser(SystemUserEntity systemUserEntity) {
        log.info("Inside saveSystemUser method of SystemUserService");
        SystemUserEntity userAsMember = systemUserRepository.findByMemberGlobalId(systemUserEntity.getMemberGlobalId());
        if (userAsMember != null) {
            // update the is_active and is_staff
            // use to update member in the system user table given the member id
            updateSystemUser(userAsMember.getSystemUserGlobalId(), userAsMember);
            SystemUserEntity tokenObject = new SystemUserEntity();

            UUID tenantGlobalId = UUID.randomUUID();
            String tenantName = "Tenant Name";
            UUID branchGlobalId = UUID.randomUUID();
            UUID refreshToken = UUID.randomUUID();

            tokenObject.setSystemUserGlobalId(userAsMember.getSystemUserGlobalId());
            tokenObject.setTenantGlobalId(tenantGlobalId);
            tokenObject.setTenantName(tenantName);
            tokenObject.setBranchGlobalId(branchGlobalId);
            tokenObject.setRefreshToken(refreshToken);

            VisualObject tokenResponse = restTemplate.postForObject("http://localhost:9100/api/v1/auth/tokens", tokenObject, VisualObject.class);

            return tokenResponse;
        } else {
            SystemUserEntity systemUser = systemUserRepository.save(systemUserEntity);

            ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, VisualObject.class);

            SystemUserEntity tokenObject = new SystemUserEntity();

            UUID tenantGlobalId = UUID.randomUUID();
            String tenantName = "Tenant Name";
            UUID branchGlobalId = UUID.randomUUID();
            UUID refreshToken = UUID.randomUUID();

            tokenObject.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
            tokenObject.setTenantGlobalId(tenantGlobalId);
            tokenObject.setTenantName(tenantName);
            tokenObject.setBranchGlobalId(branchGlobalId);
            tokenObject.setRefreshToken(refreshToken);

            VisualObject tokenResponse = restTemplate.postForObject("http://localhost:9100/api/v1/auth/tokens", tokenObject, VisualObject.class);

            return tokenResponse;
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

    public SystemUserEntity findBySystemUserGlobalId(UUID systemUserGlobalId) {
        log.info("Inside findBySystemUserGlobalId method of SystemUserService");
        SystemUserEntity login = systemUserRepository.findBySystemUserGlobalId(systemUserGlobalId);
        if (login != null) {
            return login;
        } else {
            throw new CustomNotFoundException("SystemUser - " + systemUserGlobalId + " - not found");
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

    public List<SystemUserEntity> findAllStaff() {
        log.info("Inside findAllSystemUsers method of SystemUserService");
        List<SystemUserEntity> staff = new ArrayList<SystemUserEntity>();
        staff.addAll(systemUserRepository.findAllStaff());
        if (staff.isEmpty()) {
            throw new CustomNoContentException("SystemUsers not found");
        }

        return staff;
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

    private SystemUserEntity applyPatchToSystemUserEntity(JsonPatch jsonPatch, SystemUserEntity systemUserEntity)
            throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(systemUserEntity, JsonNode.class));
        return objectMapper.treeToValue(patched, SystemUserEntity.class);
    }

    public List<UserLoginProcedureEntity> userLoginProcedure(SystemUserEntity systemUserEntity) {
        log.info("Inside userLoginProcedure method of SystemUserService");
        List<UserLoginProcedureEntity> user = userLoginProcedureRepository.userLoginProcedure(systemUserEntity.getUserName(), systemUserEntity.getPassword());
        System.out.println(user);
        if (!user.isEmpty()) {
            return user;
        } else {
            throw new CustomNotFoundException("SystemUser with phone - " + systemUserEntity.getUserName() + " - not found");
        }
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

public SystemUserEntity updateSystemUser(UUID systemUserGlobalUuid, SystemUserEntity systemUserEntity) {
    log.info("Inside updateSystemUser method of SystemUserService");
    if(systemUserGlobalUuid.equals(0L)) {
        throw new  CustomInvalidInputException("System User id - " + systemUserGlobalUuid + " - is not valid");
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
        throw new  CustomNotFoundException("System User with id - " + systemUserGlobalUuid + " - not found");
    }
}


}

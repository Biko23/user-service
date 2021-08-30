package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.microserviceconnect.Tenant;
import com.flyhub.saccox.userservice.microserviceconnect.UserTenant;
import com.flyhub.saccox.userservice.repository.SystemUserRepository;
import com.flyhub.saccox.userservice.visualobject.VisualObject;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;
    
    @Autowired
    private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;
    


	public SystemUserEntity saveSystemUser(SystemUserEntity systemUserEntity) {
//        log.info("Inside saveSystemUser method of SystemUserService");
    	systemUserEntity.setSystemUserTypeIdFk(1);
        SystemUserEntity systemUser = systemUserRepository.save(systemUserEntity);
        ResponseEntity<SystemUserEntity> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, SystemUserEntity.class);

        ResponseEntity<VisualObject> tokenResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUserResponse, VisualObject.class);        
        return systemUser;
    }
	
	public SystemUserEntity findBySystemUserId(UUID systemUserUuid) {
		SystemUserEntity login = systemUserRepository.findBySystemUserId(systemUserUuid);
		if (login != null) {
			return login;
		}
		else {
			throw new CustomNotFoundException("SystemUser - " + systemUserUuid + " - not found");
		}
	}

	public List<SystemUserEntity> findAllSystemUsers() {
		List<SystemUserEntity> systemUsers = new ArrayList<SystemUserEntity>();
		systemUsers.addAll(systemUserRepository.findAll());

		if (systemUsers.isEmpty()) {
			throw new CustomNoContentException("SystemUsers not found");
		}

		return systemUsers;
	}

	public SystemUserEntity patchSystemUser(UUID systemUserUuid, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
//        log.info("Inside patchSystemUser method of FunctionalGroupService");
		if (systemUserUuid.equals(0L)) {
			throw new CustomInvalidInputException("SystemUser id - " + systemUserUuid + " - is not valid");
		}

		Optional<SystemUserEntity> login = Optional.ofNullable(systemUserRepository.findBySystemUserId(systemUserUuid));

		if (login.isPresent()) {
			SystemUserEntity loginEntity = this.applyPatchToSystemUserEntity(jsonPatch, login.get());
			return systemUserRepository.save(loginEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + systemUserUuid + " - not found");
		}
	}

	public void deleteBySystemUserId(UUID systemUserUuid) {
//      log.info("Inside deleteFunctionalGroupById method of FunctionalGroupService");
		if (systemUserUuid.equals(0L)) {
			throw new CustomInvalidInputException("SystemUser id - " + systemUserUuid + " - is not valid");
		}

		systemUserRepository.deleteById(systemUserUuid);
	}

	public void deleteAllSystemUsers() {
//      log.info("Inside deleteAllSystemUsers method of FunctionalGroupService");
		systemUserRepository.deleteAll();
	}

	private SystemUserEntity applyPatchToSystemUserEntity(JsonPatch jsonPatch, SystemUserEntity systemUserEntity)
			throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
		JsonNode patched = jsonPatch.apply(objectMapper.convertValue(systemUserEntity, JsonNode.class));
		return objectMapper.treeToValue(patched, SystemUserEntity.class);
	}
    
//    public UserTenant getUserBelongsToTenant(UUID systemUserUuid) {
////      log.info("Inside findBySystemUserId method of SystemUserService");
//    	UserTenant ut = new UserTenant();
//    	SystemUserEntity user = systemUserRepository.findBySystemUserId(systemUserUuid);
//    	
//    	Tenant tenant = restTemplate.getForObject("http://localhost:9100/api/v1/tenants/tenants/" + user.getTenantGlobalUuid(), Tenant.class);
//    	
//    	ut.setSystemUserEntity(user);
//    	ut.setTenant(tenant);
//    	
//    	return ut;
//    	
//    }
}

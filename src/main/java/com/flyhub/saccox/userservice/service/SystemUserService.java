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
    


	public VisualObject saveSystemUser(SystemUserEntity systemUserEntity) {		
//        log.info("Inside saveSystemUser method of SystemUserService");
        SystemUserEntity systemUser = systemUserRepository.save(systemUserEntity);
        
        
        ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, VisualObject.class);
        
        SystemUserEntity tokenObject = new SystemUserEntity();
        
        tokenObject.setSystemUserId(systemUser.getSystemUserId());
        
        VisualObject tokenResponse = restTemplate.postForObject("http://localhost:9100/api/v1/auth/tokens",tokenObject, VisualObject.class);
		
        System.out.println("tokenResponse");
        System.out.println(tokenResponse);
               
        
        return tokenResponse;
    }
	
	public SystemUserEntity findBySystemUserId(UUID systemUserId) {
		SystemUserEntity login = systemUserRepository.findBySystemUserId(systemUserId);
		if (login != null) {
			return login;
		}
		else {
			throw new CustomNotFoundException("SystemUser - " + systemUserId + " - not found");
		}
	}
	
//	public SystemUserEntity findByPhoneAndPassword(String usernamePassword) {
//		SystemUserEntity user = systemUserRepository.findByPhone1AndPassword(usernamePassword);
//		if (user != null) {
//			return user;
//		}
//		else {
//			throw new CustomNotFoundException("SystemUser with number- " + usernamePassword + " - not found");
//		}
//	}

	public List<SystemUserEntity> findAllSystemUsers() {
		List<SystemUserEntity> systemUsers = new ArrayList<SystemUserEntity>();
		systemUsers.addAll(systemUserRepository.findAll());

		if (systemUsers.isEmpty()) {
			throw new CustomNoContentException("SystemUsers not found");
		}

		return systemUsers;
	}

	public SystemUserEntity patchSystemUser(UUID systemUserId, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
//        log.info("Inside patchSystemUser method of FunctionalGroupService");
		if (systemUserId.equals(0L)) {
			throw new CustomInvalidInputException("SystemUser id - " + systemUserId + " - is not valid");
		}

		Optional<SystemUserEntity> login = Optional.ofNullable(systemUserRepository.findBySystemUserId(systemUserId));

		if (login.isPresent()) {
			SystemUserEntity loginEntity = this.applyPatchToSystemUserEntity(jsonPatch, login.get());
			return systemUserRepository.save(loginEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + systemUserId + " - not found");
		}
	}

	public void deleteBySystemUserId(UUID systemUserId) {
//      log.info("Inside deleteFunctionalGroupById method of FunctionalGroupService");
		if (systemUserId.equals(0L)) {
			throw new CustomInvalidInputException("SystemUser id - " + systemUserId + " - is not valid");
		}

		systemUserRepository.deleteById(systemUserId);
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
    
}

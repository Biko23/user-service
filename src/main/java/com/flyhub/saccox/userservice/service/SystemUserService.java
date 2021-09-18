package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.entity.UserLoginProcedureEntity;
import com.flyhub.saccox.userservice.repository.SystemUserRepository;
import com.flyhub.saccox.userservice.repository.UserLoginProcedureRepository;
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
    private UserLoginProcedureRepository userLoginProcedureRepository;
    
    @Autowired
    private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;
    


	public VisualObject saveSystemUser(SystemUserEntity systemUserEntity) {		
        log.info("Inside saveSystemUser method of SystemUserService");
        SystemUserEntity systemUser = systemUserRepository.save(systemUserEntity);
        
        ResponseEntity<VisualObject> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, VisualObject.class);
		System.out.println("systemUserResponse");
		System.out.println(systemUserResponse);

        SystemUserEntity tokenObject = new SystemUserEntity();
        
        tokenObject.setSystemUserGlobalId(systemUser.getSystemUserGlobalId());
        
        VisualObject tokenResponse = restTemplate.postForObject("http://localhost:9100/api/v1/auth/tokens",tokenObject, VisualObject.class);
		
        System.out.println("tokenResponse");
        System.out.println(tokenResponse);
               
        
        return tokenResponse;
    }
	
	public SystemUserEntity findBySystemUserGlobalId(UUID systemUserGlobalId) {
		log.info("Inside findBySystemUserGlobalId method of SystemUserService");
		SystemUserEntity login = systemUserRepository.findBySystemUserGlobalId(systemUserGlobalId);
		if (login != null) {
			return login;
		}
		else {
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
	
	public List<UserLoginProcedureEntity> userLoginProcedure(String username, String password) {
		log.info("Inside userLoginProcedure method of SystemUserService");
		  List<UserLoginProcedureEntity> user = userLoginProcedureRepository.userLoginProcedure(username, password);

		  if (!user.isEmpty()) {
			  return user;
		  }
		  else {
			  throw new CustomNotFoundException("SystemUser with phone - " + username + " - not found");
		  }
	  }
    
}

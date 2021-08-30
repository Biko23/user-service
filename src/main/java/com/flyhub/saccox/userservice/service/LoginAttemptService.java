package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import com.flyhub.saccox.userservice.entity.LoginAttemptEntity;
import com.flyhub.saccox.userservice.repository.LoginAttemptRepository;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class LoginAttemptService {

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;
	@Autowired
	private ObjectMapper objectMapper;
    


	public LoginAttemptEntity saveLoginAttempt(LoginAttemptEntity loginAttemptEntity) {
			return loginAttemptRepository.save(loginAttemptEntity);
	}
	
	public LoginAttemptEntity findByLoginAttemptId(UUID loginAttemptUuid) {
		LoginAttemptEntity login = loginAttemptRepository.findByLoginAttemptId(loginAttemptUuid);
		if (login != null) {
			return login;
		}
		else {
			throw new CustomNotFoundException("Login attempt - " + loginAttemptUuid + " - not found");
		}
	}

	public List<LoginAttemptEntity> findAllLoginAttempts() {
		List<LoginAttemptEntity> loginAttempts = new ArrayList<LoginAttemptEntity>();
		loginAttempts.addAll(loginAttemptRepository.findAll());

		if (loginAttempts.isEmpty()) {
			throw new CustomNoContentException("Login attempts not found");
		}

		return loginAttempts;
	}

	public LoginAttemptEntity patchLoginAttempt(UUID loginAttemptUuid, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
//        log.info("Inside patchLoginAttempt method of FunctionalGroupService");
		if (loginAttemptUuid.equals(0L)) {
			throw new CustomInvalidInputException("Login attempt id - " + loginAttemptUuid + " - is not valid");
		}

		Optional<LoginAttemptEntity> login = Optional.ofNullable(loginAttemptRepository.findByLoginAttemptId(loginAttemptUuid));

		if (login.isPresent()) {
			LoginAttemptEntity loginEntity = this.applyPatchToLoginAttemptEntity(jsonPatch, login.get());
			return loginAttemptRepository.save(loginEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + loginAttemptUuid + " - not found");
		}
	}

	public void deleteByLoginAttemptId(UUID loginAttemptUuid) {
//      log.info("Inside deleteFunctionalGroupById method of FunctionalGroupService");
		if (loginAttemptUuid.equals(0L)) {
			throw new CustomInvalidInputException("FunctionalGroup id - " + loginAttemptUuid + " - is not valid");
		}

		loginAttemptRepository.deleteById(loginAttemptUuid);
	}

	public void deleteAllLoginAttempts() {
//      log.info("Inside deleteAllFunctionalGroups method of FunctionalGroupService");
		loginAttemptRepository.deleteAll();
	}

	private LoginAttemptEntity applyPatchToLoginAttemptEntity(JsonPatch jsonPatch, LoginAttemptEntity loginAttemptEntity)
			throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
		JsonNode patched = jsonPatch.apply(objectMapper.convertValue(loginAttemptEntity, JsonNode.class));
		return objectMapper.treeToValue(patched, LoginAttemptEntity.class);
	}
}

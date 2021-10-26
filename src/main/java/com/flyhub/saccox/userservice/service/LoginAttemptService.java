package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.LoginAttemptEntity;
import com.flyhub.saccox.userservice.repository.LoginAttemptRepository;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
@Slf4j
public class LoginAttemptService {

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;
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

	public LoginAttemptEntity saveLoginAttempt(LoginAttemptEntity loginAttemptEntity) {
		log.info("Inside saveLoginAttempt method of LoginAttemptService");
		return loginAttemptRepository.save(loginAttemptEntity);
	}
	
	public LoginAttemptEntity findByLoginAttemptGlobalId(UUID loginAttemptGlobalId) {
		log.info("Inside findByLoginAttemptGlobalId method of LoginAttemptService");
		LoginAttemptEntity login = loginAttemptRepository.findByLoginAttemptGlobalId(loginAttemptGlobalId);
		if (login != null) {
			return login;
		}
		else {
			throw new CustomNotFoundException("Login attempt - " + loginAttemptGlobalId + " - not found");
		}
	}

	public List<LoginAttemptEntity> findAllLoginAttempts() {
		log.info("Inside findAllLoginAttempts method of LoginAttemptService");
		List<LoginAttemptEntity> loginAttempts = new ArrayList<LoginAttemptEntity>();
		loginAttempts.addAll(loginAttemptRepository.findAll());

		if (loginAttempts.isEmpty()) {
			throw new CustomNoContentException("Login attempts not found");
		}

		return loginAttempts;
	}

	public LoginAttemptEntity patchLoginAttempt(UUID loginAttemptGlobalId, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
        log.info("Inside patchLoginAttempt method of LoginAttemptService");
		if (loginAttemptGlobalId.equals(0L)) {
			throw new CustomInvalidInputException("Login attempt id - " + loginAttemptGlobalId + " - is not valid");
		}

		Optional<LoginAttemptEntity> login = Optional.ofNullable(loginAttemptRepository.findByLoginAttemptGlobalId(loginAttemptGlobalId));

		if (login.isPresent()) {
			LoginAttemptEntity loginEntity = this.applyPatchToLoginAttemptEntity(jsonPatch, login.get());
			return loginAttemptRepository.save(loginEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + loginAttemptGlobalId + " - not found");
		}
	}

	public void deleteByLoginAttemptGlobalId(UUID loginAttemptGlobalId) {
      log.info("Inside deleteByLoginAttemptGlobalId method of LoginAttemptService");
		if (loginAttemptGlobalId.equals(0L)) {
			throw new CustomInvalidInputException("FunctionalGroup id - " + loginAttemptGlobalId + " - is not valid");
		}

		loginAttemptRepository.deleteById(loginAttemptGlobalId);
	}

	public void deleteAllLoginAttempts() {
//      log.info("Inside deleteAllLoginAttempts method of LoginAttemptService");
		loginAttemptRepository.deleteAll();
	}

	private LoginAttemptEntity applyPatchToLoginAttemptEntity(JsonPatch jsonPatch, LoginAttemptEntity loginAttemptEntity)
			throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
		JsonNode patched = jsonPatch.apply(objectMapper.convertValue(loginAttemptEntity, JsonNode.class));
		return objectMapper.treeToValue(patched, LoginAttemptEntity.class);
	}
}

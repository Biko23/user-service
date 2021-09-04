package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.PasswordEntity;
import com.flyhub.saccox.userservice.entity.PasswordEntity;
import com.flyhub.saccox.userservice.repository.PasswordRepository;
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
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;
	@Autowired
	private ObjectMapper objectMapper;
    


	public PasswordEntity savePassword(PasswordEntity passwordEntity) {
			return passwordRepository.save(passwordEntity);
	}
	
	public PasswordEntity findByPasswordGlobalId(UUID passwordGlobalId) {
		PasswordEntity login = passwordRepository.findByPasswordGlobalId(passwordGlobalId);
		if (login != null) {
			return login;
		}
		else {
			throw new CustomNotFoundException("Password - " + passwordGlobalId + " - not found");
		}
	}

	public List<PasswordEntity> findAllPasswords() {
		List<PasswordEntity> passwords = new ArrayList<PasswordEntity>();
		passwords.addAll(passwordRepository.findAll());

		if (passwords.isEmpty()) {
			throw new CustomNoContentException("Passwords not found");
		}

		return passwords;
	}

	public PasswordEntity patchPassword(UUID passwordGlobalId, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
//        log.info("Inside patchPassword method of FunctionalGroupService");
		if (passwordGlobalId.equals(0L)) {
			throw new CustomInvalidInputException("Password id - " + passwordGlobalId + " - is not valid");
		}

		Optional<PasswordEntity> login = Optional.ofNullable(passwordRepository.findByPasswordGlobalId(passwordGlobalId));

		if (login.isPresent()) {
			PasswordEntity loginEntity = this.applyPatchToPasswordEntity(jsonPatch, login.get());
			return passwordRepository.save(loginEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + passwordGlobalId + " - not found");
		}
	}

	public void deleteByPasswordGlobalId(UUID passwordGlobalId) {
//      log.info("Inside deleteFunctionalGroupById method of FunctionalGroupService");
		if (passwordGlobalId.equals(0L)) {
			throw new CustomInvalidInputException("Password id - " + passwordGlobalId + " - is not valid");
		}

		passwordRepository.deleteById(passwordGlobalId);
	}

	public void deleteAllPasswords() {
//      log.info("Inside deleteAllPasswords method of FunctionalGroupService");
		passwordRepository.deleteAll();
	}

	private PasswordEntity applyPatchToPasswordEntity(JsonPatch jsonPatch, PasswordEntity passwordEntity)
			throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
		JsonNode patched = jsonPatch.apply(objectMapper.convertValue(passwordEntity, JsonNode.class));
		return objectMapper.treeToValue(patched, PasswordEntity.class);
	}

}

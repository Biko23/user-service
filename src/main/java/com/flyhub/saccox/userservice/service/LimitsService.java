package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.LimitsEntity;
import com.flyhub.saccox.userservice.exception.CustomInvalidInputException;
import com.flyhub.saccox.userservice.exception.CustomNoContentException;
import com.flyhub.saccox.userservice.exception.CustomNotFoundException;
import com.flyhub.saccox.userservice.repository.LimitsRepository;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
@Slf4j
public class LimitsService {

    @Autowired
    private LimitsRepository limitsRepository;
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

	public LimitsEntity saveLimits(LimitsEntity limitsEntity) {
		log.info("Inside saveLimits method of LimitsService");
		return limitsRepository.save(limitsEntity);
	}
	
	public LimitsEntity findByLimitsGlobalId(UUID limitsGlobalId) {
		log.info("Inside findByLimitsGlobalId method of LimitsService");
		LimitsEntity login = limitsRepository.findByLimitGlobalId(limitsGlobalId);
		if (login != null) {
			return login;
		}
		else {
			throw new CustomNotFoundException("Limits - " + limitsGlobalId + " - not found");
		}
	}

	public List<LimitsEntity> findAllLimits() {
		log.info("Inside findAllLimits method of LimitsService");
		List<LimitsEntity> limitss = new ArrayList<LimitsEntity>();
		limitss.addAll(limitsRepository.findAllLimits());

		if (limitss.isEmpty()) {
			throw new CustomNoContentException("Limitss not found");
		}

		return limitss;
	}

	public LimitsEntity patchLimits(UUID limitsGlobalId, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
        log.info("Inside patchLimits method of LimitsService");
		if (limitsGlobalId.equals(0L)) {
			throw new CustomInvalidInputException("Limits id - " + limitsGlobalId + " - is not valid");
		}

		Optional<LimitsEntity> login = Optional.ofNullable(limitsRepository.findByLimitGlobalId(limitsGlobalId));

		if (login.isPresent()) {
			LimitsEntity loginEntity = this.applyPatchToLimitsEntity(jsonPatch, login.get());
			return limitsRepository.save(loginEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + limitsGlobalId + " - not found");
		}
	}

	public void deleteByLimitsGlobalId(UUID limitsGlobalId) {
      log.info("Inside deleteFunctionalGroupById method of LimitsService");
		if (limitsGlobalId.equals(0L)) {
			throw new CustomInvalidInputException("Limits id - " + limitsGlobalId + " - is not valid");
		}

		limitsRepository.deleteById(limitsGlobalId);
	}

	public void deleteAllLimits() {
      log.info("Inside deleteAllLimits method of LimitsService");
		limitsRepository.deleteAll();
	}

	private LimitsEntity applyPatchToLimitsEntity(JsonPatch jsonPatch, LimitsEntity limitsEntity)
			throws JsonPatchException, JsonProcessingException {
		JsonNode patched = jsonPatch.apply(objectMapper.convertValue(limitsEntity, JsonNode.class));
		return objectMapper.treeToValue(patched, LimitsEntity.class);
	}
}

package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import com.flyhub.saccox.userservice.visualobject.VisualObject;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.exception.CustomInvalidInputException;
import com.flyhub.saccox.userservice.exception.CustomNoContentException;
import com.flyhub.saccox.userservice.exception.CustomNotFoundException;
import com.flyhub.saccox.userservice.repository.FunctionalGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class FunctionalGroupService {

    @Autowired
    private FunctionalGroupRepository functionalGroupRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RestTemplate restTemplate;

	public Map<String, String> handleValidationExceptions(Errors errors) {
		Map<String, String> errorsMessages = new HashMap<>();
		errors.getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorsMessages.put(fieldName, errorMessage);
		});
		return errorsMessages;
	}

	public FunctionalGroupEntity saveFunctionalGroup(FunctionalGroupEntity functionalGroupEntity) {
		log.info("Inside saveFunctionalGroup method of FunctionalGroupService");
		FunctionalGroupEntity userFunctionalGroupResponse = functionalGroupRepository.save(functionalGroupEntity);
		ResponseEntity<VisualObject> authFunctionalGroupResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/functional-groups", userFunctionalGroupResponse, VisualObject.class);
		return userFunctionalGroupResponse;
	}
	
	public FunctionalGroupEntity findByFunctionalGroupGlobalId(UUID functionalGroupGlobalId) {
		log.info("Inside findByFunctionalGroupGlobalId method of FunctionalGroupService");
		FunctionalGroupEntity functionalGroup = functionalGroupRepository.findByFunctionalGroupGlobalId(functionalGroupGlobalId);
		if (functionalGroup != null) {
			return functionalGroup;
		}
		else {
			throw new CustomNotFoundException("FunctionalGroup - " + functionalGroup + " - not found");
		}
	}

	public FunctionalGroupEntity findMemberOnlineAccessFunctionalGroup(){
      log.info("Inside findMemberOnlineAccessFunctionalGroup method of FunctionalGroupService");
		FunctionalGroupEntity memberOnlineAccessGroup = functionalGroupRepository.findByNameContains("ember");
		return memberOnlineAccessGroup;
	}

	public FunctionalGroupEntity findInternalAdminFunctionalGroup(){
		log.info("Inside findInternalAdminFunctionalGroup method of FunctionalGroupService");
		FunctionalGroupEntity internalAdminFunctionalGroup = functionalGroupRepository.findByNameContains("nternal Admin");
		return internalAdminFunctionalGroup;
	}

	public List<FunctionalGroupEntity> findAllFunctionalGroups() {
		log.info("Inside findAllFunctionalGroups method of FunctionalGroupService");
		List<FunctionalGroupEntity> functionalGroups = new ArrayList<FunctionalGroupEntity>();
		functionalGroups.addAll(functionalGroupRepository.findAll());

		if (functionalGroups.isEmpty()) {
			throw new CustomNoContentException("FunctionalGroups not found");
		}

		return functionalGroups;
	}

	public List<FunctionalGroupEntity> findAllAddedFunctionalGroups() {
		log.info("Inside findAllFunctionalGroups method of FunctionalGroupService");
		List<FunctionalGroupEntity> functionalGroups = functionalGroupRepository.findAllAddedFunctionalGroups();

		if (functionalGroups.isEmpty()) {
			throw new CustomNoContentException("FunctionalGroups not found");
		}

		return functionalGroups;
	}

	public FunctionalGroupEntity patchFunctionalGroup(UUID functionalGroupGlobalId, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
        log.info("Inside patchFunctionalGroup method of FunctionalGroupService");
		if (functionalGroupGlobalId.equals(0L)) {
			throw new CustomInvalidInputException("FunctionalGroup id - " + functionalGroupGlobalId + " - is not valid");
		}

		Optional<FunctionalGroupEntity> functionalGroup = Optional
				.ofNullable(functionalGroupRepository.findByFunctionalGroupGlobalId(functionalGroupGlobalId));

		if (functionalGroup.isPresent()) {
			FunctionalGroupEntity functionalGroupEntity = this.applyPatchToFunctionalGroupEntity(jsonPatch, functionalGroup.get());
			return functionalGroupRepository.save(functionalGroupEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + functionalGroupGlobalId + " - not found");
		}
	}

	public void deleteByFunctionalGroupGlobalId(UUID globalFunctionalGroupUuid) {
      log.info("Inside deleteFunctionalGroupById method of FunctionalGroupService");
		if (globalFunctionalGroupUuid.equals(0L)) {
			throw new CustomInvalidInputException("FunctionalGroup id - " + globalFunctionalGroupUuid + " - is not valid");
		}

		functionalGroupRepository.deleteById(globalFunctionalGroupUuid);
	}

	public void deleteAllFunctionalGroups() {
      log.info("Inside deleteAllFunctionalGroups method of FunctionalGroupService");
		functionalGroupRepository.deleteAll();
	}

	private FunctionalGroupEntity applyPatchToFunctionalGroupEntity(JsonPatch jsonPatch, FunctionalGroupEntity functionalGroupEntity)
			throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
		JsonNode patched = jsonPatch.apply(objectMapper.convertValue(functionalGroupEntity, JsonNode.class));
		return objectMapper.treeToValue(patched, FunctionalGroupEntity.class);
	}
}

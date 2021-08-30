package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.ResponseFilterEntity;
import com.flyhub.saccox.userservice.entity.ResponseFilterEntity;
import com.flyhub.saccox.userservice.repository.ResponseFilterRepository;
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
public class ResponseFilterService {

    @Autowired
    private ResponseFilterRepository responseFilterRepository;
	@Autowired
	private ObjectMapper objectMapper;
    


	public ResponseFilterEntity saveResponseFilter(ResponseFilterEntity responseFilterEntity) {
			return responseFilterRepository.save(responseFilterEntity);
	}
	
	public ResponseFilterEntity findByResponseFilterId(UUID responseFilterUuid) {
		ResponseFilterEntity login = responseFilterRepository.findByResponseFilterId(responseFilterUuid);
		if (login != null) {
			return login;
		}
		else {
			throw new CustomNotFoundException("ResponseFilter - " + responseFilterUuid + " - not found");
		}
	}

	public List<ResponseFilterEntity> findAllResponseFilters() {
		List<ResponseFilterEntity> responseFilters = new ArrayList<ResponseFilterEntity>();
		responseFilters.addAll(responseFilterRepository.findAll());

		if (responseFilters.isEmpty()) {
			throw new CustomNoContentException("ResponseFilters not found");
		}

		return responseFilters;
	}

	public ResponseFilterEntity patchResponseFilter(UUID responseFilterUuid, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
//        log.info("Inside patchResponseFilter method of FunctionalGroupService");
		if (responseFilterUuid.equals(0L)) {
			throw new CustomInvalidInputException("ResponseFilter id - " + responseFilterUuid + " - is not valid");
		}

		Optional<ResponseFilterEntity> login = Optional.ofNullable(responseFilterRepository.findByResponseFilterId(responseFilterUuid));

		if (login.isPresent()) {
			ResponseFilterEntity loginEntity = this.applyPatchToResponseFilterEntity(jsonPatch, login.get());
			return responseFilterRepository.save(loginEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + responseFilterUuid + " - not found");
		}
	}

	public void deleteByResponseFilterId(UUID responseFilterUuid) {
//      log.info("Inside deleteFunctionalGroupById method of FunctionalGroupService");
		if (responseFilterUuid.equals(0L)) {
			throw new CustomInvalidInputException("ResponseFilter id - " + responseFilterUuid + " - is not valid");
		}

		responseFilterRepository.deleteById(responseFilterUuid);
	}

	public void deleteAllResponseFilters() {
//      log.info("Inside deleteAllResponseFilters method of FunctionalGroupService");
		responseFilterRepository.deleteAll();
	}

	private ResponseFilterEntity applyPatchToResponseFilterEntity(JsonPatch jsonPatch, ResponseFilterEntity responseFilterEntity)
			throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
		JsonNode patched = jsonPatch.apply(objectMapper.convertValue(responseFilterEntity, JsonNode.class));
		return objectMapper.treeToValue(patched, ResponseFilterEntity.class);
	}
//
//
//    public ResponseFilterEntity saveResponseFilter(ResponseFilterEntity responseFilterEntity) {
////        log.info("Inside saveResponse method of ResponseService");
//        return responseFilterRepository.save(responseFilterEntity);
//    }
//
//    public ResponseFilterEntity findByResponseFilterId(UUID responseFilterUuid) {
////        log.info("Inside findByResponseId method of ResponseService");
//        return responseFilterRepository.findByResponseFilterUuid(responseFilterUuid);
//    }
//
//    public List<ResponseFilterEntity> listAllResponseFilters() {
////        log.info("Inside listAllResponses method of ResponseService");
//        return responseFilterRepository.findAll();
//    }
//
//    public void deleteResponseFilter(UUID responseFilterUuid) {
////        log.info("Inside deleteResponse method of ResponseService");
//        responseFilterRepository.deleteById(responseFilterUuid);
//    }
//
//    public void deleteAllResponseFilters() {
////        log.info("Inside deleteAllResponses method of ResponseService");
//        responseFilterRepository.deleteAll();
//    }

}

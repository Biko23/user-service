package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.SystemUserFunctionalGroupMappingEntity;
import com.flyhub.saccox.userservice.entity.SystemUserFunctionalGroupMappingEntity;
import com.flyhub.saccox.userservice.repository.SystemUserFunctionalGroupMappingRepository;
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
public class SystemUserFunctionalGroupMappingService {

    @Autowired
    private SystemUserFunctionalGroupMappingRepository systemUserFunctionalGroupMappingRepository;
	@Autowired
	private ObjectMapper objectMapper;
    


	public SystemUserFunctionalGroupMappingEntity saveSystemUserFunctionalGroupMapping(SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMappingEntity) {
			return systemUserFunctionalGroupMappingRepository.save(systemUserFunctionalGroupMappingEntity);
	}
	
	public SystemUserFunctionalGroupMappingEntity findBySystemUserFunctionalGroupMappingId(UUID systemUserFunctionalGroupMappingUuid) {
		SystemUserFunctionalGroupMappingEntity login = systemUserFunctionalGroupMappingRepository.findBySystemUserFunctionalGroupMappingId(systemUserFunctionalGroupMappingUuid);
		if (login != null) {
			return login;
		}
		else {
			throw new CustomNotFoundException("SystemUserFunctionalGroupMapping - " + systemUserFunctionalGroupMappingUuid + " - not found");
		}
	}

	public List<SystemUserFunctionalGroupMappingEntity> findAllSystemUserFunctionalGroupMappings() {
		List<SystemUserFunctionalGroupMappingEntity> systemUserFunctionalGroupMappings = new ArrayList<SystemUserFunctionalGroupMappingEntity>();
		systemUserFunctionalGroupMappings.addAll(systemUserFunctionalGroupMappingRepository.findAll());

		if (systemUserFunctionalGroupMappings.isEmpty()) {
			throw new CustomNoContentException("SystemUserFunctionalGroupMappings not found");
		}

		return systemUserFunctionalGroupMappings;
	}

	public SystemUserFunctionalGroupMappingEntity patchSystemUserFunctionalGroupMapping(UUID systemUserFunctionalGroupMappingUuid, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
//        log.info("Inside patchSystemUserFunctionalGroupMapping method of FunctionalGroupService");
		if (systemUserFunctionalGroupMappingUuid.equals(0L)) {
			throw new CustomInvalidInputException("SystemUserFunctionalGroupMapping id - " + systemUserFunctionalGroupMappingUuid + " - is not valid");
		}

		Optional<SystemUserFunctionalGroupMappingEntity> login = Optional.ofNullable(systemUserFunctionalGroupMappingRepository.findBySystemUserFunctionalGroupMappingId(systemUserFunctionalGroupMappingUuid));

		if (login.isPresent()) {
			SystemUserFunctionalGroupMappingEntity loginEntity = this.applyPatchToSystemUserFunctionalGroupMappingEntity(jsonPatch, login.get());
			return systemUserFunctionalGroupMappingRepository.save(loginEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + systemUserFunctionalGroupMappingUuid + " - not found");
		}
	}

	public void deleteBySystemUserFunctionalGroupMappingId(UUID systemUserFunctionalGroupMappingUuid) {
//      log.info("Inside deleteFunctionalGroupById method of FunctionalGroupService");
		if (systemUserFunctionalGroupMappingUuid.equals(0L)) {
			throw new CustomInvalidInputException("SystemUserFunctionalGroupMapping id - " + systemUserFunctionalGroupMappingUuid + " - is not valid");
		}

		systemUserFunctionalGroupMappingRepository.deleteById(systemUserFunctionalGroupMappingUuid);
	}

	public void deleteAllSystemUserFunctionalGroupMappings() {
//      log.info("Inside deleteAllSystemUserFunctionalGroupMappings method of FunctionalGroupService");
		systemUserFunctionalGroupMappingRepository.deleteAll();
	}

	private SystemUserFunctionalGroupMappingEntity applyPatchToSystemUserFunctionalGroupMappingEntity(JsonPatch jsonPatch, SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMappingEntity)
			throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
		JsonNode patched = jsonPatch.apply(objectMapper.convertValue(systemUserFunctionalGroupMappingEntity, JsonNode.class));
		return objectMapper.treeToValue(patched, SystemUserFunctionalGroupMappingEntity.class);
	}
//
//
//    public SystemUserFunctionalGroupMappingEntity saveSystemUserFunctionalGroupMapping(SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMappingEntity) {
////        log.info("Inside saveSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingService");
//        return systemUserFunctionalGroupMappingRepository.save(systemUserFunctionalGroupMappingEntity);
//    }
//
//    public SystemUserFunctionalGroupMappingEntity findBySystemUserFunctionalGroupMappingId(UUID systemUserFunctionalGroupMappingUuid) {
////        log.info("Inside findBySystemUserFunctionalGroupMappingId method of SystemUserFunctionalGroupMappingService");
//        return systemUserFunctionalGroupMappingRepository.findBySystemUserFunctionalGroupMappingUuid(systemUserFunctionalGroupMappingUuid);
//    }
//
//    public List<SystemUserFunctionalGroupMappingEntity> listAllSystemUserFunctionalGroupMappings() {
////        log.info("Inside listAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingService");
//        return systemUserFunctionalGroupMappingRepository.findAll();
//    }
//
//    public void deleteSystemUserFunctionalGroupMapping(UUID systemUserFunctionalGroupMappingUuid) {
////        log.info("Inside deleteSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingService");
//        systemUserFunctionalGroupMappingRepository.deleteById(systemUserFunctionalGroupMappingUuid);
//    }
//
//    public void deleteAllSystemUserFunctionalGroupMappings() {
////        log.info("Inside deleteAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingService");
//        systemUserFunctionalGroupMappingRepository.deleteAll();
//    }

}

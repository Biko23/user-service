package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.ModuleEntity;
import com.flyhub.saccox.userservice.entity.ModuleEntity;
import com.flyhub.saccox.userservice.repository.ModuleRepository;
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
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
	@Autowired
	private ObjectMapper objectMapper;
    


	public ModuleEntity saveModule(ModuleEntity moduleEntity) {
			return moduleRepository.save(moduleEntity);
	}
	
	public ModuleEntity findByModuleId(UUID moduleId) {
		ModuleEntity login = moduleRepository.findByModuleId(moduleId);
		if (login != null) {
			return login;
		}
		else {
			throw new CustomNotFoundException("Module - " + moduleId + " - not found");
		}
	}

	public List<ModuleEntity> findAllModules() {
		List<ModuleEntity> modules = new ArrayList<ModuleEntity>();
		modules.addAll(moduleRepository.findAll());

		if (modules.isEmpty()) {
			throw new CustomNoContentException("Modules not found");
		}

		return modules;
	}

	public ModuleEntity patchModule(UUID moduleId, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
//        log.info("Inside patchModule method of FunctionalGroupService");
		if (moduleId.equals(0L)) {
			throw new CustomInvalidInputException("Module id - " + moduleId + " - is not valid");
		}

		Optional<ModuleEntity> login = Optional.ofNullable(moduleRepository.findByModuleId(moduleId));

		if (login.isPresent()) {
			ModuleEntity loginEntity = this.applyPatchToModuleEntity(jsonPatch, login.get());
			return moduleRepository.save(loginEntity);
		} else {
			throw new CustomNotFoundException("FunctionalGroup with id - " + moduleId + " - not found");
		}
	}

	public void deleteByModuleId(UUID moduleId) {
//      log.info("Inside deleteFunctionalGroupById method of FunctionalGroupService");
		if (moduleId.equals(0L)) {
			throw new CustomInvalidInputException("Module id - " + moduleId + " - is not valid");
		}

		moduleRepository.deleteById(moduleId);
	}

	public void deleteAllModules() {
//      log.info("Inside deleteAllModules method of FunctionalGroupService");
		moduleRepository.deleteAll();
	}

	private ModuleEntity applyPatchToModuleEntity(JsonPatch jsonPatch, ModuleEntity moduleEntity)
			throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
		JsonNode patched = jsonPatch.apply(objectMapper.convertValue(moduleEntity, JsonNode.class));
		return objectMapper.treeToValue(patched, ModuleEntity.class);
	}
}

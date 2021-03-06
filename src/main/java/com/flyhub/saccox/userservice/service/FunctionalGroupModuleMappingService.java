package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingEntity;
import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingProcedureEntity;
import com.flyhub.saccox.userservice.repository.FunctionalGroupModuleMappingProcedureRepository;
import com.flyhub.saccox.userservice.repository.FunctionalGroupModuleMappingRepository;
import com.flyhub.saccox.userservice.exception.*;
import com.flyhub.saccox.userservice.visualobject.VisualObject;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
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
public class FunctionalGroupModuleMappingService {

    @Autowired
    private FunctionalGroupModuleMappingRepository functionalGroupModuleMappingRepository;

    @Autowired
    private FunctionalGroupModuleMappingProcedureRepository functionalGroupModuleMappingProcedureRepository;

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

    public FunctionalGroupModuleMappingEntity saveFunctionalGroupModuleMapping(FunctionalGroupModuleMappingEntity functionalGroupModuleMappingEntity) {
        log.info("Inside saveFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingService");
        FunctionalGroupModuleMappingEntity userFunctionalGroupModuleMappingResponse = functionalGroupModuleMappingRepository.save(functionalGroupModuleMappingEntity);
        ResponseEntity<VisualObject> authFunctionalGroupModuleMappingResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/functional-group-module-mappings", userFunctionalGroupModuleMappingResponse, VisualObject.class);
        return userFunctionalGroupModuleMappingResponse;
    }

    public List<FunctionalGroupModuleMappingEntity> saveMultipleFunctionalGroupModuleMappings(List<FunctionalGroupModuleMappingEntity> functionalGroupModuleMappingEntity) {
        log.info("Inside saveMultipleFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingService");
        System.out.println(functionalGroupModuleMappingEntity);
        return functionalGroupModuleMappingRepository.saveAll(functionalGroupModuleMappingEntity);
    }

    public FunctionalGroupModuleMappingEntity findByFunctionalGroupModuleMappingGlobalId(UUID functionalGroupModuleMappingGlobalId) {
        log.info("Inside findByFunctionalGroupModuleMappingGlobalId method of FunctionalGroupModuleMappingService");
        if(functionalGroupModuleMappingGlobalId.equals(0L)) {
            throw new CustomInvalidInputException("Functional group module mapping id - " + functionalGroupModuleMappingGlobalId + " - is not valid");
        }

        Optional<FunctionalGroupModuleMappingEntity> systemUserOptional = Optional.ofNullable(functionalGroupModuleMappingRepository.findByFunctionalGroupModuleMappingGlobalId(functionalGroupModuleMappingGlobalId));

        if (systemUserOptional.isPresent()) {
            return systemUserOptional.get();
        } else {
            throw new CustomNotFoundException("Functional group module mapping with id - " + functionalGroupModuleMappingGlobalId + " - not found");
        }
    }

    public List<FunctionalGroupModuleMappingEntity> listAllFunctionalGroupModuleMappings() {
        log.info("Inside listAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingService");
        List<FunctionalGroupModuleMappingEntity> systemUsers = new ArrayList<FunctionalGroupModuleMappingEntity>();
        systemUsers.addAll(functionalGroupModuleMappingRepository.findAll());

        if (systemUsers.isEmpty()) {
            throw new CustomNoContentException("Functional group module mappings not found");
        }

        return systemUsers;
    }

    public FunctionalGroupModuleMappingEntity updateFunctionalGroupModuleMapping(UUID functionalGroupModuleMappingGlobalId, FunctionalGroupModuleMappingEntity functionalGroupModuleMappingEntity) {
        log.info("Inside updateFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingService");
        if(functionalGroupModuleMappingGlobalId.equals(0L)) {
            throw new CustomInvalidInputException("Functional group module mapping id - " + functionalGroupModuleMappingGlobalId + " - is not valid");
        }

        Optional<FunctionalGroupModuleMappingEntity> systemUserOptional = Optional.ofNullable(functionalGroupModuleMappingRepository.findByFunctionalGroupModuleMappingGlobalId(functionalGroupModuleMappingGlobalId));

        if (systemUserOptional.isPresent()) {
            functionalGroupModuleMappingEntity.setFunctionalGroupGlobalId(functionalGroupModuleMappingGlobalId);
            return functionalGroupModuleMappingRepository.save(functionalGroupModuleMappingEntity);
        } else {
            throw new CustomNotFoundException("Functional group module mapping with id - " + functionalGroupModuleMappingGlobalId + " - not found");
        }
    }


    public void deleteFunctionalGroupModuleMapping(UUID functionalGroupModuleMappingGlobalId) {
        log.info("Inside deleteFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingService");
        if(functionalGroupModuleMappingGlobalId.equals(0L)) {
            throw new CustomInvalidInputException("Functional group module mapping id - " + functionalGroupModuleMappingGlobalId + " - is not valid");
        }

        functionalGroupModuleMappingRepository.deleteById(functionalGroupModuleMappingGlobalId);
    }

    public void deleteAllFunctionalGroupModuleMappings() {
        log.info("Inside deleteAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingService");
        functionalGroupModuleMappingRepository.deleteAll();
    }


    public FunctionalGroupModuleMappingEntity patchFunctionalGroupModuleMapping(UUID functionalGroupModuleMappingGlobalId, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.info("Inside patchFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingService");
        if(functionalGroupModuleMappingGlobalId.equals(0L)) {
            throw new CustomInvalidInputException("Mapping id - " + functionalGroupModuleMappingGlobalId + " - is not valid");
        }

        Optional<FunctionalGroupModuleMappingEntity> functionalModuleMappingOptional = Optional.ofNullable(functionalGroupModuleMappingRepository.findByFunctionalGroupModuleMappingGlobalId(functionalGroupModuleMappingGlobalId));

        if (functionalModuleMappingOptional.isPresent()) {
            FunctionalGroupModuleMappingEntity functionalGroupModuleMappingEntity = this.applyPatchToFunctionalGroupModuleMappingEntity(jsonPatch, functionalModuleMappingOptional.get());
            return functionalGroupModuleMappingRepository.save(functionalGroupModuleMappingEntity);
        } else {
            throw new CustomNotFoundException("Member with id - " + functionalGroupModuleMappingGlobalId + " - not found");
        }
    }

    private FunctionalGroupModuleMappingEntity applyPatchToFunctionalGroupModuleMappingEntity(JsonPatch jsonPatch, FunctionalGroupModuleMappingEntity functionalGroupModuleMappingEntity) throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(functionalGroupModuleMappingEntity, JsonNode.class));
        return objectMapper.treeToValue(patched, FunctionalGroupModuleMappingEntity.class);
    }

    public List<FunctionalGroupModuleMappingProcedureEntity> functionalGroupModuleMappingProcedure() {
        log.info("Inside functionalGroupModuleMappingProcedure method of FunctionalGroupModuleMappingService");
        List<FunctionalGroupModuleMappingProcedureEntity> mappings = functionalGroupModuleMappingProcedureRepository.functionalGroupModuleMappingProcedure();

        if (!mappings.isEmpty()) {
            return mappings;
        }
        else {
            throw new CustomNotFoundException("Mappings not found");
        }
    }
}

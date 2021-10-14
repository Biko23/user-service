package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingEntity;
import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingProcedureEntity;
import com.flyhub.saccox.userservice.service.FunctionalGroupModuleMappingService;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/v1/user/functional-group-module-mappings")
@Slf4j
public class FunctionalGroupModuleMappingController {

    @Autowired
    private FunctionalGroupModuleMappingService functionalGroupModuleMappingService;

    @PostMapping("")
    public ResponseEntity<?> saveFunctionalGroupModuleMapping(@Valid @RequestBody FunctionalGroupModuleMappingEntity systemUserEntity, Errors errors) {
        log.info("Inside saveFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", functionalGroupModuleMappingService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        FunctionalGroupModuleMappingEntity _systemUserEntity = functionalGroupModuleMappingService.saveFunctionalGroupModuleMapping(systemUserEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Functional group module mapping created.", _systemUserEntity), HttpStatus.CREATED);
    }

    @GetMapping("/{functionalGroupModuleMappingGlobalId}")
    public ResponseEntity<?> findByFunctionalGroupModuleMappingGlobalId(@PathVariable("functionalGroupModuleMappingGlobalId") UUID functionalGroupModuleMappingGlobalId) {
        log.info("Inside findByFunctionalGroupModuleMappingGlobalId method of FunctionalGroupModuleMappingController");
        FunctionalGroupModuleMappingEntity systemUserEntity = functionalGroupModuleMappingService.findByFunctionalGroupModuleMappingGlobalId(functionalGroupModuleMappingGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Functional group module mapping found.", systemUserEntity), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAllFunctionalGroupModuleMappings() {
        log.info("Inside findAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Functional group module mapping(s) found.", functionalGroupModuleMappingService.listAllFunctionalGroupModuleMappings()), HttpStatus.OK);
    }

    @GetMapping("/module-mappings")
    public ResponseEntity<?> findAllFunctionalGroupModuleProcedureMappings() {
        log.info("Inside findAllFunctionalGroupModuleProcedureMappings method of FunctionalGroupModuleMappingController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Functional group module mapping(s) found.", functionalGroupModuleMappingService.functionalGroupModuleMappingProcedure()), HttpStatus.OK);
    }

    @PutMapping("/{functionalGroupModuleMappingGlobalId}")
    public ResponseEntity<?> updateFunctionalGroupModuleMapping(@PathVariable("functionalGroupModuleMappingGlobalId") @NotNull(message = "Please provide the functional group module mapping ID") UUID functionalGroupModuleMappingGlobalId, @Valid @RequestBody FunctionalGroupModuleMappingEntity systemUserEntity, Errors errors) {
        log.info("Inside updateFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", functionalGroupModuleMappingService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Functional group module mapping updated.", functionalGroupModuleMappingService.updateFunctionalGroupModuleMapping(functionalGroupModuleMappingGlobalId, systemUserEntity)), HttpStatus.OK);
    }

    @PatchMapping(path = "/{functionalGroupModuleMappingGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchFunctionalGroupModuleMapping(@PathVariable("functionalGroupModuleMappingGlobalId") @NotNull(message = "Please provide the functional group module mapping ID") UUID functionalGroupModuleMappingGlobalId, @Valid @RequestBody JsonPatch jsonPatch, Errors errors) throws JsonPatchException, JsonProcessingException {
        log.info("Inside patchFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", functionalGroupModuleMappingService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Mapping updated.", functionalGroupModuleMappingService.patchFunctionalGroupModuleMapping(functionalGroupModuleMappingGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{functionalGroupModuleMappingGlobalId}")
    public ResponseEntity<?> deleteFunctionalGroupModuleMapping(@PathVariable("functionalGroupModuleMappingGlobalId") UUID functionalGroupModuleMappingGlobalId) {
        log.info("Inside deleteFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingController");
        functionalGroupModuleMappingService.deleteFunctionalGroupModuleMapping(functionalGroupModuleMappingGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Functional group module mapping deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllFunctionalGroupModuleMappings() {
        log.info("Inside deleteAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingController");
        functionalGroupModuleMappingService.deleteAllFunctionalGroupModuleMappings();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Functional group module mappings deleted.", null), HttpStatus.OK);
    }


//    @GetMapping("/module-mappings")
//    public ResponseEntity<List<FunctionalGroupModuleMappingProcedureEntity>> findAllFunctionalGroupModuleProcedureMappings() {
//        log.info("Inside findAllFunctionalGroupModuleProcedureMappings method of FunctionalGroupModuleMappingController");
//        try {
//            List<FunctionalGroupModuleMappingProcedureEntity> functionalGroupModuleProcedureMappings = new ArrayList<FunctionalGroupModuleMappingProcedureEntity>();
//            functionalGroupModuleProcedureMappings.addAll(functionalGroupModuleMappingService.functionalGroupModuleMappingProcedure());
//
//            if (functionalGroupModuleProcedureMappings.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(functionalGroupModuleProcedureMappings, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }




}

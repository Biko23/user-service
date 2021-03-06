package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.SystemUserFunctionalGroupMappingEntity;
import com.flyhub.saccox.userservice.service.SystemUserFunctionalGroupMappingService;
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
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/v1/user/system-user-functional-group-mappings")
@Slf4j
public class SystemUserFunctionalGroupMappingController {

    @Autowired
    private SystemUserFunctionalGroupMappingService systemUserFunctionalGroupMappingService;

    @PostMapping("")
    public ResponseEntity<?> saveSystemUserFunctionalGroupMapping(@Valid @RequestBody SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMappingEntity, Errors errors) {
        log.info("Inside saveSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", systemUserFunctionalGroupMappingService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        SystemUserFunctionalGroupMappingEntity _systemUserFunctionalGroupMapping = systemUserFunctionalGroupMappingService.saveSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMappingEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUserFunctionalGroupMapping created.", _systemUserFunctionalGroupMapping), HttpStatus.CREATED);
    }
    
    @GetMapping("/{systemUserFunctionalGroupMappingGlobalId}")
    public ResponseEntity<?> findBySystemUserFunctionalGroupMappingGlobalId(@PathVariable("systemUserFunctionalGroupMappingGlobalId") UUID systemUserFunctionalGroupMappingGlobalId) {
      log.info("Inside findBySystemUserFunctionalGroupMappingGlobalId method of SystemUserFunctionalGroupMappingController");
    	SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMapping = systemUserFunctionalGroupMappingService.findBySystemUserFunctionalGroupMappingGlobalId(systemUserFunctionalGroupMappingGlobalId);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUserFunctionalGroupMapping found.", systemUserFunctionalGroupMapping), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllSystemUserFunctionalGroupMappings() {
        log.info("Inside findAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUserFunctionalGroupMapping(s) found.", systemUserFunctionalGroupMappingService.findAllSystemUserFunctionalGroupMappings()), HttpStatus.OK);
    }

    @PatchMapping(path = "/{systemUserFunctionalGroupMappingGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchSystemUserFunctionalGroupMapping(@PathVariable("systemUserFunctionalGroupMappingGlobalId") @NotNull(message = "Please provide the system user functional group mapping ID") UUID systemUserFunctionalGroupMappingGlobalId, @Valid @RequestBody JsonPatch jsonPatch, Errors errors) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdateSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", systemUserFunctionalGroupMappingService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUserFunctionalGroupMapping updated.", systemUserFunctionalGroupMappingService.patchSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMappingGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{systemUserFunctionalGroupMappingGlobalId}")
    public ResponseEntity<?> deleteBySystemUserFunctionalGroupMappingGlobalId(@PathVariable("systemUserFunctionalGroupMappingGlobalId") UUID systemUserFunctionalGroupMappingGlobalId) {
        log.info("Inside deleteBySystemUserFunctionalGroupMappingGlobalId method of SystemUserFunctionalGroupMappingController");
    	systemUserFunctionalGroupMappingService.deleteBySystemUserFunctionalGroupMappingGlobalId(systemUserFunctionalGroupMappingGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUserFunctionalGroupMapping deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllSystemUserFunctionalGroupMappings() {
        log.info("Inside deleteAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingController");
    	systemUserFunctionalGroupMappingService.deleteAllSystemUserFunctionalGroupMappings();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUserFunctionalGroupMappings deleted.", null), HttpStatus.OK);
    }


}

package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.ModuleEntity;
import com.flyhub.saccox.userservice.service.ModuleService;
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
@RequestMapping("/api/v1/user/modules")
@Slf4j
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("")
    public ResponseEntity<?> saveModule(@Valid @RequestBody ModuleEntity moduleEntity, Errors errors) {
        log.info("Inside saveModule method of ModuleController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", moduleService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        ModuleEntity _module = moduleService.saveModule(moduleEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module created.", _module), HttpStatus.CREATED);
    }
    
    @GetMapping("/{moduleGlobalId}")
    public ResponseEntity<?> findByModuleGlobalId(@PathVariable("moduleGlobalId") UUID moduleGlobalId) {
      log.info("Inside findByModuleGlobalId method of ModuleController");
    	ModuleEntity module = moduleService.findByModuleGlobalId(moduleGlobalId);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module found.", module), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllModules() {
        log.info("Inside findAllModules method of ModuleController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module(s) found.", moduleService.findAllModules()), HttpStatus.OK);
    }

//    @PutMapping("/{globalModuleID}")
//    public ResponseEntity<?> updateModule(@PathVariable("globalModuleID") UUID globalModuleID, @RequestBody ModuleEntity moduleEntity) {
////        log.info("Inside fullUpdateModule method of ModuleController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module updated.", moduleService.updateModule(globalModuleID, moduleEntity)), HttpStatus.OK);
//    }

    @PatchMapping(path = "/{moduleGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchModule(@PathVariable("moduleGlobalId") @NotNull(message = "Please provide the module ID") UUID moduleGlobalId, @Valid  @RequestBody JsonPatch jsonPatch, Errors errors) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdateModule method of ModuleController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", moduleService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module updated.", moduleService.patchModule(moduleGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{moduleGlobalId}")
    public ResponseEntity<?> deleteByModuleGlobalId(@PathVariable("moduleGlobalId") UUID moduleGlobalId) {
        log.info("Inside deleteByModuleGlobalId method of ModuleController");
        moduleService.deleteByModuleGlobalId(moduleGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllModules() {
        log.info("Inside deleteAllModules method of ModuleController");
        moduleService.deleteAllModules();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Modules deleted.", null), HttpStatus.OK);
    }
}

package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.ModuleEntity;
import com.flyhub.saccox.userservice.entity.ModuleEntity;
import com.flyhub.saccox.userservice.service.ModuleService;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/modules")
@Slf4j
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("")
    public ResponseEntity<?> saveModule(@RequestBody ModuleEntity moduleEntity) {
//        log.info("Inside saveModule method of ModuleController");
        ModuleEntity _module = moduleService.saveModule(moduleEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module created.", _module), HttpStatus.CREATED);
    }
    
    @GetMapping("/{moduleUuid}")
    public ResponseEntity<?> findByModuleId(@PathVariable("moduleUuid") UUID moduleUuid) {
//      log.info("Inside findByModuleId method of ModuleController");
    	ModuleEntity module = moduleService.findByModuleId(moduleUuid);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module found.", module), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllModules() {
//        log.info("Inside findAllModules method of ModuleController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module(s) found.", moduleService.findAllModules()), HttpStatus.OK);
    }

//    @PutMapping("/{globalModuleID}")
//    public ResponseEntity<?> updateModule(@PathVariable("globalModuleID") UUID globalModuleID, @RequestBody ModuleEntity moduleEntity) {
////        log.info("Inside fullUpdateModule method of ModuleController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module updated.", moduleService.updateModule(globalModuleID, moduleEntity)), HttpStatus.OK);
//    }

    @PatchMapping(path = "/{moduleUuid}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchModule(@PathVariable("moduleUuid") UUID moduleUuid, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
//        log.info("Inside partialUpdateModule method of ModuleController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module updated.", moduleService.patchModule(moduleUuid, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{moduleUuid}")
    public ResponseEntity<?> deleteByModuleId(@PathVariable("moduleUuid") UUID moduleUuid) {
//        log.info("Inside deleteByModuleId method of ModuleController");
        moduleService.deleteByModuleId(moduleUuid);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Module deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllModules() {
//        log.info("Inside deleteAllModules method of ModuleController");
        moduleService.deleteAllModules();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Modules deleted.", null), HttpStatus.OK);
    }
}

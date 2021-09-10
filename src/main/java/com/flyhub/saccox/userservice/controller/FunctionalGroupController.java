package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import com.flyhub.saccox.userservice.service.FunctionalGroupService;
import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import com.flyhub.saccox.userservice.service.FunctionalGroupService;
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
@RequestMapping("/api/v1/user/functional-groups")
@Slf4j
public class FunctionalGroupController {
	
	@Autowired
	private FunctionalGroupService functionalGroupService;

    @PostMapping("")
    public ResponseEntity<?> saveFunctionalGroup(@RequestBody FunctionalGroupEntity functionalGroupEntity) {
        log.info("Inside saveFunctionalGroup method of FunctionalGroupController");
        FunctionalGroupEntity _functionalGroup = functionalGroupService.saveFunctionalGroup(functionalGroupEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup created.", _functionalGroup), HttpStatus.CREATED);
    }
    
    @GetMapping("/{functionalGroupGlobalId}")
    public ResponseEntity<?> findByFunctionalGroupGlobalId(@PathVariable("functionalGroupGlobalId") UUID functionalGroupGlobalId) {
      log.info("Inside findByFunctionalGroupGlobalId method of FunctionalGroupController");
    	FunctionalGroupEntity functionalGroup = functionalGroupService.findByFunctionalGroupGlobalId(functionalGroupGlobalId);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup found.", functionalGroup), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllFunctionalGroups() {
//        log.info("Inside findAllFunctionalGroups method of FunctionalGroupController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup(s) found.", functionalGroupService.findAllFunctionalGroups()), HttpStatus.OK);
    }

//    @PutMapping("/{globalFunctionalGroupID}")
//    public ResponseEntity<?> updateFunctionalGroup(@PathVariable("globalFunctionalGroupID") UUID globalFunctionalGroupID, @RequestBody FunctionalGroupEntity functionalGroupEntity) {
////        log.info("Inside fullUpdateFunctionalGroup method of FunctionalGroupController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup updated.", functionalGroupService.updateFunctionalGroup(globalFunctionalGroupID, functionalGroupEntity)), HttpStatus.OK);
//    }

    @PatchMapping(path = "/{functionalGroupGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchFunctionalGroup(@PathVariable("functionalGroupGlobalId") UUID functionalGroupGlobalId, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdateFunctionalGroup method of FunctionalGroupController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup updated.", functionalGroupService.patchFunctionalGroup(functionalGroupGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{functionalGroupGlobalId}")
    public ResponseEntity<?> deleteByFunctionalGroupGlobalId(@PathVariable("functionalGroupGlobalId") UUID functionalGroupGlobalId) {
        log.info("Inside deleteByFunctionalGroupGlobalId method of FunctionalGroupController");
        functionalGroupService.deleteByFunctionalGroupGlobalId(functionalGroupGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllFunctionalGroups() {
        log.info("Inside deleteAllFunctionalGroups method of FunctionalGroupController");
        functionalGroupService.deleteAllFunctionalGroups();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroups deleted.", null), HttpStatus.OK);
    }
}

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
//        log.info("Inside saveFunctionalGroup method of FunctionalGroupController");
        FunctionalGroupEntity _functionalGroup = functionalGroupService.saveFunctionalGroup(functionalGroupEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup created.", _functionalGroup), HttpStatus.CREATED);
    }
    
    @GetMapping("/{functionalGroupUuid}")
    public ResponseEntity<?> findByFunctionalGroupId(@PathVariable("functionalGroupUuid") UUID functionalGroupUuid) {
//      log.info("Inside findByFunctionalGroupId method of FunctionalGroupController");
    	FunctionalGroupEntity functionalGroup = functionalGroupService.findByFunctionalGroupId(functionalGroupUuid);
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

    @PatchMapping(path = "/{functionalGroupUuid}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchFunctionalGroup(@PathVariable("functionalGroupUuid") UUID functionalGroupUuid, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
//        log.info("Inside partialUpdateFunctionalGroup method of FunctionalGroupController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup updated.", functionalGroupService.patchFunctionalGroup(functionalGroupUuid, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{functionalGroupUuid}")
    public ResponseEntity<?> deleteByFunctionalGroupId(@PathVariable("functionalGroupUuid") UUID functionalGroupUuid) {
//        log.info("Inside deleteByFunctionalGroupId method of FunctionalGroupController");
        functionalGroupService.deleteByFunctionalGroupId(functionalGroupUuid);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllFunctionalGroups() {
//        log.info("Inside deleteAllFunctionalGroups method of FunctionalGroupController");
        functionalGroupService.deleteAllFunctionalGroups();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroups deleted.", null), HttpStatus.OK);
    }
}

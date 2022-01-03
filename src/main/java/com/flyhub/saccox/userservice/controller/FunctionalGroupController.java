package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import com.flyhub.saccox.userservice.service.FunctionalGroupService;
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
@RequestMapping("/api/v1/user/functional-groups")
@Slf4j
public class FunctionalGroupController {
	
	@Autowired
	private FunctionalGroupService functionalGroupService;

    @PostMapping("")
    public ResponseEntity<?> saveFunctionalGroup(@Valid @RequestBody FunctionalGroupEntity functionalGroupEntity, Errors errors) {
        log.info("Inside saveFunctionalGroup method of FunctionalGroupController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", functionalGroupService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        FunctionalGroupEntity _functionalGroup = functionalGroupService.saveFunctionalGroup(functionalGroupEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup created.", _functionalGroup), HttpStatus.CREATED);
    }
    
    @GetMapping("/{tenantGlobalId}/{functionalGroupGlobalId}")
    public ResponseEntity<?> findByFunctionalGroupGlobalId(@PathVariable("tenantGlobalId") UUID tenantGlobalId, @PathVariable("functionalGroupGlobalId") UUID functionalGroupGlobalId) {
      log.info("Inside findByFunctionalGroupGlobalId method of FunctionalGroupController");
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup found.", functionalGroupService.findByFunctionalGroupGlobalId(tenantGlobalId,functionalGroupGlobalId)), HttpStatus.OK);
    }
    
    @GetMapping("/{tenantGlobalId}")
    public ResponseEntity<?> findAllAddedFunctionalGroups(@PathVariable("tenantGlobalId") UUID tenantGlobalId) {
        log.info("Inside findAllAddedFunctionalGroups method of FunctionalGroupController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup(s) found.", functionalGroupService.findAllAddedFunctionalGroups(tenantGlobalId)), HttpStatus.OK);
    }

//    @GetMapping("/member-online-group")
//    public ResponseEntity<?> findMemberOnlineAccessFunctionalGroup(){
//        log.info("Inside findMemberOnlineAccessFunctionalGroup method of FunctionalGroupController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Member Functional Group found.", functionalGroupService.findMemberOnlineAccessFunctionalGroup()), HttpStatus.OK);
//    }

    @GetMapping("/internal-admin-group")
    public ResponseEntity<?> findInternalAdminFunctionalGroup(){
        log.info("Inside findInternalAdminFunctionalGroup method of FunctionalGroupController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Member Functional Group found.", functionalGroupService.findInternalAdminFunctionalGroup()), HttpStatus.OK);
    }

//    @PutMapping("/{globalFunctionalGroupID}")
//    public ResponseEntity<?> updateFunctionalGroup(@PathVariable("globalFunctionalGroupID") UUID globalFunctionalGroupID, @RequestBody FunctionalGroupEntity functionalGroupEntity) {
////        log.info("Inside fullUpdateFunctionalGroup method of FunctionalGroupController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup updated.", functionalGroupService.updateFunctionalGroup(globalFunctionalGroupID, functionalGroupEntity)), HttpStatus.OK);
//    }

    @PatchMapping(path = "/{tenantGlobalId}/{functionalGroupGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchFunctionalGroup(@PathVariable("tenantGlobalId") @NotNull(message = "Please provide the tenant ID") UUID tenantGlobalId, @PathVariable("functionalGroupGlobalId") @NotNull(message = "Please provide the functional group ID") UUID functionalGroupGlobalId, @Valid @RequestBody JsonPatch jsonPatch, Errors errors) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdateFunctionalGroup method of FunctionalGroupController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", functionalGroupService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "FunctionalGroup updated.", functionalGroupService.patchFunctionalGroup(tenantGlobalId,functionalGroupGlobalId, jsonPatch)), HttpStatus.OK);
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

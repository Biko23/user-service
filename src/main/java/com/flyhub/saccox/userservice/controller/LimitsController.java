package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.LimitsEntity;
import com.flyhub.saccox.userservice.service.LimitsService;
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
@RequestMapping("/api/v1/user/limits")
@Slf4j
public class LimitsController {

    @Autowired
    private LimitsService limitsService;

    @PostMapping("")
    public ResponseEntity<?> saveLimits(@Valid @RequestBody LimitsEntity limitsEntity, Errors errors) {
        log.info("Inside saveLimits method of LimitsController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", limitsService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        LimitsEntity _limits = limitsService.saveLimits(limitsEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Limits created.", _limits), HttpStatus.CREATED);
    }
    
    @GetMapping("/{limitsGlobalId}")
    public ResponseEntity<?> findByLimitsGlobalId(@PathVariable("limitsGlobalId") UUID limitsGlobalId) {
      log.info("Inside findByLimitsGlobalId method of LimitsController");
    	LimitsEntity limits = limitsService.findByLimitsGlobalId(limitsGlobalId);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "Limits found.", limits), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllLimits() {
        log.info("Inside findAllLimits method of LimitsController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Limits found.", limitsService.findAllLimits()), HttpStatus.OK);
    }

//    @PutMapping("/{globalLimitsID}")
//    public ResponseEntity<?> updateLimits(@PathVariable("globalLimitsID") UUID globalLimitsID, @RequestBody LimitsEntity limitsEntity) {
////        log.info("Inside fullUpdateLimits method of LimitsController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Limits updated.", limitsService.updateLimits(globalLimitsID, limitsEntity)), HttpStatus.OK);
//    }

    @PatchMapping(path = "/{limitsGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchLimits(@PathVariable("limitsGlobalId") @NotNull(message = "Please provide the limits ID") UUID limitsGlobalId, @Valid  @RequestBody JsonPatch jsonPatch, Errors errors) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdateLimits method of LimitsController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", limitsService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Limits updated.", limitsService.patchLimits(limitsGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{limitsGlobalId}")
    public ResponseEntity<?> deleteByLimitsGlobalId(@PathVariable("limitsGlobalId") UUID limitsGlobalId) {
        log.info("Inside deleteByLimitsGlobalId method of LimitsController");
        limitsService.deleteByLimitsGlobalId(limitsGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Limits deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllLimits() {
        log.info("Inside deleteAllLimits method of LimitsController");
        limitsService.deleteAllLimits();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Limits deleted.", null), HttpStatus.OK);
    }
}

package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.ResponseFilterEntity;
import com.flyhub.saccox.userservice.entity.ResponseFilterEntity;
import com.flyhub.saccox.userservice.service.ResponseFilterService;
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
@RequestMapping("/api/v1/user/response-filters")
@Slf4j
public class ResponseFilterController {

    @Autowired
    private ResponseFilterService responseFilterService;

    @PostMapping("")
    public ResponseEntity<?> saveResponseFilter(@RequestBody ResponseFilterEntity responseFilterEntity) {
        log.info("Inside saveResponseFilter method of ResponseFilterController");
        ResponseFilterEntity _responseFilter = responseFilterService.saveResponseFilter(responseFilterEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ResponseFilter created.", _responseFilter), HttpStatus.CREATED);
    }
    
    @GetMapping("/{responseFilterGlobalId}")
    public ResponseEntity<?> findByResponseFilterGlobalId(@PathVariable("responseFilterGlobalId") UUID responseFilterGlobalId) {
      log.info("Inside findByResponseFilterGlobalId method of ResponseFilterController");
    	ResponseFilterEntity responseFilter = responseFilterService.findByResponseFilterGlobalId(responseFilterGlobalId);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "ResponseFilter found.", responseFilter), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllResponseFilters() {
        log.info("Inside findAllResponseFilters method of ResponseFilterController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ResponseFilter(s) found.", responseFilterService.findAllResponseFilters()), HttpStatus.OK);
    }

//    @PutMapping("/{globalResponseFilterID}")
//    public ResponseEntity<?> updateResponseFilter(@PathVariable("globalResponseFilterID") UUID globalResponseFilterID, @RequestBody ResponseFilterEntity responseFilterEntity) {
////        log.info("Inside fullUpdateResponseFilter method of ResponseFilterController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ResponseFilter updated.", responseFilterService.updateResponseFilter(globalResponseFilterID, responseFilterEntity)), HttpStatus.OK);
//    }

    @PatchMapping(path = "/{responseFilterGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchResponseFilter(@PathVariable("responseFilterGlobalId") UUID responseFilterGlobalId, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdateResponseFilter method of ResponseFilterController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ResponseFilter updated.", responseFilterService.patchResponseFilter(responseFilterGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{responseFilterGlobalId}")
    public ResponseEntity<?> deleteByResponseFilterGlobalId(@PathVariable("responseFilterGlobalId") UUID responseFilterGlobalId) {
        log.info("Inside deleteByResponseFilterGlobalId method of ResponseFilterController");
        responseFilterService.deleteByResponseFilterGlobalId(responseFilterGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ResponseFilter deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllResponseFilters() {
        log.info("Inside deleteAllResponseFilters method of ResponseFilterController");
        responseFilterService.deleteAllResponseFilters();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ResponseFilters deleted.", null), HttpStatus.OK);
    }

}

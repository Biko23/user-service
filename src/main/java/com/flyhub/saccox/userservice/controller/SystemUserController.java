package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.microserviceconnect.UserTenant;
import com.flyhub.saccox.userservice.service.SystemUserService;
import com.flyhub.saccox.userservice.visualobject.VisualObject;
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
@RequestMapping("/api/v1/user/system-users")
@Slf4j
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("")
    public ResponseEntity<?> saveSystemUser(@RequestBody SystemUserEntity systemUserEntity) {
//        log.info("Inside saveSystemUser method of SystemUserController");
        VisualObject _systemUser = systemUserService.saveSystemUser(systemUserEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser created.", _systemUser), HttpStatus.CREATED);
    }
    
    @GetMapping("/{systemUserId}")
    public ResponseEntity<?> findBySystemUserId(@PathVariable("systemUserId") UUID systemUserId) {
//      log.info("Inside findBySystemUserId method of SystemUserController");
    	SystemUserEntity systemUser = systemUserService.findBySystemUserId(systemUserId);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser found.", systemUser), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllSystemUsers() {
//        log.info("Inside findAllSystemUsers method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser(s) found.", systemUserService.findAllSystemUsers()), HttpStatus.OK);
    }

//    @PutMapping("/{globalSystemUserID}")
//    public ResponseEntity<?> updateSystemUser(@PathVariable("globalSystemUserID") UUID globalSystemUserID, @RequestBody SystemUserEntity systemUserEntity) {
////        log.info("Inside fullUpdateSystemUser method of SystemUserController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser updated.", systemUserService.updateSystemUser(globalSystemUserID, systemUserEntity)), HttpStatus.OK);
//    }

    @PatchMapping(path = "/{systemUserId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchSystemUser(@PathVariable("systemUserId") UUID systemUserId, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
//        log.info("Inside partialUpdateSystemUser method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser updated.", systemUserService.patchSystemUser(systemUserId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{systemUserId}")
    public ResponseEntity<?> deleteBySystemUserId(@PathVariable("systemUserId") UUID systemUserId) {
//        log.info("Inside deleteBySystemUserId method of SystemUserController");
        systemUserService.deleteBySystemUserId(systemUserId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllSystemUsers() {
//        log.info("Inside deleteAllSystemUsers method of SystemUserController");
        systemUserService.deleteAllSystemUsers();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUsers deleted.", null), HttpStatus.OK);
    }


//   
}

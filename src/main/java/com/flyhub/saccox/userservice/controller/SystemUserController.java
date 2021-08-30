package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.microserviceconnect.UserTenant;
import com.flyhub.saccox.userservice.service.SystemUserService;
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
        SystemUserEntity _systemUser = systemUserService.saveSystemUser(systemUserEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser created.", _systemUser), HttpStatus.CREATED);
    }
    
    @GetMapping("/{systemUserUuid}")
    public ResponseEntity<?> findBySystemUserId(@PathVariable("systemUserUuid") UUID systemUserUuid) {
//      log.info("Inside findBySystemUserId method of SystemUserController");
    	SystemUserEntity systemUser = systemUserService.findBySystemUserId(systemUserUuid);
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

    @PatchMapping(path = "/{systemUserUuid}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchSystemUser(@PathVariable("systemUserUuid") UUID systemUserUuid, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
//        log.info("Inside partialUpdateSystemUser method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser updated.", systemUserService.patchSystemUser(systemUserUuid, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{systemUserUuid}")
    public ResponseEntity<?> deleteBySystemUserId(@PathVariable("systemUserUuid") UUID systemUserUuid) {
//        log.info("Inside deleteBySystemUserId method of SystemUserController");
        systemUserService.deleteBySystemUserId(systemUserUuid);
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

package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.entity.UserLoginProcedureEntity;
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

import java.io.IOException;
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
        log.info("Inside saveSystemUser method of SystemUserController");
        VisualObject _systemUser = systemUserService.saveSystemUser(systemUserEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser created.", _systemUser), HttpStatus.CREATED);
    }

    @PostMapping("/member-online-access")
    public ResponseEntity<?> giveMemberOnlineAccess(@RequestBody SystemUserEntity systemUserEntity) {
        log.info("Inside giveMemberOnlineAccess method of SystemUserController");
        SystemUserEntity systemUser = systemUserService.giveMemberOnlineAccess(systemUserEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser created.", systemUser), HttpStatus.CREATED);
    }
    
    @GetMapping("/{systemUserGlobalId}")
    public ResponseEntity<?> findBySystemUserGlobalId(@PathVariable("systemUserGlobalId") UUID systemUserGlobalId) {
      log.info("Inside findBySystemUserGlobalId method of SystemUserController");
    	SystemUserEntity systemUser = systemUserService.findBySystemUserGlobalId(systemUserGlobalId);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser found.", systemUser), HttpStatus.OK);
    }
    
    @GetMapping("/{username}/{password}")
    public ResponseEntity<?> userLoginProcedure(@PathVariable("username") String username, @PathVariable("password") String password) {
      log.info("Inside findBySystemUserGlobalId method of SystemUserController");
    	List<UserLoginProcedureEntity> user = systemUserService.userLoginProcedure(username, password);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser found.", user), HttpStatus.OK);
    }

    @GetMapping("/staff-groups")
    public ResponseEntity<?> systemUserFunctionalGroupsProcedure() {
        log.info("Inside systemUserFunctionalGroupsProcedure method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser(s) found.", systemUserService.systemUserFunctionalGroupsProcedure()), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllSystemUsers() {
        log.info("Inside findAllSystemUsers method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser(s) found.", systemUserService.systemUserFunctionalGroupsProcedure()), HttpStatus.OK);
    }

    @GetMapping("/staff")
    public ResponseEntity<?> findAllStaff() {
        log.info("Inside findAllStaff method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser(s) found.", systemUserService.findAllStaff()), HttpStatus.OK);
    }

    @PatchMapping(path = "/{systemUserGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchSystemUser(@PathVariable("systemUserGlobalId") UUID systemUserGlobalId, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdateSystemUser method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser updated.", systemUserService.patchSystemUser(systemUserGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{systemUserGlobalId}")
    public ResponseEntity<?> deleteBySystemUserGlobalId(@PathVariable("systemUserGlobalId") UUID systemUserGlobalId) {
        log.info("Inside deleteBySystemUserGlobalId method of SystemUserController");
        systemUserService.deleteBySystemUserGlobalId(systemUserGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllSystemUsers() {
        log.info("Inside deleteAllSystemUsers method of SystemUserController");
        systemUserService.deleteAllSystemUsers();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUsers deleted.", null), HttpStatus.OK);
    }
    @GetMapping("/phone-exists/{phoneNumber}")
    public ResponseEntity<?> findByPrimaryPhoneOrSecondaryPhone(@PathVariable("phoneNumber") String phoneNumber) {
        log.info("Inside findByPrimaryPhoneOrSecondaryPhone method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser exists.", systemUserService.findByPrimaryPhoneOrSecondaryPhone(phoneNumber)), HttpStatus.OK);
    }

    @GetMapping("/email-exists/{email}")
    public ResponseEntity<?> findByPrimaryEmailOrSecondaryEmail(@PathVariable("email") String email) {
        log.info("Inside findByPrimaryEmailOrSecondaryEmail method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "SystemUser exists.", systemUserService.findByPrimaryEmailOrSecondaryEmail(email)), HttpStatus.OK);
    }

}

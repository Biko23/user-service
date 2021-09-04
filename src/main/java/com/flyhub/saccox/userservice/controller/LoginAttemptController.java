package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.LoginAttemptEntity;
import com.flyhub.saccox.userservice.entity.LoginAttemptEntity;
import com.flyhub.saccox.userservice.service.LoginAttemptService;
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
@RequestMapping("/api/v1/user/login-attempt")
@Slf4j
public class LoginAttemptController {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @PostMapping("")
    public ResponseEntity<?> saveLoginAttempt(@RequestBody LoginAttemptEntity loginAttemptEntity) {
//        log.info("Inside saveLoginAttempt method of LoginAttemptController");
        LoginAttemptEntity _loginAttempt = loginAttemptService.saveLoginAttempt(loginAttemptEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "LoginAttempt created.", _loginAttempt), HttpStatus.CREATED);
    }
    
    @GetMapping("/{loginAttemptGlobalId}")
    public ResponseEntity<?> findByLoginAttemptGlobalId(@PathVariable("loginAttemptGlobalId") UUID loginAttemptGlobalId) {
//      log.info("Inside findByLoginAttemptGlobalId method of LoginAttemptController");
    	LoginAttemptEntity loginAttempt = loginAttemptService.findByLoginAttemptGlobalId(loginAttemptGlobalId);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "LoginAttempt found.", loginAttempt), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllLoginAttempts() {
//        log.info("Inside findAllLoginAttempts method of LoginAttemptController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "LoginAttempt(s) found.", loginAttemptService.findAllLoginAttempts()), HttpStatus.OK);
    }

//    @PutMapping("/{globalLoginAttemptID}")
//    public ResponseEntity<?> updateLoginAttempt(@PathVariable("globalLoginAttemptID") UUID globalLoginAttemptID, @RequestBody LoginAttemptEntity loginAttemptEntity) {
////        log.info("Inside fullUpdateLoginAttempt method of LoginAttemptController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "LoginAttempt updated.", loginAttemptService.updateLoginAttempt(globalLoginAttemptID, loginAttemptEntity)), HttpStatus.OK);
//    }

    @PatchMapping(path = "/{loginAttemptGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchLoginAttempt(@PathVariable("loginAttemptGlobalId") UUID loginAttemptGlobalId, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
//        log.info("Inside partialUpdateLoginAttempt method of LoginAttemptController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "LoginAttempt updated.", loginAttemptService.patchLoginAttempt(loginAttemptGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{loginAttemptGlobalId}")
    public ResponseEntity<?> deleteByLoginAttemptGlobalId(@PathVariable("loginAttemptGlobalId") UUID loginAttemptGlobalId) {
//        log.info("Inside deleteByLoginAttemptGlobalId method of LoginAttemptController");
        loginAttemptService.deleteByLoginAttemptGlobalId(loginAttemptGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "LoginAttempt deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllLoginAttempts() {
//        log.info("Inside deleteAllLoginAttempts method of LoginAttemptController");
        loginAttemptService.deleteAllLoginAttempts();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "LoginAttempts deleted.", null), HttpStatus.OK);
    }

}

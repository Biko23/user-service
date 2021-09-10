package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.PasswordEntity;
import com.flyhub.saccox.userservice.entity.PasswordEntity;
import com.flyhub.saccox.userservice.service.PasswordService;
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
@RequestMapping("/api/v1/user/passwords")
@Slf4j
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("")
    public ResponseEntity<?> savePassword(@RequestBody PasswordEntity passwordEntity) {
        log.info("Inside savePassword method of PasswordController");
        PasswordEntity _password = passwordService.savePassword(passwordEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Password created.", _password), HttpStatus.CREATED);
    }
    
    @GetMapping("/{passwordGlobalId}")
    public ResponseEntity<?> findByPasswordGlobalId(@PathVariable("passwordGlobalId") UUID passwordGlobalId) {
      log.info("Inside findByPasswordGlobalId method of PasswordController");
    	PasswordEntity password = passwordService.findByPasswordGlobalId(passwordGlobalId);
      return new ResponseEntity<>(new ApiResponseFormat(true, null, "Password found.", password), HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<?> findAllPasswords() {
        log.info("Inside findAllPasswords method of PasswordController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Password(s) found.", passwordService.findAllPasswords()), HttpStatus.OK);
    }

//    @PutMapping("/{globalPasswordID}")
//    public ResponseEntity<?> updatePassword(@PathVariable("globalPasswordID") UUID globalPasswordID, @RequestBody PasswordEntity passwordEntity) {
////        log.info("Inside fullUpdatePassword method of PasswordController");
//        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Password updated.", passwordService.updatePassword(globalPasswordID, passwordEntity)), HttpStatus.OK);
//    }

    @PatchMapping(path = "/{passwordGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchPassword(@PathVariable("passwordGlobalId") UUID passwordGlobalId, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdatePassword method of PasswordController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Password updated.", passwordService.patchPassword(passwordGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{passwordGlobalId}")
    public ResponseEntity<?> deleteByPasswordGlobalId(@PathVariable("passwordGlobalId") UUID passwordGlobalId) {
        log.info("Inside deleteByPasswordGlobalId method of PasswordController");
        passwordService.deleteByPasswordGlobalId(passwordGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Password deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllPasswords() {
        log.info("Inside deleteAllPasswords method of PasswordController");
        passwordService.deleteAllPasswords();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Passwords deleted.", null), HttpStatus.OK);
    }

//    
}

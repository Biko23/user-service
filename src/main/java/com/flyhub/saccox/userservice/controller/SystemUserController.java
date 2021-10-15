package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.entity.UserLoginProcedureEntity;
import com.flyhub.saccox.userservice.model.ApiResponseModel;
import com.flyhub.saccox.userservice.service.SystemUserService;
import com.flyhub.saccox.userservice.visualobject.VisualObject;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/system-users")
@Slf4j
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("")
    public ResponseEntity<?> systemUserSignup(@RequestParam("file") MultipartFile file,
                                              @RequestParam("first_name") String first_name,
                                              @RequestParam("middle_name") String middle_name,
                                              @RequestParam("last_name") String last_name,
                                              @RequestParam("primary_phone") String primary_phone,
                                              @RequestParam("primary_email") String primary_email,
                                              @RequestParam("password") String password,
                                              @RequestParam("question") String question,
                                              @RequestParam("answer") String answer) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Inside systemUserSignup method of SystemUserController");
        ApiResponseModel _systemUser = systemUserService.systemUserSignup(file, first_name, middle_name, last_name, primary_phone, primary_email, password, question, answer);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user created.", _systemUser), HttpStatus.CREATED);
    }

    @PostMapping("/save-user")
    public ResponseEntity<?> saveSystemUser(@Valid @RequestBody SystemUserEntity systemUserEntity, Errors errors) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Inside saveSystemUser method of SystemUserController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", systemUserService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        VisualObject _systemUser = systemUserService.saveSystemUser(systemUserEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user created.", _systemUser), HttpStatus.CREATED);
    }

    @PostMapping("/member-online-access")
    public ResponseEntity<?> giveMemberOnlineAccess(@RequestBody SystemUserEntity systemUserEntity) {
        log.info("Inside giveMemberOnlineAccess method of SystemUserController");
        SystemUserEntity systemUser = systemUserService.giveMemberOnlineAccess(systemUserEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user created.", systemUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLoginProcedure(@RequestBody SystemUserEntity systemUserEntity) throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Inside userLoginProcedure method of SystemUserController");
        List<UserLoginProcedureEntity> systemUser = systemUserService.userLoginProcedure(systemUserEntity);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Logged in successfully", systemUser), HttpStatus.OK);
    }

    @GetMapping("/{systemUserGlobalId}")
    public ResponseEntity<?> findBySystemUserGlobalId(@PathVariable("systemUserGlobalId") UUID systemUserGlobalId) {
        log.info("Inside findBySystemUserGlobalId method of SystemUserController");
        SystemUserEntity systemUser = systemUserService.findBySystemUserGlobalId(systemUserGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user found.", systemUser), HttpStatus.OK);
    }

    @GetMapping("/staff")
    public ResponseEntity<?> systemUserFunctionalGroupsProcedure() {
        log.info("Inside systemUserFunctionalGroupsProcedure method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user(s) found.", systemUserService.systemUserFunctionalGroupsProcedure()), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAllSystemUsers() {
        log.info("Inside findAllSystemUsers method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user(s) found.", systemUserService.findAllSystemUsers()), HttpStatus.OK);
    }

    @PatchMapping(path = "/{systemUserGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchSystemUser(@PathVariable("systemUserGlobalId") UUID systemUserGlobalId, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdateSystemUser method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user updated.", systemUserService.patchSystemUser(systemUserGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{systemUserGlobalId}")
    public ResponseEntity<?> deleteBySystemUserGlobalId(@PathVariable("systemUserGlobalId") UUID systemUserGlobalId) {
        log.info("Inside deleteBySystemUserGlobalId method of SystemUserController");
        systemUserService.deleteBySystemUserGlobalId(systemUserGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllSystemUsers() {
        log.info("Inside deleteAllSystemUsers method of SystemUserController");
        systemUserService.deleteAllSystemUsers();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System users deleted.", null), HttpStatus.OK);
    }

    @GetMapping("/phone-exists/{phoneNumber}")
    public ResponseEntity<?> findByPrimaryPhoneOrSecondaryPhone(@PathVariable("phoneNumber") String phoneNumber) {
        log.info("Inside findByPrimaryPhoneOrSecondaryPhone method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user exists.", systemUserService.findByPrimaryPhoneOrSecondaryPhone(phoneNumber)), HttpStatus.OK);
    }

    @GetMapping("/email-exists/{email}")
    public ResponseEntity<?> findByPrimaryEmailOrSecondaryEmail(@PathVariable("email") String email) {
        log.info("Inside findByPrimaryEmailOrSecondaryEmail method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user exists.", systemUserService.findByPrimaryEmailOrSecondaryEmail(email)), HttpStatus.OK);
    }

}

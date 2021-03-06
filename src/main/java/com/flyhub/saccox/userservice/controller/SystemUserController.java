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
import javax.validation.constraints.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;

@RestController
//@Validated
@RequestMapping("/api/v1/user/system-users")
@Slf4j
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("")
    public ResponseEntity<?> systemUserSignup(@RequestParam("file") @NotNull(message = "Please provide the user image") MultipartFile file,
                                              @RequestParam("first_name") @NotBlank(message = "first_name field cannot be null") @Size(min = 2, max = 250) String first_name,
                                              @RequestParam("middle_name") String middle_name,
                                              @RequestParam("last_name") @NotBlank(message = "last_name field cannot be null") @Size(min = 2, max = 250) String last_name,
                                              @RequestParam("primary_phone") @NotNull(message = "primary_phone field cannot be null") @Pattern(regexp = "((\\+256)\\d{9})", message = "Please enter correct number format (+256701234567)") String primary_phone,
                                              @RequestParam("primary_email") @Email(message = "Please enter the correct email") @NotBlank(message = "The primary_email field cannot be null") String primary_email,
                                              @RequestParam("password") @NotBlank(message = "Password is required") @Size(min=8, max = 250, message = "The password should have a minimum of 8 and a maximum of 250 characters") String password,
                                              @RequestParam("question") String question,
                                              @RequestParam("answer") String answer) throws Exception {
        log.info("Inside systemUserSignup method of SystemUserController");
        ApiResponseModel _systemUser = systemUserService.systemUserSignup(file, first_name, middle_name, last_name, primary_phone, primary_email, password, question, answer);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user created.", _systemUser), HttpStatus.CREATED);
    }

    @PostMapping("/save-user")
    public ResponseEntity<?> saveSystemUser(@Valid @RequestBody SystemUserEntity systemUserEntity, Errors errors) throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println(systemUserEntity);
        log.info("Inside saveSystemUser method of SystemUserController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", systemUserService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user created.", systemUserService.saveSystemUser(systemUserEntity)), HttpStatus.CREATED);
    }

    @PostMapping("/member-online-access")
    public ResponseEntity<?> giveMemberOnlineAccess(@RequestBody SystemUserEntity systemUserEntity) throws InvalidKeySpecException, NoSuchAlgorithmException {
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

    @GetMapping("/{tenantGlobalId}/{systemUserGlobalId}")
    public ResponseEntity<?> findBySystemUserGlobalId(@PathVariable("tenantGlobalId") UUID tenantGlobalId, @PathVariable("systemUserGlobalId") UUID systemUserGlobalId) {
        log.info("Inside findBySystemUserGlobalId method of SystemUserController");
        SystemUserEntity systemUser = systemUserService.findBySystemUserGlobalId(tenantGlobalId,systemUserGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user found.", systemUser), HttpStatus.OK);
    }

    @GetMapping("/staff/{tenantGlobalId}")
    public ResponseEntity<?> systemUserFunctionalGroupsProcedure(@PathVariable("tenantGlobalId") UUID tenantGlobalId) {
        log.info("Inside systemUserFunctionalGroupsProcedure method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user(s) found.", systemUserService.systemUserFunctionalGroupsProcedure(tenantGlobalId)), HttpStatus.OK);
    }
    @GetMapping("/online-members/{tenantGlobalId}")
    public ResponseEntity<?> findAllOnlineMembers(@PathVariable("tenantGlobalId") UUID tenantGlobalId) {
        log.info("Inside findAllOnlineMembers method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "Online members found.", systemUserService.findAllOnlineMembers(tenantGlobalId)), HttpStatus.OK);
    }
    @GetMapping("/tenant/{tenantGlobalId}")
    public ResponseEntity<?> findAllSystemUsers(@PathVariable("tenantGlobalId") UUID tenantGlobalId) {
        log.info("Inside findAllSystemUsers method of SystemUserController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user(s) found.", systemUserService.findAllSystemUsers(tenantGlobalId)), HttpStatus.OK);
    }

    @PatchMapping(path = "/{tenantGlobalId}/{systemUserGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchSystemUser(@PathVariable("tenantGlobalId") @NotNull(message = "Please provide the user ID") UUID tenantGlobalId, @PathVariable("systemUserGlobalId") @NotNull(message = "Please provide the user ID") UUID systemUserGlobalId, @Valid @RequestBody JsonPatch jsonPatch, Errors errors) throws JsonPatchException, JsonProcessingException {
        log.info("Inside patchSystemUser method of SystemUserController");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(new ApiResponseFormat(false, null, "Invalid Values passed for the fields", systemUserService.handleValidationExceptions(errors)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "System user updated.", systemUserService.patchSystemUser(tenantGlobalId, systemUserGlobalId, jsonPatch)), HttpStatus.OK);
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
}

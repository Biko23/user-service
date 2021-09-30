package com.flyhub.saccox.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flyhub.library.apiresponse.ApiResponseFormat;
import com.flyhub.saccox.userservice.entity.ProfilePictureEntity;
import com.flyhub.saccox.userservice.service.ProfilePictureService;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/profile-pic")
@Slf4j
public class ProfilePictureController {

    @Autowired
    private ProfilePictureService profilePictureService;

    @PostMapping("")
    public ResponseEntity<?> saveProfilePicture(@RequestParam MultipartFile file) throws IOException {
        log.info("Inside saveProfilePicture method of ProfilePictureController");
        System.out.println(file);
//        ProfilePictureEntity _profilePicture = profilePictureService.saveProfilePicture(file);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ProfilePicture created.", profilePictureService.saveProfilePicture(file)), HttpStatus.CREATED);
    }

    @PostMapping("/image")
    public ResponseEntity<?> saveProfilePictureImage(@RequestBody MultipartFile UserImage) throws IOException {
        log.info("Inside saveProfilePicture method of ProfilePictureController");
        String _profilePicture = profilePictureService.saveProfilePictureImage(UserImage);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ProfilePicture created.", _profilePicture), HttpStatus.CREATED);
    }

    @GetMapping("/{profilePictureGlobalId}")
    public ResponseEntity<?> findByProfilePictureGlobalId(@PathVariable("profilePictureGlobalId") UUID profilePictureGlobalId) {
        log.info("Inside findByProfilePictureGlobalId method of ProfilePictureController");
        ProfilePictureEntity profilePicture = profilePictureService.findByProfilePictureGlobalId(profilePictureGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ProfilePicture found.", profilePicture), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAllProfilePictures() {
        log.info("Inside findAllProfilePictures method of ProfilePictureController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ProfilePicture(s) found.", profilePictureService.findAllProfilePictures()), HttpStatus.OK);
    }

    @PatchMapping(path = "/{profilePictureGlobalId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchProfilePicture(@PathVariable("profilePictureGlobalId") UUID profilePictureGlobalId, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        log.info("Inside partialUpdateProfilePicture method of ProfilePictureController");
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ProfilePicture updated.", profilePictureService.patchProfilePicture(profilePictureGlobalId, jsonPatch)), HttpStatus.OK);
    }

    @DeleteMapping("/{profilePictureGlobalId}")
    public ResponseEntity<?> deleteByProfilePictureGlobalId(@PathVariable("profilePictureGlobalId") UUID profilePictureGlobalId) {
        log.info("Inside deleteByProfilePictureGlobalId method of ProfilePictureController");
        profilePictureService.deleteByProfilePictureGlobalId(profilePictureGlobalId);
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ProfilePicture deleted.", null), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllProfilePictures() {
        log.info("Inside deleteAllProfilePictures method of ProfilePictureController");
        profilePictureService.deleteAllProfilePictures();
        return new ResponseEntity<>(new ApiResponseFormat(true, null, "ProfilePictures deleted.", null), HttpStatus.OK);
    }

}

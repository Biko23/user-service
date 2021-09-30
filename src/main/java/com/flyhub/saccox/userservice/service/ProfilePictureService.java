package com.flyhub.saccox.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyhub.saccox.userservice.entity.ProfilePictureEntity;
import com.flyhub.saccox.userservice.exception.CustomInvalidInputException;
import com.flyhub.saccox.userservice.exception.CustomNoContentException;
import com.flyhub.saccox.userservice.exception.CustomNotFoundException;
import com.flyhub.saccox.userservice.repository.ProfilePictureRepository;
import com.flyhub.saccox.userservice.repository.UserLoginProcedureRepository;
import com.flyhub.saccox.userservice.visualobject.VisualObject;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProfilePictureService {

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;



    public ProfilePictureEntity saveProfilePicture(MultipartFile file) throws IOException {
        log.info("Inside saveProfilePicture method of ProfilePictureService");
        ProfilePictureEntity picture = new ProfilePictureEntity();
        picture.setContent(file.getBytes());
        System.out.println("file.getBytes()");
        System.out.println(file.getBytes());
        picture.setName(file.getOriginalFilename());
        return profilePictureRepository.save(picture);
    }

    public String saveProfilePictureImage(MultipartFile userImage) {
//		ProfilePictureEntity profilePictureEntity =
        String show = "this is it";
        System.out.println(userImage);
        return show;
    }

    public ProfilePictureEntity findByProfilePictureGlobalId(UUID profilePictureGlobalId) {
        log.info("Inside findByProfilePictureGlobalId method of ProfilePictureService");
        ProfilePictureEntity login = profilePictureRepository.findByProfilePictureGlobalId(profilePictureGlobalId);
        if (login != null) {
            return login;
        }
        else {
            throw new CustomNotFoundException("ProfilePicture - " + profilePictureGlobalId + " - not found");
        }
    }

    public List<ProfilePictureEntity> findAllProfilePictures() {
        log.info("Inside findAllProfilePictures method of ProfilePictureService");
        List<ProfilePictureEntity> profilePictures = new ArrayList<ProfilePictureEntity>();
        profilePictures.addAll(profilePictureRepository.findAll());

        if (profilePictures.isEmpty()) {
            throw new CustomNoContentException("ProfilePictures not found");
        }

        return profilePictures;
    }

    public ProfilePictureEntity patchProfilePicture(UUID profilePictureGlobalId, JsonPatch jsonPatch)
            throws JsonPatchException, JsonProcessingException {
        log.info("Inside patchProfilePicture method of ProfilePictureService");
        if (profilePictureGlobalId.equals(0L)) {
            throw new CustomInvalidInputException("ProfilePicture id - " + profilePictureGlobalId + " - is not valid");
        }

        Optional<ProfilePictureEntity> login = Optional.ofNullable(profilePictureRepository.findByProfilePictureGlobalId(profilePictureGlobalId));

        if (login.isPresent()) {
            ProfilePictureEntity loginEntity = this.applyPatchToProfilePictureEntity(jsonPatch, login.get());
            return profilePictureRepository.save(loginEntity);
        } else {
            throw new CustomNotFoundException("FunctionalGroup with id - " + profilePictureGlobalId + " - not found");
        }
    }

    public void deleteByProfilePictureGlobalId(UUID profilePictureGlobalId) {
        log.info("Inside deleteByProfilePictureGlobalId method of ProfilePictureService");
        if (profilePictureGlobalId.equals(0L)) {
            throw new CustomInvalidInputException("ProfilePicture id - " + profilePictureGlobalId + " - is not valid");
        }

        profilePictureRepository.deleteById(profilePictureGlobalId);
    }

    public void deleteAllProfilePictures() {
        log.info("Inside deleteAllProfilePictures method of ProfilePictureService");
        profilePictureRepository.deleteAll();
    }

    private ProfilePictureEntity applyPatchToProfilePictureEntity(JsonPatch jsonPatch, ProfilePictureEntity profilePictureEntity)
            throws com.github.fge.jsonpatch.JsonPatchException, com.fasterxml.jackson.core.JsonProcessingException {
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(profilePictureEntity, JsonNode.class));
        return objectMapper.treeToValue(patched, ProfilePictureEntity.class);
    }

}

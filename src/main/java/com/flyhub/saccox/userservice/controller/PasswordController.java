package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.PasswordEntity;
import com.flyhub.saccox.userservice.service.PasswordService;
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
    public ResponseEntity<PasswordEntity> savePassword(@RequestBody PasswordEntity passwordEntity) {
//        log.info("Inside savePassword method of PasswordController");
        try {
            PasswordEntity _passwordEntity = passwordService.savePassword(passwordEntity);
            return new ResponseEntity<>(_passwordEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{passwordUuid}")
    public ResponseEntity<PasswordEntity> findByPasswordId(@PathVariable("passwordUuid") UUID passwordUuid) {
//        log.info("Inside findByPasswordId method of PasswordController");
        Optional<PasswordEntity> passwordOptional = Optional.ofNullable(passwordService.findByPasswordId(passwordUuid));

        if (passwordOptional.isPresent()) {
            return new ResponseEntity<>(passwordOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<PasswordEntity>> findAllPasswords() {
//        log.info("Inside findAllPasswords method of PasswordController");
        try {
            List<PasswordEntity> passwords = new ArrayList<PasswordEntity>();
            passwords.addAll(passwordService.listAllPasswords());

            if (passwords.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(passwords, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{passwordUuid}")
    public ResponseEntity<PasswordEntity> fullUpdatePassword(@PathVariable("passwordUuid") UUID passwordUuid, @RequestBody PasswordEntity passwordEntity) {
//        log.info("Inside fullUpdatePassword method of PasswordController");
        Optional<PasswordEntity> passwordOptional = Optional.ofNullable(passwordService.findByPasswordId(passwordUuid));

        if (passwordOptional.isPresent()) {
            passwordEntity.setPasswordUuid(passwordUuid);
            return new ResponseEntity<>(passwordService.savePassword(passwordEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{passwordUuid}")
    public ResponseEntity<PasswordEntity> partialUpdatePassword(@PathVariable("passwordUuid") UUID passwordUuid, @RequestBody PasswordEntity passwordEntity) {
//        log.info("Inside partialUpdatePassword method of PasswordController");
        Optional<PasswordEntity> passwordOptional = Optional.ofNullable(passwordService.findByPasswordId(passwordUuid));

        if (passwordOptional.isPresent()) {
            passwordEntity.setPasswordUuid(passwordUuid);
            return new ResponseEntity<>(passwordService.savePassword(passwordEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{passwordId}")
    public ResponseEntity<HttpStatus> deletePassword(@PathVariable("passwordId") UUID passwordId) {
//        log.info("Inside deletePassword method of PasswordController");
        try {
            passwordService.deletePassword(passwordId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllPasswords() {
//        log.info("Inside deleteAllPasswords method of PasswordController");
        try {
            passwordService.deleteAllPasswords();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

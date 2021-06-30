package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.LoginAttemptEntity;
import com.flyhub.saccox.userservice.service.LoginAttemptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/login-attempt")
@Slf4j
public class LoginAttemptController {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @PostMapping("")
    public ResponseEntity<LoginAttemptEntity> saveLoginAttempt(@RequestBody LoginAttemptEntity loginAttemptEntity) {
        log.info("Inside saveLoginAttempt method of LoginAttemptController");
        try {
            LoginAttemptEntity _loginAttemptEntity = loginAttemptService.saveLoginAttempt(loginAttemptEntity);
            return new ResponseEntity<>(_loginAttemptEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{loginAttemptId}")
    public ResponseEntity<LoginAttemptEntity> findByLoginAttemptId(@PathVariable("loginAttemptId") Long loginAttemptId) {
        log.info("Inside findByLoginAttemptId method of LoginAttemptController");
        Optional<LoginAttemptEntity> loginAttemptOptional = Optional.ofNullable(loginAttemptService.findByLoginAttemptId(loginAttemptId));

        if (loginAttemptOptional.isPresent()) {
            return new ResponseEntity<>(loginAttemptOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<LoginAttemptEntity>> findAllLoginAttempts() {
        log.info("Inside findAllLoginAttempts method of LoginAttemptController");
        try {
            List<LoginAttemptEntity> loginAttempts = new ArrayList<LoginAttemptEntity>();
            loginAttempts.addAll(loginAttemptService.listAllLoginAttempts());

            if (loginAttempts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(loginAttempts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{loginAttemptId}")
    public ResponseEntity<LoginAttemptEntity> fullUpdateLoginAttempt(@PathVariable("loginAttemptId") Long loginAttemptId, @RequestBody LoginAttemptEntity loginAttemptEntity) {
        log.info("Inside fullUpdateLoginAttempt method of LoginAttemptController");
        Optional<LoginAttemptEntity> loginAttemptOptional = Optional.ofNullable(loginAttemptService.findByLoginAttemptId(loginAttemptId));

        if (loginAttemptOptional.isPresent()) {
            loginAttemptEntity.setLoginAttemptId(loginAttemptId);
            return new ResponseEntity<>(loginAttemptService.saveLoginAttempt(loginAttemptEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{loginAttemptId}")
    public ResponseEntity<LoginAttemptEntity> partialUpdateLoginAttempt(@PathVariable("loginAttemptId") Long loginAttemptId, @RequestBody LoginAttemptEntity loginAttemptEntity) {
        log.info("Inside partialUpdateLoginAttempt method of LoginAttemptController");
        Optional<LoginAttemptEntity> loginAttemptOptional = Optional.ofNullable(loginAttemptService.findByLoginAttemptId(loginAttemptId));

        if (loginAttemptOptional.isPresent()) {
            loginAttemptEntity.setLoginAttemptId(loginAttemptId);
            return new ResponseEntity<>(loginAttemptService.saveLoginAttempt(loginAttemptEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{loginAttemptId}")
    public ResponseEntity<HttpStatus> deleteLoginAttempt(@PathVariable("loginAttemptId") Long loginAttemptId) {
        log.info("Inside deleteLoginAttempt method of LoginAttemptController");
        try {
            loginAttemptService.deleteLoginAttempt(loginAttemptId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllLoginAttempts() {
        log.info("Inside deleteAllLoginAttempts method of LoginAttemptController");
        try {
            loginAttemptService.deleteAllLoginAttempts();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

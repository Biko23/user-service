package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.microserviceconnect.UserTenant;
import com.flyhub.saccox.userservice.service.SystemUserService;
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
    public ResponseEntity<SystemUserEntity> saveSystemUser(@RequestBody SystemUserEntity systemUserEntity) {
//        log.info("Inside saveSystemUser method of SystemUserController");
        try {
            SystemUserEntity _systemUserEntity = systemUserService.saveSystemUser(systemUserEntity);
            return new ResponseEntity<>(_systemUserEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @GetMapping("/{systemUserUuid}")
//    public UserTenant getUserBelongsToTenant(@PathVariable("systemUserUuid") UUID systemUserUuid) {
//    	return systemUserService.getUserBelongsToTenant(systemUserUuid);
//    }

//    @GetMapping("/{systemUserId}")
//    public ResponseEntity<SystemUserEntity> findBySystemUserId(@PathVariable("systemUserId") Long systemUserId) {
//        log.info("Inside findBySystemUserId method of SystemUserController");
//        Optional<SystemUserEntity> systemUserOptional = Optional.ofNullable(systemUserService.findBySystemUserId(systemUserId));
//
//        if (systemUserOptional.isPresent()) {
//            return new ResponseEntity<>(systemUserOptional.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("")
    public ResponseEntity<List<SystemUserEntity>> findAllSystemUsers() {
//        log.info("Inside findAllSystemUsers method of SystemUserController");
        try {
            List<SystemUserEntity> systemUsers = new ArrayList<SystemUserEntity>();
            systemUsers.addAll(systemUserService.listAllSystemUsers());

            if (systemUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(systemUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{systemUserUuid}")
    public ResponseEntity<SystemUserEntity> fullUpdateSystemUser(@PathVariable("systemUserUuid") UUID systemUserUuid, @RequestBody SystemUserEntity systemUserEntity) {
//        log.info("Inside fullUpdateSystemUser method of SystemUserController");
        Optional<SystemUserEntity> systemUserOptional = Optional.ofNullable(systemUserService.findBySystemUserId(systemUserUuid));

        if (systemUserOptional.isPresent()) {
            systemUserEntity.setSystemUserUuid(systemUserUuid);
            return new ResponseEntity<>(systemUserService.saveSystemUser(systemUserEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{systemUserUuid}")
    public ResponseEntity<SystemUserEntity> partialUpdateSystemUser(@PathVariable("systemUserUuid") UUID systemUserUuid, @RequestBody SystemUserEntity systemUserEntity) {
//        log.info("Inside partialUpdateSystemUser method of SystemUserController");
        Optional<SystemUserEntity> systemUserOptional = Optional.ofNullable(systemUserService.findBySystemUserId(systemUserUuid));

        if (systemUserOptional.isPresent()) {
            systemUserEntity.setSystemUserUuid(systemUserUuid);
            return new ResponseEntity<>(systemUserService.saveSystemUser(systemUserEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{systemUserId}")
    public ResponseEntity<HttpStatus> deleteSystemUser(@PathVariable("systemUserId") UUID systemUserUuid) {
//        log.info("Inside deleteSystemUser method of SystemUserController");
        try {
            systemUserService.deleteSystemUser(systemUserUuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllSystemUsers() {
//        log.info("Inside deleteAllSystemUsers method of SystemUserController");
        try {
            systemUserService.deleteAllSystemUsers();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.SystemUserTypeEntity;
import com.flyhub.saccox.userservice.service.SystemUserTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/system-user-types")
@Slf4j
public class SystemUserTypeController {

    @Autowired
    private SystemUserTypeService systemUserTypeService;

    @PostMapping("")
    public ResponseEntity<SystemUserTypeEntity> saveSystemUserType(@RequestBody SystemUserTypeEntity systemUserTypeEntity) {
//        log.info("Inside saveSystemUserType method of SystemUserTypeController");
        try {
            SystemUserTypeEntity _systemUserTypeEntity = systemUserTypeService.saveSystemUserType(systemUserTypeEntity);
            return new ResponseEntity<>(_systemUserTypeEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{systemUserTypeId}")
    public ResponseEntity<SystemUserTypeEntity> findBySystemUserTypeId(@PathVariable("systemUserTypeId") Long systemUserTypeId) {
//        log.info("Inside findBySystemUserTypeId method of SystemUserTypeController");
        Optional<SystemUserTypeEntity> systemUserTypeOptional = Optional.ofNullable(systemUserTypeService.findBySystemUserTypeId(systemUserTypeId));

        if (systemUserTypeOptional.isPresent()) {
            return new ResponseEntity<>(systemUserTypeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<SystemUserTypeEntity>> findAllSystemUserTypes() {
//        log.info("Inside findAllSystemUserTypes method of SystemUserTypeController");
        try {
            List<SystemUserTypeEntity> systemUserTypes = new ArrayList<SystemUserTypeEntity>();
            systemUserTypes.addAll(systemUserTypeService.listAllSystemUserTypes());

            if (systemUserTypes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(systemUserTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{systemUserTypeId}")
    public ResponseEntity<SystemUserTypeEntity> fullUpdateSystemUserType(@PathVariable("systemUserTypeId") Long systemUserTypeId, @RequestBody SystemUserTypeEntity systemUserTypeEntity) {
//        log.info("Inside fullUpdateSystemUserType method of SystemUserTypeController");
        Optional<SystemUserTypeEntity> systemUserTypeOptional = Optional.ofNullable(systemUserTypeService.findBySystemUserTypeId(systemUserTypeId));

        if (systemUserTypeOptional.isPresent()) {
            systemUserTypeEntity.setSystemUserTypeId(systemUserTypeId);
            return new ResponseEntity<>(systemUserTypeService.saveSystemUserType(systemUserTypeEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{systemUserTypeId}")
    public ResponseEntity<SystemUserTypeEntity> partialUpdateSystemUserType(@PathVariable("systemUserTypeId") Long systemUserTypeId, @RequestBody SystemUserTypeEntity systemUserTypeEntity) {
//        log.info("Inside partialUpdateSystemUserType method of SystemUserTypeController");
        Optional<SystemUserTypeEntity> systemUserTypeOptional = Optional.ofNullable(systemUserTypeService.findBySystemUserTypeId(systemUserTypeId));

        if (systemUserTypeOptional.isPresent()) {
            systemUserTypeEntity.setSystemUserTypeId(systemUserTypeId);
            return new ResponseEntity<>(systemUserTypeService.saveSystemUserType(systemUserTypeEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{systemUserTypeId}")
    public ResponseEntity<HttpStatus> deleteSystemUserType(@PathVariable("systemUserTypeId") Long systemUserTypeId) {
//        log.info("Inside deleteSystemUserType method of SystemUserTypeController");
        try {
            systemUserTypeService.deleteSystemUserType(systemUserTypeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllSystemUserTypes() {
//        log.info("Inside deleteAllSystemUserTypes method of SystemUserTypeController");
        try {
            systemUserTypeService.deleteAllSystemUserTypes();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

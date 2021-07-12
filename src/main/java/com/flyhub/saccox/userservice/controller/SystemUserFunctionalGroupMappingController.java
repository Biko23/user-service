package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.SystemUserFunctionalGroupMappingEntity;
import com.flyhub.saccox.userservice.service.SystemUserFunctionalGroupMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/system-user-functional-group-mappings")
@Slf4j
public class SystemUserFunctionalGroupMappingController {

    @Autowired
    private SystemUserFunctionalGroupMappingService systemUserFunctionalGroupMapping;

    @PostMapping("")
    public ResponseEntity<SystemUserFunctionalGroupMappingEntity> saveSystemUserFunctionalGroupMapping(@RequestBody SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMappingEntity) {
//        log.info("Inside saveSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingController");
        try {
            SystemUserFunctionalGroupMappingEntity _systemUserFunctionalGroupMappingEntity = systemUserFunctionalGroupMapping.saveSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMappingEntity);
            return new ResponseEntity<>(_systemUserFunctionalGroupMappingEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{systemUserFunctionalGroupMappingId}")
    public ResponseEntity<SystemUserFunctionalGroupMappingEntity> findBySystemUserFunctionalGroupMappingId(@PathVariable("systemUserFunctionalGroupMappingId") Long systemUserFunctionalGroupMappingId) {
//        log.info("Inside findBySystemUserFunctionalGroupMappingId method of SystemUserFunctionalGroupMappingController");
        Optional<SystemUserFunctionalGroupMappingEntity> systemUserFunctionalGroupMappingOptional = Optional.ofNullable(systemUserFunctionalGroupMapping.findBySystemUserFunctionalGroupMappingId(systemUserFunctionalGroupMappingId));

        if (systemUserFunctionalGroupMappingOptional.isPresent()) {
            return new ResponseEntity<>(systemUserFunctionalGroupMappingOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<SystemUserFunctionalGroupMappingEntity>> findAllSystemUserFunctionalGroupMappings() {
//        log.info("Inside findAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingController");
        try {
            List<SystemUserFunctionalGroupMappingEntity> systemUserFunctionalGroupMappings = new ArrayList<SystemUserFunctionalGroupMappingEntity>();
            systemUserFunctionalGroupMappings.addAll(systemUserFunctionalGroupMapping.listAllSystemUserFunctionalGroupMappings());

            if (systemUserFunctionalGroupMappings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(systemUserFunctionalGroupMappings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{systemUserFunctionalGroupMappingId}")
    public ResponseEntity<SystemUserFunctionalGroupMappingEntity> fullUpdateSystemUserFunctionalGroupMapping(@PathVariable("systemUserFunctionalGroupMappingId") Long systemUserFunctionalGroupMappingId, @RequestBody SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMappingEntity) {
//        log.info("Inside fullUpdateSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingController");
        Optional<SystemUserFunctionalGroupMappingEntity> systemUserFunctionalGroupMappingOptional = Optional.ofNullable(systemUserFunctionalGroupMapping.findBySystemUserFunctionalGroupMappingId(systemUserFunctionalGroupMappingId));

        if (systemUserFunctionalGroupMappingOptional.isPresent()) {
            systemUserFunctionalGroupMappingEntity.setSystemUserFunctionalGroupMappingId(systemUserFunctionalGroupMappingId);
            return new ResponseEntity<>(systemUserFunctionalGroupMapping.saveSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMappingEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{systemUserFunctionalGroupMappingId}")
    public ResponseEntity<SystemUserFunctionalGroupMappingEntity> partialUpdateSystemUserFunctionalGroupMapping(@PathVariable("systemUserFunctionalGroupMappingId") Long systemUserFunctionalGroupMappingId, @RequestBody SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMappingEntity) {
//        log.info("Inside partialUpdateSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingController");
        Optional<SystemUserFunctionalGroupMappingEntity> systemUserFunctionalGroupMappingOptional = Optional.ofNullable(systemUserFunctionalGroupMapping.findBySystemUserFunctionalGroupMappingId(systemUserFunctionalGroupMappingId));

        if (systemUserFunctionalGroupMappingOptional.isPresent()) {
            systemUserFunctionalGroupMappingEntity.setSystemUserFunctionalGroupMappingId(systemUserFunctionalGroupMappingId);
            return new ResponseEntity<>(systemUserFunctionalGroupMapping.saveSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMappingEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{systemUserFunctionalGroupMappingId}")
    public ResponseEntity<HttpStatus> deleteSystemUserFunctionalGroupMapping(@PathVariable("systemUserFunctionalGroupMappingId") Long systemUserFunctionalGroupMappingId) {
//        log.info("Inside deleteSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingController");
        try {
            systemUserFunctionalGroupMapping.deleteSystemUserFunctionalGroupMapping(systemUserFunctionalGroupMappingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllSystemUserFunctionalGroupMappings() {
//        log.info("Inside deleteAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingController");
        try {
            systemUserFunctionalGroupMapping.deleteAllSystemUserFunctionalGroupMappings();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

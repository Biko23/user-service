package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import com.flyhub.saccox.userservice.service.FunctionalGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/functional-groups")
@Slf4j
public class FunctionalGroupController {

    @Autowired
    private FunctionalGroupService functionalGroupService;

    @PostMapping("")
    public ResponseEntity<FunctionalGroupEntity> saveFunctionalGroup(@RequestBody FunctionalGroupEntity functionalGroupEntity) {
//        log.info("Inside saveFunctionalGroup method of FunctionalGroupController");
        try {
            FunctionalGroupEntity _functionalGroupEntity = functionalGroupService.saveFunctionalGroup(functionalGroupEntity);
            return new ResponseEntity<>(_functionalGroupEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{functionalGroupId}")
    public ResponseEntity<FunctionalGroupEntity> findByFunctionalGroupId(@PathVariable("functionalGroupId") Long functionalGroupId) {
//        log.info("Inside findByFunctionalGroupId method of FunctionalGroupController");
        Optional<FunctionalGroupEntity> functionalGroupOptional = Optional.ofNullable(functionalGroupService.findByFunctionalGroupId(functionalGroupId));

        if (functionalGroupOptional.isPresent()) {
            return new ResponseEntity<>(functionalGroupOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<FunctionalGroupEntity>> findAllFunctionalGroups() {
//        log.info("Inside findAllFunctionalGroups method of FunctionalGroupController");
        try {
            List<FunctionalGroupEntity> functionalGroups = new ArrayList<FunctionalGroupEntity>();
            functionalGroups.addAll(functionalGroupService.listAllFunctionalGroups());

            if (functionalGroups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(functionalGroups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{functionalGroupId}")
    public ResponseEntity<FunctionalGroupEntity> fullUpdateFunctionalGroup(@PathVariable("functionalGroupId") Long functionalGroupId, @RequestBody FunctionalGroupEntity functionalGroupEntity) {
//        log.info("Inside fullUpdateFunctionalGroup method of FunctionalGroupController");
        Optional<FunctionalGroupEntity> functionalGroupOptional = Optional.ofNullable(functionalGroupService.findByFunctionalGroupId(functionalGroupId));

        if (functionalGroupOptional.isPresent()) {
            functionalGroupEntity.setFunctionalGroupId(functionalGroupId);
            return new ResponseEntity<>(functionalGroupService.saveFunctionalGroup(functionalGroupEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{functionalGroupId}")
    public ResponseEntity<FunctionalGroupEntity> partialUpdateFunctionalGroup(@PathVariable("functionalGroupId") Long functionalGroupId, @RequestBody FunctionalGroupEntity functionalGroupEntity) {
//        log.info("Inside partialUpdateFunctionalGroup method of FunctionalGroupController");
        Optional<FunctionalGroupEntity> functionalGroupOptional = Optional.ofNullable(functionalGroupService.findByFunctionalGroupId(functionalGroupId));

        if (functionalGroupOptional.isPresent()) {
            functionalGroupEntity.setFunctionalGroupId(functionalGroupId);
            return new ResponseEntity<>(functionalGroupService.saveFunctionalGroup(functionalGroupEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{functionalGroupId}")
    public ResponseEntity<HttpStatus> deleteFunctionalGroup(@PathVariable("functionalGroupId") Long functionalGroupId) {
//        log.info("Inside deleteFunctionalGroup method of FunctionalGroupController");
        try {
            functionalGroupService.deleteFunctionalGroup(functionalGroupId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllFunctionalGroups() {
//        log.info("Inside deleteAllFunctionalGroups method of FunctionalGroupController");
        try {
            functionalGroupService.deleteAllFunctionalGroups();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

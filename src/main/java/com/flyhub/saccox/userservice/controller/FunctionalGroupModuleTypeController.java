package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleTypeEntity;
import com.flyhub.saccox.userservice.service.FunctionalGroupModuleTypeService;
import com.flyhub.saccox.userservice.service.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/functional-group-module-types")
@Slf4j
public class FunctionalGroupModuleTypeController {

    @Autowired
    private FunctionalGroupModuleTypeService functionalGroupModuleTypeService;

    @PostMapping("")
    public ResponseEntity<FunctionalGroupModuleTypeEntity> saveFunctionalGroupModuleType(@RequestBody FunctionalGroupModuleTypeEntity functionalGroupModuleTypeEntity) {
        log.info("Inside saveFunctionalGroupModuleType method of FunctionalGroupModuleTypeController");
        try {
            FunctionalGroupModuleTypeEntity _functionalGroupModuleTypeEntity = functionalGroupModuleTypeService.saveFunctionalGroupModuleType(functionalGroupModuleTypeEntity);
            return new ResponseEntity<>(_functionalGroupModuleTypeEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{functionalGroupModuleTypeId}")
    public ResponseEntity<FunctionalGroupModuleTypeEntity> findByFunctionalGroupModuleTypeId(@PathVariable("functionalGroupModuleTypeId") Long functionalGroupModuleTypeId) {
        log.info("Inside findByFunctionalGroupModuleTypeId method of FunctionalGroupModuleTypeController");
        Optional<FunctionalGroupModuleTypeEntity> functionalGroupModuleTypeOptional = Optional.ofNullable(functionalGroupModuleTypeService.findByFunctionalGroupModuleTypeId(functionalGroupModuleTypeId));

        if (functionalGroupModuleTypeOptional.isPresent()) {
            return new ResponseEntity<>(functionalGroupModuleTypeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<FunctionalGroupModuleTypeEntity>> findAllFunctionalGroupModuleTypes() {
        log.info("Inside findAllFunctionalGroupModuleTypes method of FunctionalGroupModuleTypeController");
        try {
            List<FunctionalGroupModuleTypeEntity> functionalGroupModuleTypes = new ArrayList<FunctionalGroupModuleTypeEntity>();
            functionalGroupModuleTypes.addAll(functionalGroupModuleTypeService.listAllFunctionalGroupModuleTypes());

            if (functionalGroupModuleTypes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(functionalGroupModuleTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{functionalGroupModuleTypeId}")
    public ResponseEntity<FunctionalGroupModuleTypeEntity> fullUpdateFunctionalGroupModuleType(@PathVariable("functionalGroupModuleTypeId") Long functionalGroupModuleTypeId, @RequestBody FunctionalGroupModuleTypeEntity functionalGroupModuleTypeEntity) {
        log.info("Inside fullUpdateFunctionalGroupModuleType method of FunctionalGroupModuleTypeController");
        Optional<FunctionalGroupModuleTypeEntity> functionalGroupModuleTypeOptional = Optional.ofNullable(functionalGroupModuleTypeService.findByFunctionalGroupModuleTypeId(functionalGroupModuleTypeId));

        if (functionalGroupModuleTypeOptional.isPresent()) {
            functionalGroupModuleTypeEntity.setFunctionalGroupModuleTypeId(functionalGroupModuleTypeId);
            return new ResponseEntity<>(functionalGroupModuleTypeService.saveFunctionalGroupModuleType(functionalGroupModuleTypeEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{functionalGroupModuleTypeId}")
    public ResponseEntity<FunctionalGroupModuleTypeEntity> partialUpdateFunctionalGroupModuleType(@PathVariable("functionalGroupModuleTypeId") Long functionalGroupModuleTypeId, @RequestBody FunctionalGroupModuleTypeEntity functionalGroupModuleTypeEntity) {
        log.info("Inside partialUpdateFunctionalGroupModuleType method of FunctionalGroupModuleTypeController");
        Optional<FunctionalGroupModuleTypeEntity> functionalGroupModuleTypeOptional = Optional.ofNullable(functionalGroupModuleTypeService.findByFunctionalGroupModuleTypeId(functionalGroupModuleTypeId));

        if (functionalGroupModuleTypeOptional.isPresent()) {
            functionalGroupModuleTypeEntity.setFunctionalGroupModuleTypeId(functionalGroupModuleTypeId);
            return new ResponseEntity<>(functionalGroupModuleTypeService.saveFunctionalGroupModuleType(functionalGroupModuleTypeEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{functionalGroupModuleTypeId}")
    public ResponseEntity<HttpStatus> deleteFunctionalGroupModuleType(@PathVariable("functionalGroupModuleTypeId") Long functionalGroupModuleTypeId) {
        log.info("Inside deleteFunctionalGroupModuleType method of FunctionalGroupModuleTypeController");
        try {
            functionalGroupModuleTypeService.deleteFunctionalGroupModuleType(functionalGroupModuleTypeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllFunctionalGroupModuleTypes() {
        log.info("Inside deleteAllFunctionalGroupModuleTypes method of FunctionalGroupModuleTypeController");
        try {
            functionalGroupModuleTypeService.deleteAllFunctionalGroupModuleTypes();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

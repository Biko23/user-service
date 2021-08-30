package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingEntity;
import com.flyhub.saccox.userservice.service.FunctionalGroupModuleMappingService;
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
@RequestMapping("/api/v1/user/functional-group-module-mappings")
@Slf4j
public class FunctionalGroupModuleMappingController {

    @Autowired
    private FunctionalGroupModuleMappingService functionalGroupModuleMappingService;

    @PostMapping("")
    public ResponseEntity<FunctionalGroupModuleMappingEntity> saveFunctionalGroupModuleMapping(@RequestBody FunctionalGroupModuleMappingEntity functionalGroupModuleMappingEntity) {
//        log.info("Inside saveFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingController");
        try {
            FunctionalGroupModuleMappingEntity _functionalGroupModuleMappingEntity = functionalGroupModuleMappingService.saveFunctionalGroupModuleMapping(functionalGroupModuleMappingEntity);
            return new ResponseEntity<>(_functionalGroupModuleMappingEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{functionalGroupModuleMappingUuid}")
    public ResponseEntity<FunctionalGroupModuleMappingEntity> findByFunctionalGroupModuleMappingId(@PathVariable("functionalGroupModuleMappingUuid") UUID functionalGroupModuleMappingUuid) {
//        log.info("Inside findByFunctionalGroupModuleMappingId method of FunctionalGroupModuleMappingController");
        Optional<FunctionalGroupModuleMappingEntity> functionalGroupModuleMappingOptional = Optional.ofNullable(functionalGroupModuleMappingService.findByFunctionalGroupModuleMappingId(functionalGroupModuleMappingUuid));

        if (functionalGroupModuleMappingOptional.isPresent()) {
            return new ResponseEntity<>(functionalGroupModuleMappingOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<FunctionalGroupModuleMappingEntity>> findAllFunctionalGroupModuleMappings() {
//        log.info("Inside findAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingController");
        try {
            List<FunctionalGroupModuleMappingEntity> functionalGroupModuleMappings = new ArrayList<FunctionalGroupModuleMappingEntity>();
            functionalGroupModuleMappings.addAll(functionalGroupModuleMappingService.listAllFunctionalGroupModuleMappings());

            if (functionalGroupModuleMappings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(functionalGroupModuleMappings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{functionalGroupModuleMappingUuid}")
    public ResponseEntity<FunctionalGroupModuleMappingEntity> fullUpdateFunctionalGroupModuleMapping(@PathVariable("functionalGroupModuleMappingUuid") UUID functionalGroupModuleMappingUuid, @RequestBody FunctionalGroupModuleMappingEntity functionalGroupModuleMappingEntity) {
//        log.info("Inside fullUpdateFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingController");
        Optional<FunctionalGroupModuleMappingEntity> functionalGroupModuleMappingOptional = Optional.ofNullable(functionalGroupModuleMappingService.findByFunctionalGroupModuleMappingId(functionalGroupModuleMappingUuid));

        if (functionalGroupModuleMappingOptional.isPresent()) {
            functionalGroupModuleMappingEntity.setFunctionalGroupModuleMappingUuid(functionalGroupModuleMappingUuid);
            return new ResponseEntity<>(functionalGroupModuleMappingService.saveFunctionalGroupModuleMapping(functionalGroupModuleMappingEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{functionalGroupModuleMappingUuid}")
    public ResponseEntity<FunctionalGroupModuleMappingEntity> partialUpdateFunctionalGroupModuleMapping(@PathVariable("functionalGroupModuleMappingUuid") UUID functionalGroupModuleMappingUuid, @RequestBody FunctionalGroupModuleMappingEntity functionalGroupModuleMappingEntity) {
//        log.info("Inside partialUpdateFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingController");
        Optional<FunctionalGroupModuleMappingEntity> functionalGroupModuleMappingOptional = Optional.ofNullable(functionalGroupModuleMappingService.findByFunctionalGroupModuleMappingId(functionalGroupModuleMappingUuid));

        if (functionalGroupModuleMappingOptional.isPresent()) {
            functionalGroupModuleMappingEntity.setFunctionalGroupModuleMappingUuid(functionalGroupModuleMappingUuid);
            return new ResponseEntity<>(functionalGroupModuleMappingService.saveFunctionalGroupModuleMapping(functionalGroupModuleMappingEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{functionalGroupModuleMappingUuid}")
    public ResponseEntity<HttpStatus> deleteFunctionalGroupModuleMapping(@PathVariable("functionalGroupModuleMappingUuid") UUID functionalGroupModuleMappingUuid) {
//        log.info("Inside deleteFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingController");
        try {
            functionalGroupModuleMappingService.deleteFunctionalGroupModuleMapping(functionalGroupModuleMappingUuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllFunctionalGroupModuleMappings() {
//        log.info("Inside deleteAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingController");
        try {
            functionalGroupModuleMappingService.deleteAllFunctionalGroupModuleMappings();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.ModuleEntity;
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
@RequestMapping("/api/v1/user/modules")
@Slf4j
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("")
    public ResponseEntity<ModuleEntity> saveModule(@RequestBody ModuleEntity moduleEntity) {
//        log.info("Inside saveModule method of ModuleController");
        try {
            ModuleEntity _module = moduleService.saveModule(moduleEntity);
            return new ResponseEntity<>(_module, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<ModuleEntity> findByModuleId(@PathVariable("moduleId") Long moduleId) {
//        log.info("Inside findByModuleId method of ModuleController");
        Optional<ModuleEntity> moduleOptional = Optional.ofNullable(moduleService.findByModuleId(moduleId));

        if (moduleOptional.isPresent()) {
            return new ResponseEntity<>(moduleOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ModuleEntity>> findAllModules() {
//        log.info("Inside findAllModules method of ModuleController");
        try {
            List<ModuleEntity> modules = new ArrayList<ModuleEntity>();
            modules.addAll(moduleService.listAllModules());

            if (modules.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(modules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{moduleId}")
    public ResponseEntity<ModuleEntity> fullUpdateModule(@PathVariable("moduleId") Long moduleId, @RequestBody ModuleEntity moduleEntity) {
//        log.info("Inside fullUpdateModule method of ModuleController");
        Optional<ModuleEntity> moduleOptional = Optional.ofNullable(moduleService.findByModuleId(moduleId));

        if (moduleOptional.isPresent()) {
            moduleEntity.setModuleId(moduleId);
            return new ResponseEntity<>(moduleService.saveModule(moduleEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{moduleId}")
    public ResponseEntity<ModuleEntity> partialUpdateModule(@PathVariable("moduleId") Long moduleId, @RequestBody ModuleEntity moduleEntity) {
//        log.info("Inside partialUpdateModule method of ModuleController");
        Optional<ModuleEntity> moduleOptional = Optional.ofNullable(moduleService.findByModuleId(moduleId));

        if (moduleOptional.isPresent()) {
            moduleEntity.setModuleId(moduleId);
            return new ResponseEntity<>(moduleService.saveModule(moduleEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{moduleId}")
    public ResponseEntity<HttpStatus> deleteModule(@PathVariable("moduleId") Long moduleId) {
//        log.info("Inside deleteModule method of ModuleController");
        try {
            moduleService.deleteModule(moduleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllModules() {
//        log.info("Inside deleteAllModules method of ModuleController");
        try {
            moduleService.deleteAllModules();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

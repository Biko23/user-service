package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.ModuleEntity;
import com.flyhub.saccox.userservice.repository.ModuleRepository;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public ModuleEntity saveModule(ModuleEntity adminLookupAddressTypeEntity) {
//        log.info("Inside saveModule method of ModuleService");
        return moduleRepository.save(adminLookupAddressTypeEntity);
    }

    public ModuleEntity findByModuleId(UUID moduleUuid) {
//        log.info("Inside findByModuleId method of ModuleService");
        return moduleRepository.findByModuleUuid(moduleUuid);
    }

    public List<ModuleEntity> listAllModules() {
//        log.info("Inside listAllModules method of ModuleService");
        return moduleRepository.findAll();
    }

    public void deleteModule(UUID moduleUuid) {
//        log.info("Inside deleteModule method of ModuleService");
        moduleRepository.deleteById(moduleUuid);
    }

    public void deleteAllModules() {
//        log.info("Inside deleteAllModules method of ModuleService");
        moduleRepository.deleteAll();
    }

}

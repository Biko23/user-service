package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleTypeEntity;
import com.flyhub.saccox.userservice.repository.FunctionalGroupModuleTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FunctionalGroupModuleTypeService {

    @Autowired
    private FunctionalGroupModuleTypeRepository functionalGroupModuleTypeRepository;

    public FunctionalGroupModuleTypeEntity saveFunctionalGroupModuleType(FunctionalGroupModuleTypeEntity functionalGroupModuleTypeEntity) {
        log.info("Inside saveFunctionalGroupModuleType method of FunctionalGroupModuleTypeService");
        return functionalGroupModuleTypeRepository.save(functionalGroupModuleTypeEntity);
    }

    public FunctionalGroupModuleTypeEntity findByFunctionalGroupModuleTypeId(Long moduleId) {
        log.info("Inside findByFunctionalGroupModuleTypeId method of FunctionalGroupModuleTypeService");
        return functionalGroupModuleTypeRepository.findByFunctionalGroupModuleTypeId(moduleId);
    }

    public List<FunctionalGroupModuleTypeEntity> listAllFunctionalGroupModuleTypes() {
        log.info("Inside listAllFunctionalGroupModuleTypes method of FunctionalGroupModuleTypeService");
        return functionalGroupModuleTypeRepository.findAll();
    }

    public void deleteFunctionalGroupModuleType(Long moduleId) {
        log.info("Inside deleteFunctionalGroupModuleType method of FunctionalGroupModuleTypeService");
        functionalGroupModuleTypeRepository.deleteById(moduleId);
    }

    public void deleteAllFunctionalGroupModuleTypes() {
        log.info("Inside deleteAllFunctionalGroupModuleTypes method of FunctionalGroupModuleTypeService");
        functionalGroupModuleTypeRepository.deleteAll();
    }

}

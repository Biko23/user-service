package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingEntity;
import com.flyhub.saccox.userservice.repository.FunctionalGroupModuleMappingRepository;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FunctionalGroupModuleMappingService {

    @Autowired
    private FunctionalGroupModuleMappingRepository functionalGroupModuleMappingRepository;

    public FunctionalGroupModuleMappingEntity saveFunctionalGroupModuleMapping(FunctionalGroupModuleMappingEntity functionalGroupModuleMappingEntity) {
//        log.info("Inside saveFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingService");
        return functionalGroupModuleMappingRepository.save(functionalGroupModuleMappingEntity);
    }

    public FunctionalGroupModuleMappingEntity findByFunctionalGroupModuleMappingId(Long functionalGroupModuleMappingId) {
//        log.info("Inside findByFunctionalGroupModuleMappingId method of FunctionalGroupModuleMappingService");
        return functionalGroupModuleMappingRepository.findByFunctionalGroupModuleMappingId(functionalGroupModuleMappingId);
    }

    public List<FunctionalGroupModuleMappingEntity> listAllFunctionalGroupModuleMappings() {
//        log.info("Inside listAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingService");
        return functionalGroupModuleMappingRepository.findAll();
    }

    public void deleteFunctionalGroupModuleMapping(Long functionalGroupModuleMappingId) {
//        log.info("Inside deleteFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingService");
        functionalGroupModuleMappingRepository.deleteById(functionalGroupModuleMappingId);
    }

    public void deleteAllFunctionalGroupModuleMappings() {
//        log.info("Inside deleteAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingService");
        functionalGroupModuleMappingRepository.deleteAll();
    }

}

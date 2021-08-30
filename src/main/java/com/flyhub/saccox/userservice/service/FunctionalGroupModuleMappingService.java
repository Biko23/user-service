package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingEntity;
import com.flyhub.saccox.userservice.repository.FunctionalGroupModuleMappingRepository;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FunctionalGroupModuleMappingService {

    @Autowired
    private FunctionalGroupModuleMappingRepository functionalGroupModuleMappingRepository;

    public FunctionalGroupModuleMappingEntity saveFunctionalGroupModuleMapping(FunctionalGroupModuleMappingEntity functionalGroupModuleMappingEntity) {
//        log.info("Inside saveFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingService");
        return functionalGroupModuleMappingRepository.save(functionalGroupModuleMappingEntity);
    }

    public FunctionalGroupModuleMappingEntity findByFunctionalGroupModuleMappingId(UUID functionalGroupModuleMappingUuid) {
//        log.info("Inside findByFunctionalGroupModuleMappingId method of FunctionalGroupModuleMappingService");
        return functionalGroupModuleMappingRepository.findByFunctionalGroupModuleMappingUuid(functionalGroupModuleMappingUuid);
    }

    public List<FunctionalGroupModuleMappingEntity> listAllFunctionalGroupModuleMappings() {
//        log.info("Inside listAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingService");
        return functionalGroupModuleMappingRepository.findAll();
    }

    public void deleteFunctionalGroupModuleMapping(UUID functionalGroupModuleMappingUuid) {
//        log.info("Inside deleteFunctionalGroupModuleMapping method of FunctionalGroupModuleMappingService");
        functionalGroupModuleMappingRepository.deleteById(functionalGroupModuleMappingUuid);
    }

    public void deleteAllFunctionalGroupModuleMappings() {
//        log.info("Inside deleteAllFunctionalGroupModuleMappings method of FunctionalGroupModuleMappingService");
        functionalGroupModuleMappingRepository.deleteAll();
    }

}

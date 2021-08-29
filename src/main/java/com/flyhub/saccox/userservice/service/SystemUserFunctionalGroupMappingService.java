package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.SystemUserFunctionalGroupMappingEntity;
import com.flyhub.saccox.userservice.repository.SystemUserFunctionalGroupMappingRepository;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SystemUserFunctionalGroupMappingService {

    @Autowired
    private SystemUserFunctionalGroupMappingRepository systemUserFunctionalGroupMappingRepository;

    public SystemUserFunctionalGroupMappingEntity saveSystemUserFunctionalGroupMapping(SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMappingEntity) {
//        log.info("Inside saveSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingService");
        return systemUserFunctionalGroupMappingRepository.save(systemUserFunctionalGroupMappingEntity);
    }

    public SystemUserFunctionalGroupMappingEntity findBySystemUserFunctionalGroupMappingId(UUID systemUserFunctionalGroupMappingUuid) {
//        log.info("Inside findBySystemUserFunctionalGroupMappingId method of SystemUserFunctionalGroupMappingService");
        return systemUserFunctionalGroupMappingRepository.findBySystemUserFunctionalGroupMappingUuid(systemUserFunctionalGroupMappingUuid);
    }

    public List<SystemUserFunctionalGroupMappingEntity> listAllSystemUserFunctionalGroupMappings() {
//        log.info("Inside listAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingService");
        return systemUserFunctionalGroupMappingRepository.findAll();
    }

    public void deleteSystemUserFunctionalGroupMapping(UUID systemUserFunctionalGroupMappingUuid) {
//        log.info("Inside deleteSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingService");
        systemUserFunctionalGroupMappingRepository.deleteById(systemUserFunctionalGroupMappingUuid);
    }

    public void deleteAllSystemUserFunctionalGroupMappings() {
//        log.info("Inside deleteAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingService");
        systemUserFunctionalGroupMappingRepository.deleteAll();
    }

}

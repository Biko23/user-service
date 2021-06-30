package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.SystemUserFunctionalGroupMappingEntity;
import com.flyhub.saccox.userservice.repository.SystemUserFunctionalGroupMappingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SystemUserFunctionalGroupMappingService {

    @Autowired
    private SystemUserFunctionalGroupMappingRepository systemUserFunctionalGroupMappingRepository;

    public SystemUserFunctionalGroupMappingEntity saveSystemUserFunctionalGroupMapping(SystemUserFunctionalGroupMappingEntity systemUserFunctionalGroupMappingEntity) {
        log.info("Inside saveSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingService");
        return systemUserFunctionalGroupMappingRepository.save(systemUserFunctionalGroupMappingEntity);
    }

    public SystemUserFunctionalGroupMappingEntity findBySystemUserFunctionalGroupMappingId(Long systemUserFunctionalGroupMappingEntity) {
        log.info("Inside findBySystemUserFunctionalGroupMappingId method of SystemUserFunctionalGroupMappingService");
        return systemUserFunctionalGroupMappingRepository.findBySystemUserFunctionalGroupMappingId(systemUserFunctionalGroupMappingEntity);
    }

    public List<SystemUserFunctionalGroupMappingEntity> listAllSystemUserFunctionalGroupMappings() {
        log.info("Inside listAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingService");
        return systemUserFunctionalGroupMappingRepository.findAll();
    }

    public void deleteSystemUserFunctionalGroupMapping(Long systemUserFunctionalGroupMappingEntity) {
        log.info("Inside deleteSystemUserFunctionalGroupMapping method of SystemUserFunctionalGroupMappingService");
        systemUserFunctionalGroupMappingRepository.deleteById(systemUserFunctionalGroupMappingEntity);
    }

    public void deleteAllSystemUserFunctionalGroupMappings() {
        log.info("Inside deleteAllSystemUserFunctionalGroupMappings method of SystemUserFunctionalGroupMappingService");
        systemUserFunctionalGroupMappingRepository.deleteAll();
    }

}

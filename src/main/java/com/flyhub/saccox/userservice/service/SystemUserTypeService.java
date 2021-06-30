package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.SystemUserTypeEntity;
import com.flyhub.saccox.userservice.repository.SystemUserTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SystemUserTypeService {

    @Autowired
    private SystemUserTypeRepository systemUserTypeRepository;

    public SystemUserTypeEntity saveSystemUserType(SystemUserTypeEntity systemUserTypeEntity) {
        log.info("Inside saveSystemUserType method of SystemUserTypeService");
        return systemUserTypeRepository.save(systemUserTypeEntity);
    }

    public SystemUserTypeEntity findBySystemUserTypeId(Long systemUserTypeId) {
        log.info("Inside findBySystemUserTypeId method of SystemUserTypeService");
        return systemUserTypeRepository.findBySystemUserTypeId(systemUserTypeId);
    }

    public List<SystemUserTypeEntity> listAllSystemUserTypes() {
        log.info("Inside listAllSystemUserTypes method of SystemUserTypeService");
        return systemUserTypeRepository.findAll();
    }

    public void deleteSystemUserType(Long systemUserTypeId) {
        log.info("Inside deleteSystemUserType method of SystemUserTypeService");
        systemUserTypeRepository.deleteById(systemUserTypeId);
    }

    public void deleteAllSystemUserTypes() {
        log.info("Inside deleteAllSystemUserTypes method of SystemUserTypeService");
        systemUserTypeRepository.deleteAll();
    }

}

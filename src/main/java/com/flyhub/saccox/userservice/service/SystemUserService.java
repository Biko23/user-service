package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.repository.SystemUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    public SystemUserEntity saveSystemUser(SystemUserEntity systemUserEntity) {
        log.info("Inside saveSystemUser method of SystemUserService");
        return systemUserRepository.save(systemUserEntity);
    }

    public SystemUserEntity findBySystemUserId(Long systemUserId) {
        log.info("Inside findBySystemUserId method of SystemUserService");
        return systemUserRepository.findBySystemUserId(systemUserId);
    }

    public List<SystemUserEntity> listAllSystemUsers() {
        log.info("Inside listAllSystemUsers method of SystemUserService");
        return systemUserRepository.findAll();
    }

    public void deleteSystemUser(Long systemUserId) {
        log.info("Inside deleteSystemUser method of SystemUserService");
        systemUserRepository.deleteById(systemUserId);
    }

    public void deleteAllSystemUsers() {
        log.info("Inside deleteAllSystemUsers method of SystemUserService");
        systemUserRepository.deleteAll();
    }

}

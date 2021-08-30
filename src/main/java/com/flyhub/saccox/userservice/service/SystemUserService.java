package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.microserviceconnect.Tenant;
import com.flyhub.saccox.userservice.microserviceconnect.UserTenant;
import com.flyhub.saccox.userservice.repository.SystemUserRepository;
import com.flyhub.saccox.userservice.visualobject.VisualObject;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    public SystemUserEntity saveSystemUser(SystemUserEntity systemUserEntity) {
//        log.info("Inside saveSystemUser method of SystemUserService");
    	systemUserEntity.setSystemUserTypeIdFk(1);
        SystemUserEntity systemUser = systemUserRepository.save(systemUserEntity);
        ResponseEntity<SystemUserEntity> systemUserResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUser, SystemUserEntity.class);

        ResponseEntity<VisualObject> tokenResponse = restTemplate.postForEntity("http://localhost:9100/api/v1/auth/system-users", systemUserResponse, VisualObject.class);        
        return systemUser;
    }
    
    public UserTenant getUserBelongsToTenant(UUID systemUserUuid) {
//      log.info("Inside findBySystemUserId method of SystemUserService");
    	UserTenant ut = new UserTenant();
    	SystemUserEntity user = systemUserRepository.findBySystemUserUuid(systemUserUuid);
    	
    	Tenant tenant = restTemplate.getForObject("http://localhost:9100/api/v1/tenants/tenants/" + user.getTenantGlobalUuid(), Tenant.class);
    	
    	ut.setSystemUserEntity(user);
    	ut.setTenant(tenant);
    	
    	return ut;
    	
    }

    public SystemUserEntity findBySystemUserId(UUID systemUserUuid) {
//        log.info("Inside findBySystemUserId method of SystemUserService");
        return systemUserRepository.findBySystemUserUuid(systemUserUuid);
    }

    public List<SystemUserEntity> listAllSystemUsers() {
//        log.info("Inside listAllSystemUsers method of SystemUserService");
        return systemUserRepository.findAll();
    }

    public void deleteSystemUser(UUID systemUserUuid) {
//        log.info("Inside deleteSystemUser method of SystemUserService");
        systemUserRepository.deleteById(systemUserUuid);
    }

    public void deleteAllSystemUsers() {
//        log.info("Inside deleteAllSystemUsers method of SystemUserService");
        systemUserRepository.deleteAll();
    }

}

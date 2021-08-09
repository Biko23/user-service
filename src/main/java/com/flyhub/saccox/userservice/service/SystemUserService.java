package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.microserviceconnect.Tenant;
import com.flyhub.saccox.userservice.microserviceconnect.UserTenant;
import com.flyhub.saccox.userservice.repository.SystemUserRepository;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        return systemUserRepository.save(systemUserEntity);
    }
    
    public UserTenant getUserBelongsToTenant(Long systemUserId) {
//      log.info("Inside findBySystemUserId method of SystemUserService");
    	UserTenant ut = new UserTenant();
    	SystemUserEntity user = systemUserRepository.findBySystemUserId(systemUserId);
    	
    	Tenant tenant = restTemplate.getForObject("http://localhost:9100/api/v1/tenants/tenants/" + user.getTenantGlobalUuid(), Tenant.class);
    	
    	ut.setSystemUserEntity(user);
    	ut.setTenant(tenant);
    	
    	return ut;
    	
    }

    public SystemUserEntity findBySystemUserId(Long systemUserId) {
//        log.info("Inside findBySystemUserId method of SystemUserService");
        return systemUserRepository.findBySystemUserId(systemUserId);
    }

    public List<SystemUserEntity> listAllSystemUsers() {
//        log.info("Inside listAllSystemUsers method of SystemUserService");
        return systemUserRepository.findAll();
    }

    public void deleteSystemUser(Long systemUserId) {
//        log.info("Inside deleteSystemUser method of SystemUserService");
        systemUserRepository.deleteById(systemUserId);
    }

    public void deleteAllSystemUsers() {
//        log.info("Inside deleteAllSystemUsers method of SystemUserService");
        systemUserRepository.deleteAll();
    }

}

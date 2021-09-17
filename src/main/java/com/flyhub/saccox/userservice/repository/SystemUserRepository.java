package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.SystemUserEntity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUserEntity, UUID> {
    SystemUserEntity findBySystemUserGlobalId(UUID systemUserGlobalId);
    @Query("SELECT u FROM SystemUserEntity u WHERE u.isStaff = 1")
    List<SystemUserEntity> findAllStaff();
    
}

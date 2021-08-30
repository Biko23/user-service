package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.SystemUserEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUserEntity, UUID> {
    SystemUserEntity findBySystemUserUuid(UUID systemUserUuid);
}

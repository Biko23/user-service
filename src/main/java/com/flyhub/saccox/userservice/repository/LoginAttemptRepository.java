package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.LoginAttemptEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttemptEntity, UUID> {
    LoginAttemptEntity findByLoginAttemptUuid(UUID loginAttemptUuid);
}

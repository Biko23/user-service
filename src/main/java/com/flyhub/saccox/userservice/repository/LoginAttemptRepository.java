package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.LoginAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttemptEntity, Long> {
    LoginAttemptEntity findByLoginAttemptId(Long loginAttemptId);
}

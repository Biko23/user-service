package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.PasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordEntity, Long> {
    PasswordEntity findByPasswordId(Long passwordId);
}

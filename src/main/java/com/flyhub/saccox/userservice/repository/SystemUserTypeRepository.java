package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.SystemUserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserTypeRepository extends JpaRepository<SystemUserTypeEntity, Long> {
    SystemUserTypeEntity findBySystemUserTypeId(Long systemUserTypeId);
}

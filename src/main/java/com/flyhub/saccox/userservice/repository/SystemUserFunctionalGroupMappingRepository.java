package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.SystemUserFunctionalGroupMappingEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserFunctionalGroupMappingRepository extends JpaRepository<SystemUserFunctionalGroupMappingEntity, UUID> {
	SystemUserFunctionalGroupMappingEntity findBySystemUserFunctionalGroupMappingGlobalId(UUID systemUserFunctionalGroupMappingGlobalId);
}

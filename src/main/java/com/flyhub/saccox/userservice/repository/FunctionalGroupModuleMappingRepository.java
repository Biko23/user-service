package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalGroupModuleMappingRepository extends JpaRepository<FunctionalGroupModuleMappingEntity, UUID> {
    FunctionalGroupModuleMappingEntity findByFunctionalGroupModuleMappingGlobalId(UUID functionalGroupModuleMappingGlobalId);
}

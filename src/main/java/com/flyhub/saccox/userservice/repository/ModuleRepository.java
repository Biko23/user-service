package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.ModuleEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {
    ModuleEntity findByModuleId(UUID moduleUuid);
}

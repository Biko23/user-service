package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, Long> {
    ModuleEntity findByModuleId(Long moduleId);
}

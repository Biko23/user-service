package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalGroupModuleTypeRepository extends JpaRepository<FunctionalGroupModuleTypeEntity, Long> {
    FunctionalGroupModuleTypeEntity findByFunctionalGroupModuleTypeId(Long functionalGroupModuleTypeId);
}

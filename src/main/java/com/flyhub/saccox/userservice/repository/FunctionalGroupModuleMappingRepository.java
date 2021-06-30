package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalGroupModuleMappingRepository extends JpaRepository<FunctionalGroupModuleMappingEntity, Long> {
    FunctionalGroupModuleMappingEntity findByFunctionalGroupModuleMappingId(Long functionalGroupModuleMappingId);
}

package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalGroupRepository extends JpaRepository<FunctionalGroupEntity, Long> {
    FunctionalGroupEntity findByFunctionalGroupId(Long functionalGroupId);
}

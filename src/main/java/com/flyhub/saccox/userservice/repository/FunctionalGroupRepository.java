package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalGroupRepository extends JpaRepository<FunctionalGroupEntity, UUID> {
    FunctionalGroupEntity findByFunctionalGroupGlobalId(UUID functionalGroupGlobalId);
}

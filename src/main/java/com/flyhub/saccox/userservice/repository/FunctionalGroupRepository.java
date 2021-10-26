package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalGroupRepository extends JpaRepository<FunctionalGroupEntity, UUID> {
    FunctionalGroupEntity findByFunctionalGroupGlobalId(UUID functionalGroupGlobalId);
    FunctionalGroupEntity findByNameContains(String name);
    @Query(value="SELECT f.* FROM user_functional_group AS f WHERE f.is_default=0", nativeQuery = true)
    List<FunctionalGroupEntity> findAllAddedFunctionalGroups();
}

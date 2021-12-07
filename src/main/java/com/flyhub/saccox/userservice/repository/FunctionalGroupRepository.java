package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalGroupRepository extends JpaRepository<FunctionalGroupEntity, UUID> {
    @Query(value = "SELECT * FROM user_functional_group fg WHERE fg.tenant_global_id = :tenantGlobalId AND fg.functional_group_global_id = :functionalGroupGlobalId", nativeQuery = true)
    FunctionalGroupEntity findByFunctionalGroupGlobalId(UUID tenantGlobalId, UUID functionalGroupGlobalId);
    @Query(value="SELECT * FROM user_functional_group fg WHERE fg.tenant_global_id = :tenantGlobalId AND fg.is_default=0", nativeQuery = true)
    List<FunctionalGroupEntity> findAllAddedFunctionalGroups(UUID tenantGlobalId);
    @Query(value="SELECT * FROM user_functional_group fg WHERE fg.tenant_global_id IS NULL AND fg.name LIKE '%' ||'nternal'||'%'", nativeQuery = true)
    FunctionalGroupEntity findFunctionalGroupByName(String name);
}

package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.TenantOnlineMembersProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TenantOnlineMembersProcedureRepository extends JpaRepository<TenantOnlineMembersProcedureEntity, UUID> {
	@Query(value="select * from user_tenant_online_members_procedure()", nativeQuery=true)
    List<TenantOnlineMembersProcedureEntity> tenantOnlineMembersProcedure();
}

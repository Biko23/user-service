package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.SystemUserEntity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUserEntity, UUID> {
    @Query(value = "SELECT * FROM user_system_user s WHERE s.tenant_global_id = :tenantGlobalId AND s.system_user_global_id = :systemUserGlobalId", nativeQuery = true)
    SystemUserEntity findBySystemUserGlobalId(UUID tenantGlobalId, UUID systemUserGlobalId);

    @Query(value = "SELECT * FROM user_system_user s WHERE s.tenant_global_id = :tenantGlobalId AND s.member_global_id = :memberGlobalId", nativeQuery = true)
    SystemUserEntity findByMemberGlobalId(UUID tenantGlobalId, UUID memberGlobalId);

    @Query(value = "SELECT * FROM user_system_user s WHERE s.primary_phone = :primaryPhone OR s.primary_email = :primaryEmail", nativeQuery = true)
    SystemUserEntity findByPrimaryPhoneOrPrimaryEmail(String primaryPhone, String primaryEmail);

    @Query(value = "SELECT * FROM user_system_user s WHERE s.tenant_global_id = :tenantGlobalId", nativeQuery = true)
    List<SystemUserEntity> findAllSystemUsers(UUID tenantGlobalId);
}


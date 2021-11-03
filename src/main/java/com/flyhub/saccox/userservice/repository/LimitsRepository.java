package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.LimitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LimitsRepository extends JpaRepository<LimitsEntity, UUID> {
    LimitsEntity findByLimitGlobalId(UUID limitGlobalId);


    @Query(value = "SELECT * FROM user_limits a WHERE a.soft_delete = 0",nativeQuery = true)
    List<LimitsEntity> findAllLimits();
}

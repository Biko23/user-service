package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingProcedureEntity;
import com.flyhub.saccox.userservice.entity.UserLoginProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FunctionalGroupModuleMappingProcedureRepository extends JpaRepository<FunctionalGroupModuleMappingProcedureEntity, UUID> {
	@Query(value="select * from FunctionalGroupModuleMappingProcedure()", nativeQuery=true)
    List<FunctionalGroupModuleMappingProcedureEntity> functionalGroupModuleMappingProcedure();
}

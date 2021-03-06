package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.FunctionalGroupModuleMappingProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FunctionalGroupModuleMappingProcedureRepository extends JpaRepository<FunctionalGroupModuleMappingProcedureEntity, UUID> {
	@Query(value="select * from user_functional_group_module_mapping_procedure()", nativeQuery=true)
    List<FunctionalGroupModuleMappingProcedureEntity> functionalGroupModuleMappingProcedure();
}

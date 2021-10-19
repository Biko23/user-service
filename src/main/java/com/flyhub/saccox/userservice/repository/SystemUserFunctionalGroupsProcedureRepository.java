package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.SystemUserFunctionalGroupsProcedureEntity;
import com.flyhub.saccox.userservice.entity.UserLoginProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface SystemUserFunctionalGroupsProcedureRepository extends JpaRepository<SystemUserFunctionalGroupsProcedureEntity, UUID> {
	@Query(value="select * from SystemUserFunctionalGroupsProcedure()", nativeQuery=true)
    List<SystemUserFunctionalGroupsProcedureEntity> systemUserFunctionalGroupsProcedure();
}

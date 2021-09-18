package com.flyhub.saccox.userservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flyhub.saccox.userservice.entity.UserLoginProcedureEntity;

@Repository
public interface UserLoginProcedureRepository extends JpaRepository<UserLoginProcedureEntity, UUID> {
	@Query(value="select * from UserLoginProcedure(:username, :pass)", nativeQuery=true)
    List<UserLoginProcedureEntity> userLoginProcedure(@Param("username") String username, @Param("pass") String password);
}

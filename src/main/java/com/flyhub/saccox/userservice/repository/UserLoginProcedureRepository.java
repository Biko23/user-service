package com.flyhub.saccox.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flyhub.saccox.userservice.entity.UserLoginProcedureEntity;

@Repository
public interface UserLoginProcedureRepository extends JpaRepository<UserLoginProcedureEntity, String> {
	@Query(value="select * from UserLoginProcedure(:phone, :pass)",nativeQuery=true)
    List<UserLoginProcedureEntity> userLoginProcedure(@Param("phone") String phone1, @Param("pass") String password);
}

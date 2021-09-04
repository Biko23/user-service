package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.ResponseFilterEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseFilterRepository extends JpaRepository<ResponseFilterEntity, UUID> {
    ResponseFilterEntity findByResponseFilterGlobalId(UUID responseFilterGlobalId);
}

package com.flyhub.saccox.userservice.repository;

import com.flyhub.saccox.userservice.entity.ProfilePictureEntity;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProfilePictureRepository extends JpaRepository<ProfilePictureEntity, UUID> {
    ProfilePictureEntity findByProfilePictureGlobalId(UUID profilePictureGlobalId);
}

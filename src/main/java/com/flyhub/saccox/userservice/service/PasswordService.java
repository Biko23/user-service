package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.PasswordEntity;
import com.flyhub.saccox.userservice.repository.PasswordRepository;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public PasswordEntity savePassword(PasswordEntity passwordEntity) {
//        log.info("Inside savePassword method of PasswordService");
        return passwordRepository.save(passwordEntity);
    }

    public PasswordEntity findByPasswordId(UUID passwordUuid) {
//        log.info("Inside findByPasswordId method of PasswordService");
        return passwordRepository.findByPasswordUuid(passwordUuid);
    }

    public List<PasswordEntity> listAllPasswords() {
//        log.info("Inside listAllPasswords method of PasswordService");
        return passwordRepository.findAll();
    }

    public void deletePassword(UUID passwordUuid) {
//        log.info("Inside deletePassword method of PasswordService");
        passwordRepository.deleteById(passwordUuid);
    }

    public void deleteAllPasswords() {
//        log.info("Inside deleteAllPasswords method of PasswordService");
        passwordRepository.deleteAll();
    }

}

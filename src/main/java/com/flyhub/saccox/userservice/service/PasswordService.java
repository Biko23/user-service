package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.PasswordEntity;
import com.flyhub.saccox.userservice.repository.PasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public PasswordEntity savePassword(PasswordEntity passwordEntity) {
//        log.info("Inside savePassword method of PasswordService");
        return passwordRepository.save(passwordEntity);
    }

    public PasswordEntity findByPasswordId(Long passwordId) {
//        log.info("Inside findByPasswordId method of PasswordService");
        return passwordRepository.findByPasswordId(passwordId);
    }

    public List<PasswordEntity> listAllPasswords() {
//        log.info("Inside listAllPasswords method of PasswordService");
        return passwordRepository.findAll();
    }

    public void deletePassword(Long passwordId) {
//        log.info("Inside deletePassword method of PasswordService");
        passwordRepository.deleteById(passwordId);
    }

    public void deleteAllPasswords() {
//        log.info("Inside deleteAllPasswords method of PasswordService");
        passwordRepository.deleteAll();
    }

}

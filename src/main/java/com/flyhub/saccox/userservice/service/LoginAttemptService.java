package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.LoginAttemptEntity;
import com.flyhub.saccox.userservice.repository.LoginAttemptRepository;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoginAttemptService {

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;

    public LoginAttemptEntity saveLoginAttempt(LoginAttemptEntity loginAttemptEntity) {
//        log.info("Inside saveLoginAttempt method of LoginAttemptService");
        return loginAttemptRepository.save(loginAttemptEntity);
    }

    public LoginAttemptEntity findByLoginAttemptId(Long loginAttemptId) {
//        log.info("Inside findByLoginAttemptId method of LoginAttemptService");
        return loginAttemptRepository.findByLoginAttemptId(loginAttemptId);
    }

    public List<LoginAttemptEntity> listAllLoginAttempts() {
//        log.info("Inside listAllLoginAttempts method of LoginAttemptService");
        return loginAttemptRepository.findAll();
    }

    public void deleteLoginAttempt(Long loginAttemptId) {
//        log.info("Inside deleteLoginAttempt method of LoginAttemptService");
        loginAttemptRepository.deleteById(loginAttemptId);
    }

    public void deleteAllLoginAttempts() {
//        log.info("Inside deleteAllLoginAttempts method of LoginAttemptService");
        loginAttemptRepository.deleteAll();
    }

}

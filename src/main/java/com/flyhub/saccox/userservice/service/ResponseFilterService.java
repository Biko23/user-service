package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.ResponseFilterEntity;
import com.flyhub.saccox.userservice.repository.ResponseFilterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResponseFilterService {

    @Autowired
    private ResponseFilterRepository responseFilterRepository;

    public ResponseFilterEntity saveResponseFilter(ResponseFilterEntity responseFilterEntity) {
        log.info("Inside saveResponse method of ResponseService");
        return responseFilterRepository.save(responseFilterEntity);
    }

    public ResponseFilterEntity findByResponseFilterId(Long responseFilterId) {
        log.info("Inside findByResponseId method of ResponseService");
        return responseFilterRepository.findByResponseFilterId(responseFilterId);
    }

    public List<ResponseFilterEntity> listAllResponseFilters() {
        log.info("Inside listAllResponses method of ResponseService");
        return responseFilterRepository.findAll();
    }

    public void deleteResponseFilter(Long responseFilterId) {
        log.info("Inside deleteResponse method of ResponseService");
        responseFilterRepository.deleteById(responseFilterId);
    }

    public void deleteAllResponseFilters() {
        log.info("Inside deleteAllResponses method of ResponseService");
        responseFilterRepository.deleteAll();
    }

}

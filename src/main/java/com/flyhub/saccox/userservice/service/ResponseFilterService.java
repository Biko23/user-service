package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.ResponseFilterEntity;
import com.flyhub.saccox.userservice.repository.ResponseFilterRepository;
import com.flyhub.saccox.userservice.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ResponseFilterService {

    @Autowired
    private ResponseFilterRepository responseFilterRepository;

    public ResponseFilterEntity saveResponseFilter(ResponseFilterEntity responseFilterEntity) {
//        log.info("Inside saveResponse method of ResponseService");
        return responseFilterRepository.save(responseFilterEntity);
    }

    public ResponseFilterEntity findByResponseFilterId(UUID responseFilterUuid) {
//        log.info("Inside findByResponseId method of ResponseService");
        return responseFilterRepository.findByResponseFilterUuid(responseFilterUuid);
    }

    public List<ResponseFilterEntity> listAllResponseFilters() {
//        log.info("Inside listAllResponses method of ResponseService");
        return responseFilterRepository.findAll();
    }

    public void deleteResponseFilter(UUID responseFilterUuid) {
//        log.info("Inside deleteResponse method of ResponseService");
        responseFilterRepository.deleteById(responseFilterUuid);
    }

    public void deleteAllResponseFilters() {
//        log.info("Inside deleteAllResponses method of ResponseService");
        responseFilterRepository.deleteAll();
    }

}

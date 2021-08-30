package com.flyhub.saccox.userservice.controller;

import com.flyhub.saccox.userservice.entity.ResponseFilterEntity;
import com.flyhub.saccox.userservice.service.ResponseFilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/response-filters")
@Slf4j
public class ResponseFilterController {

    @Autowired
    private ResponseFilterService responseFilterService;

    @PostMapping("")
    public ResponseEntity<ResponseFilterEntity> saveResponseFilter(@RequestBody ResponseFilterEntity responseFilterEntity) {
//        log.info("Inside saveResponseFilter method of ResponseFilterController");
        try {
            ResponseFilterEntity _responseFilterEntity = responseFilterService.saveResponseFilter(responseFilterEntity);
            return new ResponseEntity<>(_responseFilterEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{responseFilterUuid}")
    public ResponseEntity<ResponseFilterEntity> findByResponseFilterId(@PathVariable("responseFilterUuid") UUID responseFilterUuid) {
//        log.info("Inside findByResponseFilterId method of ResponseFilterController");
        Optional<ResponseFilterEntity> responseFilterOptional = Optional.ofNullable(responseFilterService.findByResponseFilterId(responseFilterUuid));

        if (responseFilterOptional.isPresent()) {
            return new ResponseEntity<>(responseFilterOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseFilterEntity>> findAllResponseFilters() {
//        log.info("Inside findAllResponseFilters method of ResponseFilterController");
        try {
            List<ResponseFilterEntity> responseFilters = new ArrayList<ResponseFilterEntity>();
            responseFilters.addAll(responseFilterService.listAllResponseFilters());

            if (responseFilters.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(responseFilters, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{responseFilterUuid}")
    public ResponseEntity<ResponseFilterEntity> fullUpdateResponseFilter(@PathVariable("responseFilterUuid") UUID responseFilterUuid, @RequestBody ResponseFilterEntity responseFilterEntity) {
//        log.info("Inside fullUpdateResponseFilter method of ResponseFilterController");
        Optional<ResponseFilterEntity> responseFilterOptional = Optional.ofNullable(responseFilterService.findByResponseFilterId(responseFilterUuid));

        if (responseFilterOptional.isPresent()) {
            responseFilterEntity.setResponseFilterUuid(responseFilterUuid);
            return new ResponseEntity<>(responseFilterService.saveResponseFilter(responseFilterEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{responseFilterUuid}")
    public ResponseEntity<ResponseFilterEntity> partialUpdateResponseFilter(@PathVariable("responseFilterUuid") UUID responseFilterUuid, @RequestBody ResponseFilterEntity responseFilterEntity) {
//        log.info("Inside partialUpdateResponseFilter method of ResponseFilterController");
        Optional<ResponseFilterEntity> responseFilterOptional = Optional.ofNullable(responseFilterService.findByResponseFilterId(responseFilterUuid));

        if (responseFilterOptional.isPresent()) {
            responseFilterEntity.setResponseFilterUuid(responseFilterUuid);
            return new ResponseEntity<>(responseFilterService.saveResponseFilter(responseFilterEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{responseFilterUuid}")
    public ResponseEntity<HttpStatus> deleteResponseFilter(@PathVariable("responseFilterUuid") UUID responseFilterUuid) {
//        log.info("Inside deleteResponseFilter method of ResponseFilterController");
        try {
            responseFilterService.deleteResponseFilter(responseFilterUuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllResponseFilters() {
//        log.info("Inside deleteAllResponseFilters method of ResponseFilterController");
        try {
            responseFilterService.deleteAllResponseFilters();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

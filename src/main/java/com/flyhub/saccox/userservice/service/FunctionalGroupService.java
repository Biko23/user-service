package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.visualobject.VisualObject;
import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import com.flyhub.saccox.userservice.entity.SystemUserEntity;
import com.flyhub.saccox.userservice.exception.*;
import com.flyhub.saccox.userservice.repository.FunctionalGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.flyhub.saccox.userservice.visualobject.VisualObject;

@Service
@Slf4j
public class FunctionalGroupService {

    @Autowired
    private FunctionalGroupRepository functionalGroupRepository;

    public FunctionalGroupEntity saveFunctionalGroup(FunctionalGroupEntity functionalGroupEntity) {
//        log.info("Inside saveFunctionalGroup method of FunctionalGroupService");
//        ResponseEntity<VisualObject> systemUserResponse = restTemplate.getForEntity("http://localhost:9100/api/v1/auth/authorise/" + account.getChartOfAccountsSubCategoryId(), VisualObject.class);

        return functionalGroupRepository.save(functionalGroupEntity);
    }

    public FunctionalGroupEntity findByFunctionalGroupId(UUID functionalGroupUuid) {
//        log.info("Inside findByFunctionalGroupId method of FunctionalGroupService");
        return functionalGroupRepository.findByFunctionalGroupUuid(functionalGroupUuid);
    }

    public List<FunctionalGroupEntity> listAllFunctionalGroups() {
//        log.info("Inside listAllFunctionalGroups method of FunctionalGroupService");
        return functionalGroupRepository.findAll();
    }

    public void deleteFunctionalGroup(UUID functionalGroupUuid) {
//        log.info("Inside deleteFunctionalGroup method of FunctionalGroupService");
        functionalGroupRepository.deleteById(functionalGroupUuid);
    }

    public void deleteAllFunctionalGroups() {
//        log.info("Inside deleteAllFunctionalGroups method of FunctionalGroupService");
        functionalGroupRepository.deleteAll();
    }

}

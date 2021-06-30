package com.flyhub.saccox.userservice.service;

import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import com.flyhub.saccox.userservice.repository.FunctionalGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FunctionalGroupService {

    @Autowired
    private FunctionalGroupRepository functionalGroupRepository;

    public FunctionalGroupEntity saveFunctionalGroup(FunctionalGroupEntity functionalGroupEntity) {
        log.info("Inside saveFunctionalGroup method of FunctionalGroupService");
        return functionalGroupRepository.save(functionalGroupEntity);
    }

    public FunctionalGroupEntity findByFunctionalGroupId(Long functionalGroupId) {
        log.info("Inside findByFunctionalGroupId method of FunctionalGroupService");
        return functionalGroupRepository.findByFunctionalGroupId(functionalGroupId);
    }

    public List<FunctionalGroupEntity> listAllFunctionalGroups() {
        log.info("Inside listAllFunctionalGroups method of FunctionalGroupService");
        return functionalGroupRepository.findAll();
    }

    public void deleteFunctionalGroup(Long functionalGroupId) {
        log.info("Inside deleteFunctionalGroup method of FunctionalGroupService");
        functionalGroupRepository.deleteById(functionalGroupId);
    }

    public void deleteAllFunctionalGroups() {
        log.info("Inside deleteAllFunctionalGroups method of FunctionalGroupService");
        functionalGroupRepository.deleteAll();
    }

}

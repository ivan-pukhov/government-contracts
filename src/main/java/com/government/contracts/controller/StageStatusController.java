package com.government.contracts.controller;

import com.government.contracts.entity.StageStatus;
import com.government.contracts.service.CrudService;
import com.government.contracts.service.StageStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stageStatus")
public class StageStatusController extends AbstractCrudController<StageStatus, Long> {

    @Autowired
    private StageStatusService stageStatusService;

    @Override
    protected CrudService<StageStatus, Long> getCrudService() {
        return stageStatusService;
    }
}

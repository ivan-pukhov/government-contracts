package com.government.contracts.controller;

import com.government.contracts.model.StageStatus;
import com.government.contracts.repository.StageStatusRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stageStatus")
public class StageStatusController extends AbstractCrudController<StageStatus, Long> {
    public StageStatusController(StageStatusRepository repository) {
        super(repository);
    }
}

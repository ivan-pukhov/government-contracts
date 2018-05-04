package com.government.contracts.controller;

import com.government.contracts.model.Stage;
import com.government.contracts.repository.StageRepository;
import com.government.contracts.service.StageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stage")
public class StageController extends AbstractCrudController<Stage, Long>{
    public StageController(StageService stageService) {
        super(stageService);
    }
}

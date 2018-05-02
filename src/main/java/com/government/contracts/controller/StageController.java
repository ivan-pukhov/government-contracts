package com.government.contracts.controller;

import com.government.contracts.model.Stage;
import com.government.contracts.repository.StageRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stage")
public class StageController extends AbstractCrudController<Stage, Long>{
    public StageController(StageRepository repository) {
        super(repository);
    }
}

package com.government.contracts.controller;

import com.government.contracts.entity.Act;
import com.government.contracts.service.ActService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("act")
public class ActController extends AbstractCrudController<Act, Long> {
    public ActController(ActService actService) {
        super(actService);
    }
}

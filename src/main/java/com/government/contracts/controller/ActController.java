package com.government.contracts.controller;

import com.government.contracts.entity.Act;
import com.government.contracts.service.ActService;
import com.government.contracts.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("act")
public class ActController extends AbstractCrudController<Act, Long> {
    @Autowired
    private ActService actService;

    @Override
    protected CrudService<Act, Long> getCrudService() {
        return null;
    }
}

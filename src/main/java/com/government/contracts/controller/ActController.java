package com.government.contracts.controller;

import com.government.contracts.model.Act;
import com.government.contracts.repository.ActRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("act")
public class ActController extends AbstractCrudController<Act, Long> {
    public ActController(ActRepository repository) {
        super(repository);
    }
}

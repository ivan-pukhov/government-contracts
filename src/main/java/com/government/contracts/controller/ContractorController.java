package com.government.contracts.controller;

import com.government.contracts.entity.Contractor;
import com.government.contracts.service.ContractorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contractor")
public class ContractorController extends AbstractCrudController<Contractor, Long>{
    public ContractorController(ContractorService service) {
        super(service);
    }
}

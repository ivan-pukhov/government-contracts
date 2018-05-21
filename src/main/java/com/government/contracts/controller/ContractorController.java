package com.government.contracts.controller;

import com.government.contracts.entity.Contractor;
import com.government.contracts.service.ContractorService;
import com.government.contracts.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contractor")
public class ContractorController extends AbstractCrudController<Contractor, Long>{

    @Autowired
    private ContractorService contractorService;

    @Override
    protected CrudService<Contractor, Long> getCrudService() {
        return contractorService;
    }
}

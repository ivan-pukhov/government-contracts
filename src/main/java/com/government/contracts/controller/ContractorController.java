package com.government.contracts.controller;

import com.government.contracts.model.Contractor;
import com.government.contracts.repository.ContractorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contractor")
public class ContractorController extends AbstractCrudController<Contractor, Long>{
    public ContractorController(ContractorRepository repository) {
        super(repository);
    }
}
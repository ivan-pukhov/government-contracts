package com.government.contracts.controller;

import com.government.contracts.model.Contract;
import com.government.contracts.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContractController {

    @Autowired
    private ContractService contractService;

    @RequestMapping("/info")
    public String info() {
        return "Contract controller info";
    }

    @RequestMapping("/findByName/{name}")
    public List<Contract> findByName(@PathVariable("name") String name) {
        return contractService.findByName(name);
    }

}

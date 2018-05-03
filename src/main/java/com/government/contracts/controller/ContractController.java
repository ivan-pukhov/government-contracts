package com.government.contracts.controller;

import com.government.contracts.dto.contract.ContractDto;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.model.Contract;
import com.government.contracts.repository.ContractRepository;
import com.government.contracts.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contract")
public class ContractController extends AbstractCrudController<Contract, Long> {
    public ContractController(ContractRepository repository) {
        super(repository);
    }

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

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public List<ContractDto> findContracts(@RequestBody ContractFilterParams params) {
        return contractService.findContracts(params);
    }

}

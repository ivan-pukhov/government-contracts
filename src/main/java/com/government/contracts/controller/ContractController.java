package com.government.contracts.controller;

import com.government.contracts.dto.ResponseDto;
import com.government.contracts.dto.contract.ContractDto;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.model.Contract;
import com.government.contracts.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contract")
public class ContractController extends AbstractCrudController<Contract, Long> {

    private ContractService contractService;

    public ContractController(ContractService contractService) {
        super(contractService);
        this.contractService = contractService;
    }

    @RequestMapping("/findByName/{name}")
    public List<Contract> findByName(@PathVariable("name") String name) {
        return contractService.findByName(name);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> findContracts(@RequestBody ContractFilterParams params) {
        return createCorrectResponse(contractService.findContracts(params));
    }

}

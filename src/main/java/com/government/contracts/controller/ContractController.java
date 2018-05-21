package com.government.contracts.controller;

import com.government.contracts.dto.ResponseDto;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.entity.Contract;
import com.government.contracts.service.ContractService;
import com.government.contracts.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contract")
public class ContractController extends AbstractCrudController<Contract, Long> {

    @Autowired
    private ContractService contractService;

    @RequestMapping("/findByName/{name}")
    public List<Contract> findByName(@PathVariable("name") String name) {
        return contractService.findByName(name);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> findContracts(@RequestBody ContractFilterParams params) {
        return createCorrectResponse(contractService.findContracts(params));
    }

    @Override
    protected CrudService<Contract, Long> getCrudService() {
        return contractService;
    }
}

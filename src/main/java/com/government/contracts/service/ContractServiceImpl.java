package com.government.contracts.service;

import com.government.contracts.dto.contract.ContractDto;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.model.Contract;
import com.government.contracts.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<Contract> findByName(String name) {
        return contractRepository.findByName(name);
    }

    @Override
    public List<ContractDto> findContracts(ContractFilterParams params) {
        List<Contract> contracts = contractRepository.findContracts(params);
        return contracts.stream().map(it -> new ContractDto(it)).collect(Collectors.toList());
    }
}

package com.government.contracts.service;

import com.government.contracts.model.Contract;
import com.government.contracts.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<Contract> findByName(String name) {
        return contractRepository.findByName(name);
    }
}

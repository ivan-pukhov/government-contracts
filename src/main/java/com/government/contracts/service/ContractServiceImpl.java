package com.government.contracts.service;

import com.government.contracts.dto.contract.ContractDto;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.entity.AdditionalAgreement;
import com.government.contracts.entity.Contract;
import com.government.contracts.repository.contract.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContractServiceImpl extends CrudServiceImpl<Contract, Long> implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<Contract> findByName(String name) {
        return contractRepository.findByName(name);
    }

    @Override
    public List<ContractDto> findContracts(ContractFilterParams params) {
        Iterable<Contract> contracts = contractRepository.findContracts(params);
        return StreamSupport.stream(contracts.spliterator(), false)
                .map(it -> new ContractDto(it)).collect(Collectors.toList());
    }

    @Override
    public void updateContract(AdditionalAgreement agreement) {
        Long contractId = agreement.getContractId();
        Contract contract = findById(contractId).get();

        BigDecimal contractPrice = agreement.getContractPrice();
        if (contractPrice != null) {
            contract.setContractPrice(contractPrice);
            save(contract);
        }
    }

    @Override
    public CrudRepository<Contract, Long> getCrudRepository() {
        return contractRepository;
    }
}

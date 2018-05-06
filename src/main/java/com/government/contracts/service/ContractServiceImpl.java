package com.government.contracts.service;

import com.government.contracts.dto.contract.ContractDto;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.model.AdditionalAgreement;
import com.government.contracts.model.Contract;
import com.government.contracts.repository.contract.ContractRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl extends CrudServiceImpl<Contract, Long> implements ContractService {

    private ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        super(contractRepository);
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> findByName(String name) {
        return contractRepository.findByName(name);
    }

    @Override
    public List<ContractDto> findContracts(ContractFilterParams params) {
        List<Contract> contracts = contractRepository.findContracts(params);
        return contracts.stream().map(it -> new ContractDto(it)).collect(Collectors.toList());
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
}

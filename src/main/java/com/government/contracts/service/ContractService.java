package com.government.contracts.service;

import com.government.contracts.dto.contract.ContractDto;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.model.Contract;

import java.util.List;

public interface ContractService {

    List<Contract> findByName(String name);

    List<ContractDto> findContracts(ContractFilterParams params);
}

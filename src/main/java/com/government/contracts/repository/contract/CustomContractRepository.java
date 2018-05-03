package com.government.contracts.repository.contract;

import com.government.contracts.dto.contract.ContractDto;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.model.Contract;

import java.util.List;

public interface CustomContractRepository {
    List<Contract> findContracts(ContractFilterParams params);
}

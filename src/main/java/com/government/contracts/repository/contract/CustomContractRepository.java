package com.government.contracts.repository.contract;

import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.entity.Contract;

import java.util.List;

public interface CustomContractRepository {
    List<Contract> findContracts1(ContractFilterParams params);
}

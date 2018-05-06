package com.government.contracts.service;

import com.government.contracts.dto.contract.ContractDto;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.model.AdditionalAgreement;
import com.government.contracts.model.Contract;

import java.util.List;

public interface ContractService extends CrudService<Contract, Long> {

    List<Contract> findByName(String name);

    List<ContractDto> findContracts(ContractFilterParams params);

    void updateContract(AdditionalAgreement agreement);
}

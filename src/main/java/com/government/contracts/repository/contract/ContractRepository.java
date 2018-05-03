package com.government.contracts.repository.contract;

import com.government.contracts.model.Contract;
import com.government.contracts.repository.contract.CustomContractRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContractRepository extends PagingAndSortingRepository<Contract, Long>, CustomContractRepository {

    List<Contract> findByName(String name);
}

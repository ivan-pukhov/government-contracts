package com.government.contracts.repository;

import com.government.contracts.model.Contract;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContractRepository extends PagingAndSortingRepository<Contract, Long> {

    List<Contract> findByName(String name);
}

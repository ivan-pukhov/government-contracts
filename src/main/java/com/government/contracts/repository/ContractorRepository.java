package com.government.contracts.repository;


import com.government.contracts.model.Contractor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContractorRepository extends PagingAndSortingRepository<Contractor, Long> {
}

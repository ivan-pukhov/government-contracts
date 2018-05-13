package com.government.contracts.repository;


import com.government.contracts.entity.Act;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActRepository extends PagingAndSortingRepository<Act, Long>{
}

package com.government.contracts.repository;

import com.government.contracts.model.Stage;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface StageRepository extends PagingAndSortingRepository<Stage, Long> {
}

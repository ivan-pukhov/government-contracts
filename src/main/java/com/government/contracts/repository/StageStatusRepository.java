package com.government.contracts.repository;

import com.government.contracts.model.StageStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StageStatusRepository extends PagingAndSortingRepository<StageStatus, Long> {
}

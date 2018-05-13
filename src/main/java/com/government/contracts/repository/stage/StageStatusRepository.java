package com.government.contracts.repository.stage;

import com.government.contracts.entity.StageStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StageStatusRepository extends PagingAndSortingRepository<StageStatus, Long> {

    StageStatus findByStageCode(String stageCode);
}

package com.government.contracts.repository.stage;

import com.government.contracts.entity.Stage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface StageRepository extends PagingAndSortingRepository<Stage, Long>, JpaSpecificationExecutor {

    Iterable<Stage> findByIdIn(List<Long> ids);

    default Iterable<Stage> findContractStages(Long contractId, Long statusId) {
        return findAll(Specification.where(StageSpecifications.stageByContractId(contractId))
                .and(StageSpecifications.stageByStatusId(statusId)));
    }
}

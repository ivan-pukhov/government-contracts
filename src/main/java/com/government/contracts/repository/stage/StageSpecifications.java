package com.government.contracts.repository.stage;

import com.government.contracts.entity.Stage;
import org.springframework.data.jpa.domain.Specification;

public final class StageSpecifications {
    private StageSpecifications() {
    }

    public static Specification<Stage> stageByContractId(Long contractId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("contract").get("id"),
                contractId);
    }

    public static Specification<Stage> stageByStatusId(Long stageStatusId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("stageStatus").get("id"),
                stageStatusId);
    }

    public static Specification<Stage> stageByAdditionalAgreementId(Long additionalAgreementId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("additionalAgreementId"),
                additionalAgreementId);
    }
}

package com.government.contracts.repository.contract;

import com.government.contracts.dto.PeriodDate;
import com.government.contracts.entity.Contract;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public final class ContractSpecifications {
    private ContractSpecifications() {
    }

    public static Specification<Contract> contractByContractNumber(String contractNumber) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("contractNumber"), contractNumber);
    }

    public static Specification<Contract> contractByContractCode(String contractCode) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("contractCode"), contractCode);
    }

    public static Specification<Contract> contractByPeriodDate(PeriodDate periodDate, String fieldName) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList();
            if(periodDate != null) {
                if(periodDate.getStartPeriodDate() !=null) {
                    predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), periodDate.getStartPeriodDate()));
                }
                if(periodDate.getEndPeriodDate() !=null) {
                    predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), periodDate.getEndPeriodDate())
                    );
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}

package com.government.contracts.repository.contract;

import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.entity.Contract;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public interface ContractRepository extends PagingAndSortingRepository<Contract, Long>, JpaSpecificationExecutor, CustomContractRepository {
    List<Contract> findByName(String name);

    default Iterable<Contract> findContracts(ContractFilterParams params) {
        List<Specification> predicates = new ArrayList();
        if (!StringUtils.isEmpty(params.getContractCode())) {
            predicates.add(ContractSpecifications.contractByContractCode(params.getContractCode()));
        }
        if(!StringUtils.isEmpty(params.getContractNumber())) {
            predicates.add(ContractSpecifications.contractByContractNumber(params.getContractNumber()));
        }
        predicates.add(ContractSpecifications.contractByPeriodDate(params.getStartPeriod(), "contractStartDate"));
        predicates.add(ContractSpecifications.contractByPeriodDate(params.getEndPeriod(), "contractEndDate"));


        if(!predicates.isEmpty()) {
            Specification<Contract> result = predicates.get(0);
            for(int i = 1; i < predicates.size(); i++) {
                result = Specification.where(result).and(predicates.get(i));
            }
            return findAll(Specification.where(result));
        } else {
            return findAll();
        }

    }
}

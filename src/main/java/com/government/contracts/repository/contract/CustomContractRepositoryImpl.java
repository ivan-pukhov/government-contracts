package com.government.contracts.repository.contract;

import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.entity.Contract;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CustomContractRepositoryImpl implements CustomContractRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Contract> findContracts1(ContractFilterParams params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contract> criteriaQuery = cb.createQuery(Contract.class);
        Root<Contract> from = criteriaQuery.from(Contract.class);
        criteriaQuery.select(from);
        List<Predicate> predicates = new ArrayList<>();
        if(params.getContractCode() != null) {
            predicates.add(cb.equal(from.get("contractCode"), params.getContractCode()));
        }
        if(params.getContractNumber() != null) {
            predicates.add(cb.equal(from.get("contractNumber"), params.getContractNumber()));
        }
        Predicate[] array = new Predicate[predicates.size()];
        criteriaQuery.where(predicates.toArray(array));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}

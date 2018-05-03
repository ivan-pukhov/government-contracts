package com.government.contracts.repository;

import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.model.Contract;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class CustomContractRepositoryImpl implements CustomContractRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Contract> findContracts(ContractFilterParams params) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contract> criteriaQuery = cb.createQuery(Contract.class);
        Root<Contract> from = criteriaQuery.from(Contract.class);
        criteriaQuery.select(from);

        criteriaQuery.where(cb.equal(from.get("contractNumber"), params.getContractNumber()),
                cb.equal(from.get("contractCode"), params.getContractCode()));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}

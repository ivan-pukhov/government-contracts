package com.government.contracts.repository.payment;

import com.government.contracts.entity.Payment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>, JpaSpecificationExecutor {
    default Iterable<Payment> findByContractId(Long contractId) {
        return findAll(Specification.where(PaymentSpecifications.paymentByContractId(contractId)));
    }
}

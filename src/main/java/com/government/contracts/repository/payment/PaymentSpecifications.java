package com.government.contracts.repository.payment;


import com.government.contracts.entity.Payment;
import org.springframework.data.jpa.domain.Specification;

public final class PaymentSpecifications {
    private PaymentSpecifications() {
    }

    public static Specification<Payment> paymentByContractId(Long contractId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                        root.get("stage").get("contract").get("id"), contractId);
    }
}

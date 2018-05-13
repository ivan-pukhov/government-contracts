package com.government.contracts.repository;


import com.government.contracts.entity.PaymentType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentTypeRepository extends PagingAndSortingRepository<PaymentType, Long> {
    PaymentType findByCode(String code);
}

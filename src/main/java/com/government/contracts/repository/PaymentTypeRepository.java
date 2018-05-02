package com.government.contracts.repository;


import com.government.contracts.model.PaymentType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentTypeRepository extends PagingAndSortingRepository<PaymentType, Long>{
}

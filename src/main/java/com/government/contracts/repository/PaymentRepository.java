package com.government.contracts.repository;

import com.government.contracts.model.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {
}

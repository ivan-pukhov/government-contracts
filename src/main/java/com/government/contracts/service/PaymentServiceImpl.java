package com.government.contracts.service;

import com.government.contracts.model.Payment;
import com.government.contracts.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends CrudServiceImpl<Payment, Long> implements PaymentService {

    public PaymentServiceImpl(PaymentRepository repository) {
        super(repository);
    }
}

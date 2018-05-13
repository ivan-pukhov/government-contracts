package com.government.contracts.service;

import com.government.contracts.entity.Payment;
import com.government.contracts.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends CrudServiceImpl<Payment, Long> implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public CrudRepository<Payment, Long> getRepository() {
        return paymentRepository;
    }
}

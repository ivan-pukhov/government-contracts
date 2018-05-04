package com.government.contracts.controller;

import com.government.contracts.model.Payment;
import com.government.contracts.repository.PaymentRepository;
import com.government.contracts.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentController extends AbstractCrudController<Payment, Long> {
    @Autowired
    public PaymentController(PaymentService paymentService) {
        super(paymentService);
    }
}

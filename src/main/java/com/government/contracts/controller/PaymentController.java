package com.government.contracts.controller;

import com.government.contracts.entity.Payment;
import com.government.contracts.service.CrudService;
import com.government.contracts.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentController extends AbstractCrudController<Payment, Long> {
    @Autowired
    private PaymentService paymentService;

    @Override
    protected CrudService<Payment, Long> getCrudService() {
        return paymentService;
    }
}

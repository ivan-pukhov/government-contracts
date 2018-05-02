package com.government.contracts.controller;

import com.government.contracts.model.PaymentType;
import com.government.contracts.repository.PaymentTypeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paymentType")
public class PaymentTypeController extends AbstractCrudController<PaymentType, Long>{
    public PaymentTypeController(PaymentTypeRepository repository) {
        super(repository);
    }
}

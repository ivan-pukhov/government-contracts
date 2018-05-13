package com.government.contracts.controller;

import com.government.contracts.entity.PaymentType;
import com.government.contracts.service.PaymentTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paymentType")
public class PaymentTypeController extends AbstractCrudController<PaymentType, Long>{
    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        super(paymentTypeService);
    }
}

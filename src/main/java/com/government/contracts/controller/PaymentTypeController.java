package com.government.contracts.controller;

import com.government.contracts.entity.PaymentType;
import com.government.contracts.service.CrudService;
import com.government.contracts.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("paymentType")
public class PaymentTypeController extends AbstractCrudController<PaymentType, Long>{

    @Autowired
    private PaymentTypeService paymentTypeService;

    @Override
    protected CrudService<PaymentType, Long> getCrudService() {
        return paymentTypeService;
    }
}

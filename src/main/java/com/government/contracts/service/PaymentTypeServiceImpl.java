package com.government.contracts.service;

import com.government.contracts.entity.PaymentType;
import com.government.contracts.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeServiceImpl extends CrudServiceImpl<PaymentType, Long> implements PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    public CrudRepository<PaymentType, Long> getCrudRepository() {
        return paymentTypeRepository;
    }
}

package com.government.contracts.service;

import com.government.contracts.model.Payment;
import com.government.contracts.model.PaymentType;
import com.government.contracts.repository.PaymentTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeServiceImpl extends CrudServiceImpl<PaymentType, Long> implements PaymentTypeService {

    public PaymentTypeServiceImpl(PaymentTypeRepository repository) {
        super(repository);
    }
}

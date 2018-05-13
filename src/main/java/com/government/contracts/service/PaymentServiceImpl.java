package com.government.contracts.service;

import com.government.contracts.entity.Act;
import com.government.contracts.entity.Payment;
import com.government.contracts.entity.PaymentType;
import com.government.contracts.enums.PaymentTypeEnum;
import com.government.contracts.repository.ActRepository;
import com.government.contracts.repository.PaymentRepository;
import com.government.contracts.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentServiceImpl extends CrudServiceImpl<Payment, Long> implements PaymentService {

    private static final String ACT_TYPE = "COMPLETED_JOB";

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private ActRepository actRepository;

    @Override
    public Payment save(Payment domain) {
        Long paymentTypeId = domain.getPaymentTypeId();
        Optional<PaymentType> paymentTypeOptional = paymentTypeRepository.findById(paymentTypeId);
        if(paymentTypeOptional.isPresent()) {
            Payment storedPayment = super.save(domain);
            PaymentType paymentType = paymentTypeOptional.get();
            if (PaymentTypeEnum.COMPLETED_JOB.name().equals(paymentType.getCode())) {
                createAct(storedPayment);
            }
            return storedPayment;
        }
        throw new IllegalArgumentException("Wrong payment type id : [" + paymentTypeId + "]");

    }

    @Override
    public CrudRepository<Payment, Long> getCrudRepository() {
        return paymentRepository;
    }

    private Act createAct(Payment payment) {
        Act act = new Act();
        act.setActDate(LocalDateTime.now());
        act.setActType(ACT_TYPE);
        act.setPayment(payment);
        Act storedAct = actRepository.save(act);
        return storedAct;
    }
}

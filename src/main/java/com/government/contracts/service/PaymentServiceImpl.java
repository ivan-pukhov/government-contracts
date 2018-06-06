package com.government.contracts.service;

import com.government.contracts.dto.PaymentDto;
import com.government.contracts.entity.*;
import com.government.contracts.enums.PaymentTypeEnum;
import com.government.contracts.enums.StageStatusEnum;
import com.government.contracts.repository.ActRepository;
import com.government.contracts.repository.payment.PaymentRepository;
import com.government.contracts.repository.PaymentTypeRepository;
import com.government.contracts.repository.stage.StageRepository;
import com.government.contracts.repository.stage.StageStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class PaymentServiceImpl extends CrudServiceImpl<Payment, Long> implements PaymentService {

    private static final String ACT_TYPE = "COMPLETED_JOB";

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private ActRepository actRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageStatusRepository stageStatusRepository;

    @Override
    public Payment save(Payment domain) {
        Long paymentTypeId = domain.getPaymentType().getId();
        Optional<PaymentType> paymentTypeOptional = paymentTypeRepository.findById(paymentTypeId);
        if(paymentTypeOptional.isPresent()) {
            Long stageId = domain.getStage().getId();
            domain.setPaymentType(paymentTypeOptional.get());
            Optional<Stage> stageOpt = stageRepository.findById(stageId);
            if (stageOpt.isPresent()) {
                Payment storedPayment = super.save(domain);
                PaymentType paymentType = paymentTypeOptional.get();
                if (PaymentTypeEnum.COMPLETED_JOB.name().equals(paymentType.getCode())) {
                    createAct(storedPayment);

                    Stage stage = stageOpt.get();
                    StageStatus closed = stageStatusRepository.findByStageCode(StageStatusEnum.CLOSED.name());
                    stage.setStageStatus(closed);
                    stageRepository.save(stage);
                }
                return storedPayment;
            }
            throw new IllegalArgumentException("Wrong stage id: [" + stageId + "]");

        }
        throw new IllegalArgumentException("Wrong payment type id : [" + paymentTypeId + "]");
    }

    @Override
    public List<PaymentDto> findPaymentsByContractId(Long contractId) {
        Iterable<Payment> payments = paymentRepository.findByContractId(contractId);
        return StreamSupport.stream(payments.spliterator(), false).map(payment -> {
            PaymentDto dto = new PaymentDto();
            dto.setId(payment.getId());
            dto.setContractId(payment.getStage().getContract().getId());
            dto.setStageId(payment.getStage().getId());
            dto.setPaymentDate(payment.getPaymentDate());
            dto.setPaymentSum(payment.getPaymentSum());
            dto.setPaymentType(payment.getPaymentType());
            return dto;
        }).collect(toList());
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

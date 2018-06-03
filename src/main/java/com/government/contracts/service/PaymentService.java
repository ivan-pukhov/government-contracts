package com.government.contracts.service;

import com.government.contracts.dto.PaymentDto;
import com.government.contracts.entity.Payment;

import java.util.List;

public interface PaymentService extends CrudService<Payment, Long> {

    List<PaymentDto> findPaymentsByContractId(Long contractId);
}

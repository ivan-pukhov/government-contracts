package com.government.contracts.controller;

import com.government.contracts.dto.ResponseDto;
import com.government.contracts.entity.Payment;
import com.government.contracts.service.CrudService;
import com.government.contracts.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("payment")
public class PaymentController extends AbstractCrudController<Payment, Long> {
    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/contract/{contractId}")
    public ResponseEntity<ResponseDto> findPaymentsByContractId(@PathVariable Long contractId) {
        return createCorrectResponse(paymentService.findPaymentsByContractId(contractId));
    }

    @Override
    protected CrudService<Payment, Long> getCrudService() {
        return paymentService;
    }
}

package com.government.contracts.utils;

import com.government.contracts.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by vano on 29.4.18.
 */
public final class TestEntityFactory {
    private static final String SHORT_PREFIX = "short_";
    private static final String FULL_PREFIX = "full_";
    private static final long DATE_STEP = 2L;

    public static final BigDecimal TEST_CONTRACT_PRICE = BigDecimal.TEN;
    public static final BigDecimal TEST_PRICE = BigDecimal.ONE;
    private TestEntityFactory() {


    }

    public static final Contractor createContractor(String inn, String name) {
        Contractor contractor = new Contractor();
        contractor.setInn(inn);
        contractor.setShortName(SHORT_PREFIX + name);
        contractor.setFullName(FULL_PREFIX + name);
        return contractor;
    }

    public static final Contract createContract(String contractName, String contractNumber, String contractCode) {
        Contract contract = new Contract();
        contract.setName(contractName);
        contract.setContractNumber(contractNumber);
        contract.setContractCode(contractCode);
        contract.setContractStartDate(LocalDateTime.now());
        contract.setContractEndDate(LocalDateTime.now().plusMonths(DATE_STEP));
        contract.setContractPrice(TEST_CONTRACT_PRICE);
        return contract;
    }

    public static final AdditionalAgreement createAdditionalAgreement(Long contractId, String agreementNumber, String agreementName) {
        AdditionalAgreement additionalAgreement = new AdditionalAgreement();
        additionalAgreement.setContractPrice(TEST_CONTRACT_PRICE);
        additionalAgreement.setPrice(TEST_PRICE);
        additionalAgreement.setContractId(contractId);
        additionalAgreement.setAgreeementNumber(agreementNumber);
        additionalAgreement.setAgreementName(agreementName);
        additionalAgreement.setStartDate(LocalDateTime.now());
        additionalAgreement.setEndDate(LocalDateTime.now().plusMonths(DATE_STEP));
        additionalAgreement.setAgreementDate(LocalDateTime.now());
        return additionalAgreement;
    }

    public static final StageStatus createStageStatus(String statusName) {
        StageStatus stageStatus = new StageStatus();
        stageStatus.setStageName(statusName);
        return stageStatus;
    }

    public static final Stage createStage(Long statusId,Long contractId, String name, String stageNumber) {
        Stage stage = new Stage();
        stage.setStageName(name);
        stage.setStageStatusId(statusId);
        stage.setContractId(contractId);
        stage.setStartDate(LocalDateTime.now());
        stage.setEndDate(LocalDateTime.now().plusMonths(DATE_STEP));
        stage.setStageNumber(stageNumber);
        stage.setPrice(TEST_PRICE);
        return stage;
    }

    public static final PaymentType createPaymentType(String name) {
        PaymentType paymentType = new PaymentType();
        paymentType.setName(name);
        return paymentType;
    }

    public static final Payment createPayment(Long paymentTypeId, String stageNumber) {
        Payment payment = new Payment();
        payment.setStageNumber(stageNumber);
        payment.setPaymentTypeId(paymentTypeId);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentSum(TEST_PRICE);
        return payment;
    }

    public static final Act createAct(Long stageId, String actType, String stageNumber) {
        Act act = new Act();
        act.setActType(actType);
        act.setStageId(stageId);
        act.setStageNumber(stageNumber);
        act.setActDate(LocalDateTime.now());
        act.setStagePrice(TEST_PRICE);
        return act;
    }
}

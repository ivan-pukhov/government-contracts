package com.government.contracts.repository;

import com.government.contracts.entity.*;
import com.government.contracts.enums.PaymentTypeEnum;
import com.government.contracts.repository.contract.ContractRepository;
import com.government.contracts.repository.payment.PaymentRepository;
import com.government.contracts.repository.stage.StageRepository;
import com.government.contracts.repository.stage.StageStatusRepository;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class PaymentRepositoryTest extends AbstractRepositoryTest<Payment, Long> {

    private static final String[] TEST_STAGE_NUMBERS = {"testStageNumber1", "testStageNumber2", "testStageNumber3"};

    private static final String TEST_INN = "testInn";
    private static final String TEST_CONTRACTOR_NAME = "contractor_name";

    private static final String[] TEST_CONTRACT_NAMES = {"TestContractName1", "TestContractName2", "TestContractName3"};
    private static final String[] TEST_CONTRACT_NUMBERS = {"testContractNumber1", "testContractNumber2", "TestContractNumber3"};
    private static final String[] TEST_CONTRACT_CODES = {"testContractCode1", "testContractCode2", "TestContractCode3"};
    private static final String[] TEST_STAGE_STATUS_NAMES = {"testStatusName1", "testStatusName2", "testStatusName3"};
    private static final String[] TEST_STAGE_STATUS_CODES = {"testStatusCode1", "testStatusCode2", "testStatusCode3"};
    private static final String[] TEST_STAGE_NAMES = {"testStageName1", "testStageName2", "testStageName3"};

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private StageStatusRepository stageStatusRepository;
    @Autowired
    private StageRepository stageRepository;

    @Test
    public void testFindByContractId() {
        Contract contract1 = createContract(TEST_CONTRACT_NAMES[1], TEST_CONTRACT_NUMBERS[1], TEST_CONTRACT_CODES[1]);
        Contract contract2 = createContract(TEST_CONTRACT_NAMES[2], TEST_CONTRACT_NUMBERS[2], TEST_CONTRACT_CODES[2]);

        Stage cn1Stage = createStage(contract1, TEST_STAGE_NAMES[1], TEST_STAGE_NUMBERS[1],
                TEST_STAGE_STATUS_NAMES[1], TEST_STAGE_STATUS_CODES[1]);
        Stage cn2Stage = createStage(contract2, TEST_STAGE_NAMES[2], TEST_STAGE_NUMBERS[2],
                TEST_STAGE_STATUS_NAMES[2], TEST_STAGE_STATUS_CODES[2]);

        Payment payment11 = createPayment(PaymentTypeEnum.PREPAYMENT, cn1Stage);
        Payment storedPayment11 = paymentRepository.save(payment11);
        Payment payment12 = createPayment(PaymentTypeEnum.COMPLETED_JOB, cn1Stage);
        Payment storedPayment12 = paymentRepository.save(payment12);

        Payment payment21 = createPayment(PaymentTypeEnum.PREPAYMENT, cn2Stage);
        Payment storedPayment21 = paymentRepository.save(payment21);
        Payment payment22 = createPayment(PaymentTypeEnum.REFUND, cn2Stage);
        Payment storedPayment22 = paymentRepository.save(payment22);

        Iterable<Payment> cn1Payments = paymentRepository.findByContractId(contract1.getId());
        Iterable<Payment> cn2Payments = paymentRepository.findByContractId(contract2.getId());
        Assert.assertTrue(cn1Payments.iterator().hasNext());
        for(Payment payment : cn1Payments) {
            Assert.assertEquals(contract1.getId(), payment.getStage().getContract().getId());
            Assert.assertTrue(payment.getId().equals(storedPayment11.getId())
                    || payment.getId().equals(storedPayment12.getId()));
        }


        Assert.assertTrue(cn2Payments.iterator().hasNext());

        for(Payment payment : cn2Payments) {
            Assert.assertEquals(contract2.getId(), payment.getStage().getContract().getId());
            Assert.assertTrue(payment.getId().equals(storedPayment21.getId())
                    || payment.getId().equals(storedPayment22.getId()));
        }
    }

    @Override
    protected Payment createEntity() {
        Stage stage = createDefaultStage();
        return createPayment(PaymentTypeEnum.PREPAYMENT, stage);
    }

    private Payment createPayment(PaymentTypeEnum paymentTypeVal, Stage stage) {
        PaymentType paymentType = paymentTypeRepository.findByCode(paymentTypeVal.name());
        PaymentType storedType = paymentTypeRepository.save(paymentType);

        Assert.assertNotNull(storedType.getId());
        Payment payment = TestEntityFactory.createPayment(storedType, stage);
        return payment;
    }

    @Override
    protected CrudRepository<Payment, Long> getRepository() {
        return paymentRepository;
    }

    @Override
    protected void assertEntity(Payment entity) {
        Assert.assertEquals(TEST_STAGE_NUMBERS[0], entity.getStage().getStageNumber());
        Assert.assertNotNull(entity.getPaymentDate());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getPaymentSum());
        Assert.assertNotNull(entity.getPaymentType().getId());
    }

    private Contract createContract(String contractName, String contractNumber, String contractCode) {
        Contractor contractor = TestEntityFactory.createContractor(TEST_INN, TEST_CONTRACTOR_NAME);
        Contractor savedContractor = contractorRepository.save(contractor);
        Assert.assertNotNull(savedContractor.getId());
        Contract contract = TestEntityFactory.createContract(contractName, contractCode, contractNumber);
        contract.setContractor(savedContractor);

        Contract savedContract = contractRepository.save(contract);
        Assert.assertNotNull(savedContract.getId());
        return savedContract;
    }

    private Stage createStage(Contract contract, String stageName, String stageNumber, String stageStatusName, String stageCode) {

        StageStatus stageStatus = TestEntityFactory.createStageStatus(stageStatusName, stageCode);
        StageStatus storedStatus = stageStatusRepository.save(stageStatus);

        Stage stage = TestEntityFactory.createStage(storedStatus, contract, stageName, stageNumber);
        Stage storedStage = stageRepository.save(stage);
        return storedStage;
    }

    private Stage createDefaultStage() {
        Contract contract = createContract(TEST_CONTRACT_NAMES[0], TEST_CONTRACT_NUMBERS[0], TEST_CONTRACT_CODES[0]);

        StageStatus stageStatus = TestEntityFactory.createStageStatus(TEST_STAGE_STATUS_NAMES[0], TEST_STAGE_STATUS_CODES[0]);
        StageStatus storedStatus = stageStatusRepository.save(stageStatus);

        Stage stage = TestEntityFactory.createStage(storedStatus, contract, TEST_STAGE_NAMES[0], TEST_STAGE_NUMBERS[0]);
        Stage storedStage = stageRepository.save(stage);
        return storedStage;

    }
}

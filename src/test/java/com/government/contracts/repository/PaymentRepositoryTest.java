package com.government.contracts.repository;

import com.government.contracts.entity.*;
import com.government.contracts.enums.PaymentTypeEnum;
import com.government.contracts.repository.contract.ContractRepository;
import com.government.contracts.repository.stage.StageRepository;
import com.government.contracts.repository.stage.StageStatusRepository;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class PaymentRepositoryTest extends AbstractRepositoryTest<Payment, Long> {

    private static final String TEST_STAGE_NUMBER = "testStageNumber";
    private static final String TEST_PAYMENT_TYPE = "testPaymentType";

    private static final String TEST_INN = "testInn";
    private static final String TEST_CONTRACTOR_NAME = "contractor_name";

    private static final String TEST_CONTRACT_NAME = "TestContractName";
    private static final String TEST_CONTRACT_NUMBER = "testContractNumber";
    private static final String TEST_CONTRACT_CODE = "testContractCode";
    private static final String TEST_STAGE_STATUS_NAME = "testStatusName";
    private static final String TEST_STAGE_STATUS_CODE = "testStatusCode";
    private static final String TEST_STAGE_NAME = "testStageName";

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
    @Override
    protected Payment createEntity() {
        Stage stage = createStage();
        PaymentType paymentType = paymentTypeRepository.findByCode(PaymentTypeEnum.PREPAYMENT.name());
        PaymentType storedType = paymentTypeRepository.save(paymentType);

        Assert.assertNotNull(storedType.getId());

        Payment payment = TestEntityFactory.createPayment(storedType.getId(), stage);
        return payment;
    }

    @Override
    protected CrudRepository<Payment, Long> getRepository() {
        return paymentRepository;
    }

    @Override
    protected void assertEntity(Payment entity) {
        Assert.assertEquals(TEST_STAGE_NUMBER, entity.getStage().getStageNumber());
        Assert.assertNotNull(entity.getPaymentDate());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getPaymentSum());
        Assert.assertNotNull(entity.getPaymentTypeId());
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

    private Stage createStage() {
        Contract contract = createContract(TEST_CONTRACT_NAME, TEST_CONTRACT_NUMBER, TEST_CONTRACT_CODE);

        StageStatus stageStatus = TestEntityFactory.createStageStatus(TEST_STAGE_STATUS_NAME, TEST_STAGE_STATUS_CODE);
        StageStatus storedStatus = stageStatusRepository.save(stageStatus);

        Stage stage = TestEntityFactory.createStage(storedStatus, contract, TEST_STAGE_NAME, TEST_STAGE_NUMBER);
        Stage storedStage = stageRepository.save(stage);
        return storedStage;

    }
}

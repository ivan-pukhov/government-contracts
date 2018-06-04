package com.government.contracts.repository;

import com.government.contracts.entity.*;
import com.government.contracts.enums.PaymentTypeEnum;
import com.government.contracts.repository.contract.ContractRepository;
import com.government.contracts.repository.payment.PaymentRepository;
import com.government.contracts.repository.stage.StageRepository;
import com.government.contracts.repository.stage.StageStatusRepository;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class ActRepositoryTest extends AbstractRepositoryTest<Act, Long> {

    private static final String ACT_TYPE = "testActType";
    private static final String TEST_CONTRACTOR_NAME = "contractor_name";
    private static final String TEST_CONTRACT_NUMBER = "contractNumber";
    private static final String TEST_CONTRACT_NAME = "contractName";
    private static final String TEST_CONTRACT_CODE = "contractCode";
    private static final String TEST_STAGE_STATUS_NAME = "testStatusName";
    private static final String TEST_STAGE_STATUS_CODE = "testStatusCode";
    private static final String TEST_STAGE_NAME = "testStageName";
    private static final String TEST_STAGE_NUMBER = "testStageNumber";
    private static final String TEST_INN = "testInn";
    private static final String TEST_ACT_TYPE = "testActType";

    @Autowired
    private ActRepository actRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageStatusRepository stageStatusRepository;
    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    protected Act createEntity() {
        Contract contract = createContract();

        StageStatus stageStatus = TestEntityFactory.createStageStatus(TEST_STAGE_STATUS_NAME, TEST_STAGE_STATUS_CODE);
        StageStatus storedStatus = stageStatusRepository.save(stageStatus);

        Stage stage = TestEntityFactory.createStage(storedStatus, contract, TEST_STAGE_NAME, TEST_STAGE_NUMBER);
        Stage storedStage = stageRepository.save(stage);

        PaymentType completedType = paymentTypeRepository.findByCode(PaymentTypeEnum.COMPLETED_JOB.name());
        Payment payment = TestEntityFactory.createPayment(completedType, storedStage);
        Payment storedPayment = paymentRepository.save(payment);

        Act act = TestEntityFactory.createAct(storedPayment, TEST_ACT_TYPE, TEST_STAGE_NUMBER);
        return act;
    }

    @Override
    protected CrudRepository<Act, Long> getRepository() {
        return actRepository;
    }

    @Override
    protected void assertEntity(Act entity) {
        Assert.assertEquals(TEST_ACT_TYPE, entity.getActType());
        Assert.assertNotNull(entity.getActDate());
        Assert.assertEquals(TEST_STAGE_NUMBER, entity.getStageNumber());
        Assert.assertNotNull(entity.getPayment());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getStagePrice());
    }

    private Contract createContract() {
        Contractor contractor = TestEntityFactory.createContractor(TEST_INN, TEST_CONTRACTOR_NAME);
        Contractor savedContractor = contractorRepository.save(contractor);
        Assert.assertNotNull(savedContractor.getId());
        Contract contract = TestEntityFactory.createContract(TEST_CONTRACT_NAME, TEST_CONTRACT_NUMBER, TEST_CONTRACT_CODE);
        contract.setContractor(savedContractor);

        Contract savedContract = contractRepository.save(contract);
        Assert.assertNotNull(savedContract.getId());
        return savedContract;
    }
}

package com.government.contracts.repository;

import com.government.contracts.model.*;
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

    @Override
    protected Act createEntity() {
        Contract contract = createContract();

        StageStatus stageStatus = TestEntityFactory.createStageStatus(TEST_STAGE_STATUS_NAME);
        StageStatus storedStatus = stageStatusRepository.save(stageStatus);

        Stage stage = TestEntityFactory.createStage(storedStatus.getId(), contract.getId(), TEST_STAGE_NAME, TEST_STAGE_NUMBER);
        Stage storedStage = stageRepository.save(stage);

        Act act = TestEntityFactory.createAct(storedStage.getId(), TEST_ACT_TYPE, TEST_STAGE_NUMBER);
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
        Assert.assertNotNull(entity.getStageId());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getStagePrice());
    }

    private Contract createContract() {
        Contractor contractor = TestEntityFactory.createContractor(TEST_INN, TEST_CONTRACTOR_NAME);
        Contractor savedContractor = contractorRepository.save(contractor);
        Assert.assertNotNull(savedContractor.getId());
        Contract contract = TestEntityFactory.createContract(TEST_CONTRACT_NAME, TEST_CONTRACT_NUMBER, TEST_CONTRACT_CODE);
        contract.setContractorId(savedContractor.getId());

        Contract savedContract = contractRepository.save(contract);
        Assert.assertNotNull(savedContract.getId());
        return savedContract;
    }
}

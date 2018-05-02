package com.government.contracts.repository;

import com.government.contracts.model.Contract;
import com.government.contracts.model.Contractor;
import com.government.contracts.model.Stage;
import com.government.contracts.model.StageStatus;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class StageRepositoryTest extends AbstractRepositoryTest<Stage, Long> {

    private static final String TEST_INN = "testInn";
    private static final String TEST_CONTRACTOR_NAME = "contractor_name";
    private static final String TEST_CONTRACT_NUMBER = "contractNumber";
    private static final String TEST_CONTRACT_NAME = "contractName";
    private static final String TEST_CONTRACT_CODE = "contractCode";
    private static final String TEST_STAGE_STATUS_NAME = "testStatusName";
    private static final String TEST_STAGE_NAME = "testStageName";
    private static final String TEST_STAGE_NUMBER = "testStageNumber";

    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageStatusRepository stageStatusRepository;
    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
    private ContractRepository contractRepository;

    @Override
    protected Stage createEntity() {
        Contract contract = createContract();

        StageStatus stageStatus = TestEntityFactory.createStageStatus(TEST_STAGE_STATUS_NAME);
        StageStatus storedStatus = stageStatusRepository.save(stageStatus);

        Stage stage = TestEntityFactory.createStage(storedStatus.getId(), contract.getId(), TEST_STAGE_NAME, TEST_STAGE_NUMBER);

        return stage;
    }

    @Override
    protected CrudRepository<Stage, Long> getRepository() {
        return stageRepository;
    }

    @Override
    protected void assertEntity(Stage entity) {
        Assert.assertEquals(TEST_STAGE_NAME, entity.getStageName());
        Assert.assertNotNull(entity.getStartDate());
        Assert.assertNotNull(entity.getEndDate());
        Assert.assertNotNull(entity.getContractId());
        Assert.assertNotNull(entity.getStageStatusId());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getPrice());
        Assert.assertEquals(TEST_STAGE_NUMBER, entity.getStageNumber());
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

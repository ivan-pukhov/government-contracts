package com.government.contracts.repository.stage;

import com.government.contracts.enums.StageStatusEnum;
import com.government.contracts.entity.*;
import com.government.contracts.repository.AbstractRepositoryTest;
import com.government.contracts.repository.ContractorRepository;
import com.government.contracts.repository.contract.ContractRepository;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class StageParentInfoRepositoryTest extends AbstractRepositoryTest<StageParentInfo, Long> {

    private static final String TEST_INN = "testInn";
    private static final String TEST_CONTRACTOR_NAME = "testContractor";
    private static final String CONTRACT_NAME = "contractName";
    private static final String CONTRACT_NUMBER = CONTRACT_NAME + "_contractNumber";
    private static final String CONTRACT_CODE = CONTRACT_NAME + "_contractCode";
    private static final String STAGE_NAME = "stage_name";
    private static final String STAGE_NUMBER = STAGE_NAME + "_stage_number";
    private static final String TEST_DESCRIPTION = "testDescription";


    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageStatusRepository stageStatusRepository;
    @Autowired
    private StageParentInfoRepository stageParentInfoRepository;

    @Override
    protected StageParentInfo createEntity() {
        Contract contract = createContract();
        StageStatus deletedStatus = stageStatusRepository.findByStageCode(StageStatusEnum.DELETED.name());
        StageStatus currentStatus = stageStatusRepository.findByStageCode(StageStatusEnum.DELETED.name());

        Stage deletedStage = createStage(contract, deletedStatus);
        Stage currentStage = createStage(contract, currentStatus);

        StageParentInfo parentInfo = new StageParentInfo();
        parentInfo.setStageId(currentStage.getId());
        parentInfo.setParentStageId(deletedStage.getId());
        parentInfo.setDescription(TEST_DESCRIPTION);
        return parentInfo;
    }

    @Override
    protected CrudRepository<StageParentInfo, Long> getRepository() {
        return stageParentInfoRepository;
    }

    @Override
    protected void assertEntity(StageParentInfo entity) {
        Assert.assertNotNull(entity.getId());
        Assert.assertNotNull(entity.getParentStageId());
        Assert.assertNotNull(entity.getStageId());
        Assert.assertEquals(TEST_DESCRIPTION, entity.getDescription());
    }

    private Stage createStage(Contract contract, StageStatus stageStatus) {
        Stage stage = TestEntityFactory.createStage(stageStatus, contract, STAGE_NAME, STAGE_NUMBER);
        return stageRepository.save(stage);
    }

    private Contract createContract() {
        Contractor contractor = TestEntityFactory.createContractor(TEST_INN, TEST_CONTRACTOR_NAME);
        Contractor savedContractor = contractorRepository.save(contractor);
        Assert.assertNotNull(savedContractor.getId());
        Contract contract = TestEntityFactory.createContract(CONTRACT_NAME, CONTRACT_CODE, CONTRACT_NUMBER);
        contract.setContractor(savedContractor);

        Contract savedContract = contractRepository.save(contract);
        Assert.assertNotNull(savedContract.getId());
        return savedContract;
    }
}

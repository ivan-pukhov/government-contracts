package com.government.contracts.repository.stage;

import com.government.contracts.enums.StageStatusEnum;
import com.government.contracts.entity.*;
import com.government.contracts.repository.AbstractRepositoryTest;
import com.government.contracts.repository.AdditionalAgreementRepository;
import com.government.contracts.repository.ContractorRepository;
import com.government.contracts.repository.contract.ContractRepository;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;


public class StageRepositoryTest extends AbstractRepositoryTest<Stage, Long> {

    private static final String TEST_INN = "testInn";
    private static final String TEST_CONTRACTOR_NAME = "contractor_name";

    private static final String[] TEST_CONTRACT_NAMES = {"contractName1", "contractName2", "contractName3"};
    private static final String TEST_CONTRACT_NUMBER_POSTFIX = "_contractNumber";
    private static final String[] TEST_CONTRACT_NUMBERS = {TEST_CONTRACT_NAMES[0] + TEST_CONTRACT_NUMBER_POSTFIX,
            TEST_CONTRACT_NAMES[1] + TEST_CONTRACT_NUMBER_POSTFIX,
            TEST_CONTRACT_NAMES[2] + TEST_CONTRACT_NUMBER_POSTFIX
    };
    private static final String TEST_CONTRACT_CODE_POSTFIX = "_contractCode";
    private static final String[] TEST_CONTRACT_CODES = {TEST_CONTRACT_NAMES[0] + TEST_CONTRACT_CODE_POSTFIX,
            TEST_CONTRACT_NAMES[1] + TEST_CONTRACT_CODE_POSTFIX,
            TEST_CONTRACT_NAMES[2] + TEST_CONTRACT_NUMBER_POSTFIX
    };
    private static final String TEST_STAGE_STATUS_NAME = "testStatusName";
    private static final String TEST_STAGE_STATUS_CODE = "testStatusCode";
    private static final String[] TEST_STAGE_NAMES = {"testStageName1", "testStageName2", "testStageName3"};
    private static final String TEST_STAGE_NUMBER_POSTFIX = "_testStageNumber";
    private static final String[] TEST_STAGE_NUMBERS = {
            TEST_STAGE_NAMES[0] + TEST_STAGE_NUMBER_POSTFIX,
            TEST_STAGE_NAMES[1] + TEST_STAGE_NUMBER_POSTFIX,
            TEST_STAGE_NAMES[2] + TEST_STAGE_NUMBER_POSTFIX,
    };
    private static final String TEST_AGREEMENT_NAME = "agreementName";
    private static final String TEST_AGREEMENT_NUMBER = "agreementNumber";

    private static final Long WRONG_CONTRACT_ID = 12L;

    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageStatusRepository stageStatusRepository;
    @Autowired
    private ContractorRepository contractorRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private AdditionalAgreementRepository additionalAgreementRepository;

    @Test
    public void testSaveStageAdditionalAgreement() {
        Stage stage = createDefaultStage(true);
        Stage saved = stageRepository.save(stage);
        Assert.assertNotNull(saved.getId());

        Stage stored = stageRepository.findById(saved.getId()).get();
        assertEntity(stored);
        Assert.assertNotNull(stored.getAdditionalAgreement());
    }

    @Test
    public void testFindContractStagesByContractId() {
        Contract contract1 = createContract(TEST_CONTRACT_NAMES[1], TEST_CONTRACT_NUMBERS[1], TEST_CONTRACT_CODES[1]);

        StageStatus currentStatus = stageStatusRepository.findByStageCode(StageStatusEnum.CURRENT.name());

        Stage stage1 = TestEntityFactory.createStage(currentStatus, contract1, TEST_STAGE_NAMES[1], TEST_STAGE_NUMBERS[1]);
        stage1.setContract(contract1);
        Stage storedStage1 = stageRepository.save(stage1);
        Assert.assertNotNull(storedStage1.getId());

        Contract contract2 = createContract(TEST_CONTRACT_NAMES[2], TEST_CONTRACT_NUMBERS[2], TEST_CONTRACT_CODES[2]);

        Stage stage2 = TestEntityFactory.createStage(currentStatus, contract2, TEST_STAGE_NAMES[2], TEST_STAGE_NUMBERS[2]);
        stage2.setContract(contract2);
        Stage storedStage2 = stageRepository.save(stage2);
        Assert.assertNotNull(storedStage2.getId());

        Iterable<Stage> contract1Stages = stageRepository.findAll(StageSpecifications.stageByContractId(contract1.getId()));
        Assert.assertTrue(contract1Stages.iterator().hasNext());
        contract1Stages.forEach(stage -> Assert.assertEquals(contract1.getId(), stage.getContract().getId()));

        Iterable<Stage> contract2Stages = stageRepository.findAll(StageSpecifications.stageByContractId(contract2.getId()));
        Assert.assertTrue(contract2Stages.iterator().hasNext());
        contract2Stages.forEach(stage -> Assert.assertEquals(contract2.getId(), stage.getContract().getId()));
    }

    @Test
    public void testFindContractStagesByWrongContractId() {
        Stage stage = createDefaultStage(false);
        Stage storedStage = stageRepository.save(stage);
        Assert.assertNotNull(storedStage.getId());
        Iterable<Stage> stages = stageRepository.findAll(StageSpecifications.stageByContractId(WRONG_CONTRACT_ID));
        Assert.assertFalse(stages.iterator().hasNext());
    }

    @Test
    public void testFindContractByStageStatusAndContractId() {
        // Add stages for the first contract
        Contract contract1 = createContract(TEST_CONTRACT_NAMES[1], TEST_CONTRACT_NUMBERS[1], TEST_CONTRACT_CODES[1]);
        StageStatus currentStatus = stageStatusRepository.findByStageCode(StageStatusEnum.CURRENT.name());
        StageStatus closedStatus = stageStatusRepository.findByStageCode(StageStatusEnum.CLOSED.name());

        Stage currentStage = TestEntityFactory.createStage(currentStatus, contract1, TEST_STAGE_NAMES[1], TEST_STAGE_NUMBERS[1]);
        Stage savedCurrentStage = stageRepository.save(currentStage);
        Assert.assertNotNull(savedCurrentStage.getId());

        Stage closedStage = TestEntityFactory.createStage(closedStatus, contract1, TEST_STAGE_NAMES[2], TEST_STAGE_NUMBERS[2]);
        Stage savedClosedStage = stageRepository.save(closedStage);
        Assert.assertNotNull(savedClosedStage.getId());

        // Add current stage for the second contract
        Contract contract2 = createContract(TEST_CONTRACT_NAMES[2], TEST_CONTRACT_NUMBERS[2], TEST_CONTRACT_CODES[2]);
        Stage currentStage2 = TestEntityFactory.createStage(currentStatus, contract2, TEST_STAGE_NAMES[2], TEST_STAGE_NUMBERS[2]);
        Stage savedCurrentStage2 = stageRepository.save(currentStage2);
        Assert.assertNotNull(savedCurrentStage2.getId());


        Iterable<Stage> currentStages = stageRepository.findAll(Specification.where(StageSpecifications.stageByContractId(contract1.getId())
                .and(StageSpecifications.stageByStatusId(currentStatus.getId()))));

        Assert.assertTrue(currentStages.iterator().hasNext());
        currentStages.forEach(item -> {
            Assert.assertEquals(contract1.getId(), item.getContract().getId());
            Assert.assertEquals(currentStatus.getId(), item.getStageStatus().getId());
            Assert.assertEquals(StageStatusEnum.CURRENT.name(), item.getStageStatus().getStageCode());
        });


        Iterable<Stage> currentStages1 = stageRepository.findContractStages(contract1.getId(), currentStatus.getId());
        Assert.assertTrue(currentStages1.iterator().hasNext());
        currentStages1.forEach(item -> {
            Assert.assertEquals(contract1.getId(), item.getContract().getId());
            Assert.assertEquals(currentStatus.getId(), item.getStageStatus().getId());
            Assert.assertEquals(StageStatusEnum.CURRENT.name(), item.getStageStatus().getStageCode());
        });

    }

    @Test
    public void testFindByIdIn() {
        Contract contract = createContract(TEST_CONTRACT_NAMES[1], TEST_CONTRACT_NUMBERS[1], TEST_CONTRACT_CODES[1]);

        StageStatus currentStatus = stageStatusRepository.findByStageCode(StageStatusEnum.CURRENT.name());

        Stage stage1 = TestEntityFactory.createStage(currentStatus, contract, TEST_STAGE_NAMES[1], TEST_STAGE_NUMBERS[1]);
        Stage storedStage1 = stageRepository.save(stage1);


        Stage stage2 = TestEntityFactory.createStage(currentStatus, contract, TEST_STAGE_NAMES[2], TEST_STAGE_NUMBERS[2]);
        Stage storedStage2 = stageRepository.save(stage2);

        Stage stage3 = TestEntityFactory.createStage(currentStatus, contract, TEST_STAGE_NAMES[2], TEST_STAGE_NUMBERS[2]);
        Stage storedStage3 = stageRepository.save(stage3);

        Iterable<Stage> stages = stageRepository.findByIdIn(Arrays.asList(storedStage1.getId(), storedStage2.getId()));
        Assert.assertTrue(stages.iterator().hasNext());
        for (Stage stage : stages) {
            Assert.assertTrue(stage.getId().equals(storedStage1.getId())
                    || stage.getId().equals(storedStage2.getId()));
            Assert.assertNotEquals(stage.getId(), storedStage3.getId());
        }
    }

    @Override
    protected Stage createEntity() {
        return createDefaultStage(false);
    }

    @Override
    protected CrudRepository<Stage, Long> getRepository() {
        return stageRepository;
    }

    @Override
    protected void assertEntity(Stage entity) {
        Assert.assertEquals(TEST_STAGE_NAMES[0], entity.getStageName());
        Assert.assertNotNull(entity.getStartDate());
        Assert.assertNotNull(entity.getEndDate());
        Assert.assertNotNull(entity.getContract());
        Assert.assertNotNull(entity.getStageStatus());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getPrice());
        Assert.assertEquals(TEST_STAGE_NUMBERS[0], entity.getStageNumber());
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

    private Stage createDefaultStage(boolean isAgreementExists) {
        Contract contract = createContract(TEST_CONTRACT_NAMES[0], TEST_CONTRACT_NUMBERS[0], TEST_CONTRACT_CODES[0]);

        StageStatus stageStatus = TestEntityFactory.createStageStatus(TEST_STAGE_STATUS_NAME, TEST_STAGE_STATUS_CODE);
        StageStatus storedStatus = stageStatusRepository.save(stageStatus);

        Stage stage = TestEntityFactory.createStage(storedStatus, contract, TEST_STAGE_NAMES[0], TEST_STAGE_NUMBERS[0]);
        if(isAgreementExists) {
            AdditionalAgreement additionalAgreement = TestEntityFactory.createAdditionalAgreement(contract.getId(), TEST_AGREEMENT_NUMBER, TEST_AGREEMENT_NAME);
            AdditionalAgreement storedAgreement = additionalAgreementRepository.save(additionalAgreement);
            stage.setAdditionalAgreement(storedAgreement);
        }
        return stage;

    }
}

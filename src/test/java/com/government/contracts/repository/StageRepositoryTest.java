package com.government.contracts.repository;

import com.government.contracts.model.*;
import com.government.contracts.repository.contract.ContractRepository;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class StageRepositoryTest extends AbstractRepositoryTest<Stage, Long> {

    private static final String TEST_INN = "testInn";
    private static final String TEST_CONTRACTOR_NAME = "contractor_name";
    private static final String TEST_CONTRACT_NUMBER = "contractNumber";
    private static final String TEST_CONTRACT_NAME = "contractName";
    private static final String TEST_CONTRACT_CODE = "contractCode";
    private static final String TEST_STAGE_STATUS_NAME = "testStatusName";
    private static final String TEST_STAGE_STATUS_CODE = "testStatusCode";
    private static final String TEST_STAGE_NAME = "testStageName";
    private static final String TEST_STAGE_NUMBER = "testStageNumber";
    private static final String TEST_AGREEMENT_NAME = "agreementName";
    private static final String TEST_AGREEMENT_NUMBER = "agreementNumber";

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
        Stage stage = createStage(true);
        Stage saved = stageRepository.save(stage);
        Assert.assertNotNull(saved.getId());

        Stage stored = stageRepository.findById(saved.getId()).get();
        assertEntity(stored);
        Assert.assertNotNull(stored.getAdditionalAgreement());
    }

    @Override
    protected Stage createEntity() {
        return createStage(false);
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
        Assert.assertNotNull(entity.getContract());
        Assert.assertNotNull(entity.getStageStatus());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getPrice());
        Assert.assertEquals(TEST_STAGE_NUMBER, entity.getStageNumber());
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

    private Stage createStage(boolean isAgreementExists) {
        Contract contract = createContract();

        StageStatus stageStatus = TestEntityFactory.createStageStatus(TEST_STAGE_STATUS_NAME, TEST_STAGE_STATUS_CODE);
        StageStatus storedStatus = stageStatusRepository.save(stageStatus);

        Stage stage = TestEntityFactory.createStage(storedStatus, contract, TEST_STAGE_NAME, TEST_STAGE_NUMBER);
        if(isAgreementExists) {
            AdditionalAgreement additionalAgreement = TestEntityFactory.createAdditionalAgreement(contract.getId(), TEST_AGREEMENT_NUMBER, TEST_AGREEMENT_NAME);
            AdditionalAgreement storedAgreement = additionalAgreementRepository.save(additionalAgreement);
            stage.setAdditionalAgreement(storedAgreement);
        }
        return stage;

    }
}

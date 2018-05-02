package com.government.contracts.repository;

import com.government.contracts.model.AdditionalAgreement;
import com.government.contracts.model.Contract;
import com.government.contracts.model.Contractor;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by vano on 30.4.18.
 */
public class AdditionalAgreementRepositoryTest extends AbstractRepositoryTest<AdditionalAgreement, Long> {

    private static final String TEST_CONTRACT_NAME = "TestContractName";
    private static final String TEST_CONTRACTOR_NAME = "TestContractorName";
    private static final String TEST_INN = "testInn";
    private static final String TEST_CONTRACT_NUMBER = "contractNumber";
    private static final String TEST_CONTRACT_CODE = "contractCode";

    private static final String AGREEMENT_NUMBER = "agreementNumber";
    private static final String AGREEMENT_NAME = "AGREEMENTnAME";

    @Autowired
    private AdditionalAgreementRepository additionalAgreementRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Override
    protected AdditionalAgreement createEntity() {
        Contract contract = createContract();
        AdditionalAgreement additionalAgreement = TestEntityFactory.createAdditionalAgreement(contract.getId(), AGREEMENT_NUMBER, AGREEMENT_NAME);

        return additionalAgreement;
    }

    @Override
    protected CrudRepository<AdditionalAgreement, Long> getRepository() {
        return additionalAgreementRepository;
    }

    @Override
    protected void assertEntity(AdditionalAgreement entity) {
        Assert.assertEquals(AGREEMENT_NAME, entity.getAgreementName());
        Assert.assertEquals(AGREEMENT_NUMBER, entity.getAgreementNumber());
        Assert.assertNotNull(entity.getStartDate());
        Assert.assertNotNull(entity.getEndDate());
        Assert.assertEquals(TestEntityFactory.TEST_CONTRACT_PRICE, entity.getContractPrice());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getPrice());

        Assert.assertNotNull(entity.getAgreementDate());
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

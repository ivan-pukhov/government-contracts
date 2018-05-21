package com.government.contracts.repository;

import com.government.contracts.entity.AdditionalAgreement;
import com.government.contracts.entity.Contract;
import com.government.contracts.entity.Contractor;
import com.government.contracts.repository.contract.ContractRepository;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vano on 30.4.18.
 */
public class AdditionalAgreementRepositoryTest extends AbstractRepositoryTest<AdditionalAgreement, Long> {

    private static final String[] TEST_CONTRACT_NAMES = {"TestContractName1", "TestContractName2","TestContractName3"};
    private static final String TEST_CONTRACTOR_NAME = "TestContractorName";
    private static final String TEST_INN = "testInn";
    private static final String[] TEST_CONTRACT_NUMBERS = {"contractNumber1", "contractNumber2", "contractNumber3"};
    private static final String[] TEST_CONTRACT_CODES = {"contractCode1", "contarctCode2","contarctCode3"};

    private static final String[] AGREEMENT_NUMBERS = {"agreementNumber1", "agreementNumber2", "agreementNumber3"};
    private static final String[] AGREEMENT_NAMES = {"AGREEMENTnAME1", "AGREEMENTnAME2", "AGREEMENTnAME3"};

    @Autowired
    private AdditionalAgreementRepository additionalAgreementRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Test
    public void testFindByContractId() {
        Contract contract1 = createContract(TEST_CONTRACT_NAMES[1], TEST_CONTRACT_NUMBERS[1], TEST_CONTRACT_CODES[1]);
        Contract contract2 = createContract(TEST_CONTRACT_NAMES[2], TEST_CONTRACT_NUMBERS[2], TEST_CONTRACT_CODES[2]);

        AdditionalAgreement additionalAgreement1 = TestEntityFactory.createAdditionalAgreement(contract1.getId(),
                AGREEMENT_NUMBERS[1], AGREEMENT_NAMES[1]);
        AdditionalAgreement additionalAgreement2 = TestEntityFactory.createAdditionalAgreement(contract2.getId(),
                AGREEMENT_NUMBERS[2], AGREEMENT_NAMES[2]);

        additionalAgreementRepository.saveAll(Arrays.asList(additionalAgreement1,additionalAgreement2));

        assertFindByContractId(contract1.getId(), additionalAgreementRepository.findByContractId(contract1.getId()));
        assertFindByContractId(contract2.getId(), additionalAgreementRepository.findByContractId(contract2.getId()));

    }


    @Override
    protected AdditionalAgreement createEntity() {
        Contract contract = createContract(TEST_CONTRACT_NAMES[0], TEST_CONTRACT_NUMBERS[0], TEST_CONTRACT_CODES[0]);
        AdditionalAgreement additionalAgreement = TestEntityFactory.createAdditionalAgreement(contract.getId(),
                AGREEMENT_NUMBERS[0], AGREEMENT_NAMES[0]);

        return additionalAgreement;
    }

    @Override
    protected CrudRepository<AdditionalAgreement, Long> getRepository() {
        return additionalAgreementRepository;
    }

    @Override
    protected void assertEntity(AdditionalAgreement entity) {
        Assert.assertEquals(AGREEMENT_NAMES[0], entity.getAgreementName());
        Assert.assertEquals(AGREEMENT_NUMBERS[0], entity.getAgreementNumber());
        Assert.assertNotNull(entity.getStartDate());
        Assert.assertNotNull(entity.getEndDate());
        Assert.assertEquals(TestEntityFactory.TEST_CONTRACT_PRICE, entity.getContractPrice());
        Assert.assertEquals(TestEntityFactory.TEST_PRICE, entity.getPrice());

        Assert.assertNotNull(entity.getAgreementDate());
    }

    private Contract createContract(String contractName, String contractNumber, String contractCode) {
        Contractor contractor = TestEntityFactory.createContractor(TEST_INN, TEST_CONTRACTOR_NAME);
        Contractor savedContractor = contractorRepository.save(contractor);
        Assert.assertNotNull(savedContractor.getId());
        Contract contract = TestEntityFactory.createContract(contractName, contractNumber,
                contractCode);
        contract.setContractor(savedContractor);

        Contract savedContract = contractRepository.save(contract);
        Assert.assertNotNull(savedContract.getId());
        return savedContract;
    }

    private void assertFindByContractId(Long contractId, List<AdditionalAgreement> additionalAgreements) {
        Assert.assertFalse(additionalAgreements.isEmpty());
        for(AdditionalAgreement additionalAgreement : additionalAgreements) {
            Assert.assertEquals(contractId, additionalAgreement.getContractId());
        }
    }
}

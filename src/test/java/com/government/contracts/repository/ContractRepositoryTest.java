package com.government.contracts.repository;

import com.government.contracts.model.Contract;
import com.government.contracts.model.Contractor;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vano on 29.4.18.
 */
public class ContractRepositoryTest  extends AbstractRepositoryTest<Contract, Long> {

    private static final String CONTRACT_NAME = "contractName";
    private static final String CONTRACT_CODE = "contractCode12";
    private static final String CONTRACT_NUMBER = "contractNumber12";
    private static final String INN = "testInn";
    private static final String CONTRACTOR_NAME = "testContractorName";

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Override
    protected Contract createEntity() {
        Contract contract = TestEntityFactory.createContract(CONTRACT_NAME, CONTRACT_NUMBER, CONTRACT_CODE);
        Contractor contractor = TestEntityFactory.createContractor(INN,CONTRACTOR_NAME);
        Contractor savedContractor = contractorRepository.save(contractor);
        contract.setContractorId(savedContractor.getId());
        return contract;
    }

    @Override
    protected CrudRepository<Contract, Long> getRepository() {
        return contractRepository;
    }

    @Override
    protected void assertEntity(Contract entity) {
        Assert.assertEquals(CONTRACT_CODE, entity.getContractCode());
        Assert.assertEquals(CONTRACT_NAME,entity.getName());
        Assert.assertEquals(CONTRACT_NUMBER, entity.getContractNumber());
        Assert.assertNotNull(entity.getContractorId());
    }
}

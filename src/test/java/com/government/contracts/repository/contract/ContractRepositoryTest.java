package com.government.contracts.repository.contract;

import com.government.contracts.dto.PeriodDate;
import com.government.contracts.dto.contract.ContractFilterParams;
import com.government.contracts.entity.Contract;
import com.government.contracts.entity.Contractor;
import com.government.contracts.repository.AbstractRepositoryTest;
import com.government.contracts.repository.ContractorRepository;
import com.government.contracts.utils.TestEntityFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public class ContractRepositoryTest  extends AbstractRepositoryTest<Contract, Long> {

    private static final String[] CONTRACT_NAMES = {"contractName1", "contractName2", "contractName3"};
    private static final String[] CONTRACT_CODES = {"contractCode1", "contractCode2", "contractCode3"};
    private static final String[] CONTRACT_NUMBERS = {"contractNumber1", "contractNumber2", "contractNumber3"};
    private static final String INN = "testInn";
    private static final String CONTRACTOR_NAME = "testContractorName";

    private static final int DATE_STEP_1 = 2;
    private static final int DATE_STEP_2 = 3;



    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Test
    public void testFindByContractCode() {
        prepareContracts();
        String contractCode = CONTRACT_CODES[1];
        ContractFilterParams filterParams = createFilterObject(contractCode, null);
        Iterable<Contract> contracts = contractRepository.findContracts(filterParams);

        Assert.assertTrue(contracts.iterator().hasNext());
        for(Contract contract : contracts) {
            Assert.assertEquals(contractCode, contract.getContractCode());
        }
    }

    @Test
    public void testFindByContractNumber() {
        prepareContracts();
        String contractNumber = CONTRACT_NUMBERS[1];
        ContractFilterParams filterParams = createFilterObject(null, contractNumber);
        Iterable<Contract> contracts = contractRepository.findContracts(filterParams);

        Assert.assertTrue(contracts.iterator().hasNext());
        for(Contract contract : contracts) {
            Assert.assertEquals(contractNumber, contract.getContractNumber());
        }
    }

    @Test
    public void testFindByContractCodeAndNumber() {
        prepareContracts();
        String contractCode = CONTRACT_CODES[1];
        String contractNumber = CONTRACT_NUMBERS[1];
        ContractFilterParams filterParams = createFilterObject(contractCode, contractNumber);
        Iterable<Contract> contracts = contractRepository.findContracts(filterParams);

        Assert.assertTrue(contracts.iterator().hasNext());
        for(Contract contract : contracts) {
            Assert.assertEquals(contractNumber, contract.getContractNumber());
            Assert.assertEquals(contractCode, contract.getContractCode());
        }
    }

    @Test
    public void testFindByContractCodeAndNumberEmptyResult() {
        prepareContracts();
        String contractCode = CONTRACT_CODES[1];
        String contractNumber = CONTRACT_NUMBERS[2];
        ContractFilterParams filterParams = createFilterObject(contractCode, contractNumber);
        Iterable<Contract> contracts = contractRepository.findContracts(filterParams);

        Assert.assertFalse(contracts.iterator().hasNext());
    }

    @Test
    public void testFindByStartPeriodStartDate() {
        prepareContracts();

        ContractFilterParams filterParams = new ContractFilterParams();
        PeriodDate startPeriod = new PeriodDate();
        filterParams.setStartPeriod(startPeriod);
        LocalDateTime filterDate = LocalDateTime.now().minusDays(DATE_STEP_1);
        startPeriod.setStartPeriodDate(filterDate);
        Iterable<Contract> contracts = contractRepository.findContracts(filterParams);

        Assert.assertTrue(contracts.iterator().hasNext());
        for(Contract contract : contracts) {
            Assert.assertTrue(contract.getContractStartDate().isAfter(filterDate));
        }
    }

    @Test
    public void testFindByStartPeriodStartDateEndDate() {
        prepareContracts();

        ContractFilterParams filterParams = new ContractFilterParams();
        PeriodDate startPeriod = new PeriodDate();
        filterParams.setStartPeriod(startPeriod);
        LocalDateTime startFilterDate = LocalDateTime.now().minusDays(DATE_STEP_1);
        startPeriod.setStartPeriodDate(startFilterDate);

        LocalDateTime endFilterDate = LocalDateTime.now().plusDays(DATE_STEP_2);
        startPeriod.setEndPeriodDate(endFilterDate);
        Iterable<Contract> contracts = contractRepository.findContracts(filterParams);

        Assert.assertTrue(contracts.iterator().hasNext());
        for(Contract contract : contracts) {
            Assert.assertTrue(contract.getContractStartDate().isAfter(startFilterDate));
            Assert.assertTrue(contract.getContractStartDate().isBefore(endFilterDate));
        }
    }

    @Override
    protected Contract createEntity() {
        Contract contract = TestEntityFactory.createContract(CONTRACT_NAMES[0],
                CONTRACT_NUMBERS[0], CONTRACT_CODES[0]);
        Contractor contractor = TestEntityFactory.createContractor(INN, CONTRACTOR_NAME);
        Contractor savedContractor = contractorRepository.save(contractor);
        contract.setContractor(savedContractor);
        return contract;
    }

    @Override
    protected CrudRepository<Contract, Long> getRepository() {
        return contractRepository;
    }

    @Override
    protected void assertEntity(Contract entity) {
        Assert.assertEquals(CONTRACT_CODES[0], entity.getContractCode());
        Assert.assertEquals(CONTRACT_NAMES[0],entity.getName());
        Assert.assertEquals(CONTRACT_NUMBERS[0], entity.getContractNumber());
        Assert.assertNotNull(entity.getContractor());
    }

    private void prepareContracts() {
        Contract contract1 = TestEntityFactory.createContract(CONTRACT_NAMES[1],
                CONTRACT_NUMBERS[1], CONTRACT_CODES[1]);
        LocalDateTime startDate1 = LocalDateTime.now();
        contract1.setContractStartDate(startDate1);
        contract1.setContractEndDate(startDate1.plusMonths(DATE_STEP_1));
        Contract contract2 = TestEntityFactory.createContract(CONTRACT_NAMES[2],
                CONTRACT_NUMBERS[2], CONTRACT_CODES[2]);
        LocalDateTime startDate2 = LocalDateTime.now().minusMonths(DATE_STEP_2);
        contract2.setContractStartDate(startDate2);
        contract2.setContractEndDate(LocalDateTime.now().plusMonths(DATE_STEP_2));
        Contractor contractor = TestEntityFactory.createContractor(INN, CONTRACTOR_NAME);
        Contractor savedContractor = contractorRepository.save(contractor);
        contract1.setContractor(savedContractor);
        contract2.setContractor(savedContractor);
        Contract savedContract1 = contractRepository.save(contract1);
        Assert.assertNotNull(savedContract1.getContractStartDate());
        Assert.assertNotNull(savedContract1.getContractEndDate());
        Contract savedContract2 = contractRepository.save(contract2);
        Assert.assertNotNull(savedContract2.getContractStartDate());
        Assert.assertNotNull(savedContract2.getContractEndDate());
    }

    private ContractFilterParams createFilterObject(String contractCode, String contractNumber) {
        ContractFilterParams filterParams = new ContractFilterParams();
        filterParams.setContractCode(contractCode);
        filterParams.setContractNumber(contractNumber);
        return filterParams;
    }
}

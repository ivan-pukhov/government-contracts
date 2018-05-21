package com.government.contracts.service;

import com.government.contracts.entity.AdditionalAgreement;
import com.government.contracts.repository.AdditionalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalAgreementServiceImpl extends CrudServiceImpl<AdditionalAgreement, Long> implements AdditionalAgreementService {

    @Autowired
    private ContractService contractService;

    @Autowired
    private AdditionalAgreementRepository additionalAgreementRepository;

    @Override
    public AdditionalAgreement save(AdditionalAgreement agreement) {
        super.save(agreement);
        contractService.updateContract(agreement);
        return agreement;
    }

    @Override
    public AdditionalAgreement update(Long id, AdditionalAgreement agreement) {
        super.update(id, agreement);
        contractService.updateContract(agreement);
        return super.update(id, agreement);
    }

    @Override
    public List<AdditionalAgreement> findByContractId(Long contractId) {
        return additionalAgreementRepository.findByContractId(contractId);
    }

    @Override
    public CrudRepository<AdditionalAgreement, Long> getCrudRepository() {
        return additionalAgreementRepository;
    }
}

package com.government.contracts.service;

import com.government.contracts.model.AdditionalAgreement;
import com.government.contracts.repository.AdditionalAgreementRepository;
import org.springframework.stereotype.Service;

@Service
public class AdditionalAgreementServiceImpl extends CrudServiceImpl<AdditionalAgreement, Long> implements AdditionalAgreementService {

    private ContractService contractService;

    public AdditionalAgreementServiceImpl(AdditionalAgreementRepository repository, ContractService contractService) {
        super(repository);
        this.contractService = contractService;
    }

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
}

package com.government.contracts.service;

import com.government.contracts.model.AdditionalAgreement;
import com.government.contracts.repository.AdditionalAgreementRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AdditionalAgreementServiceImpl extends CrudServiceImpl<AdditionalAgreement, Long> implements AdditionalAgreementService {

    public AdditionalAgreementServiceImpl(AdditionalAgreementRepository repository) {
        super(repository);
    }
}

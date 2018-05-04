package com.government.contracts.controller;

import com.government.contracts.model.AdditionalAgreement;
import com.government.contracts.repository.AdditionalAgreementRepository;
import com.government.contracts.service.AdditionalAgreementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("additionalAgreement")
public class AdditionalAgreementController extends AbstractCrudController<AdditionalAgreement, Long> {
    public AdditionalAgreementController(AdditionalAgreementService service) {
        super(service);
    }
}

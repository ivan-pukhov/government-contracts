package com.government.contracts.controller;

import com.government.contracts.dto.ResponseDto;
import com.government.contracts.entity.AdditionalAgreement;
import com.government.contracts.service.AdditionalAgreementService;
import com.government.contracts.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("additionalAgreement")
public class AdditionalAgreementController extends AbstractCrudController<AdditionalAgreement, Long> {

    @Autowired
    private AdditionalAgreementService additionalAgreementService;

    @RequestMapping("/find/contract/{contractId}")
    public ResponseEntity<ResponseDto> findByContractId(@PathVariable Long contractId) {
        return createCorrectResponse(additionalAgreementService.findByContractId(contractId));
    }

    @Override
    protected CrudService<AdditionalAgreement, Long> getCrudService() {
        return additionalAgreementService;
    }
}

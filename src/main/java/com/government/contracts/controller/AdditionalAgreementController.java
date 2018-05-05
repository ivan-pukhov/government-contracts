package com.government.contracts.controller;

import com.government.contracts.dto.ResponseDto;
import com.government.contracts.model.AdditionalAgreement;
import com.government.contracts.model.Contract;
import com.government.contracts.repository.contract.ContractRepository;
import com.government.contracts.service.AdditionalAgreementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("additionalAgreement")
public class AdditionalAgreementController extends AbstractCrudController<AdditionalAgreement, Long> {

    private ContractRepository contractRepository;

    public AdditionalAgreementController(AdditionalAgreementService service, ContractRepository contractRepository) {
        super(service);
        this.contractRepository = contractRepository;
    }

    @Override
    public ResponseEntity<ResponseDto> save(@RequestBody AdditionalAgreement domain) {
        BigDecimal contractPrice = domain.getContractPrice();
        if (contractPrice != null) {
            Long contractId = domain.getContractId();
            Contract contract = contractRepository.findById(contractId).get();

            contract.setContractPrice(contractPrice);
        }
        return super.save(domain);
    }

    @Override
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id, @RequestBody AdditionalAgreement domain) {
        BigDecimal contractPrice = domain.getContractPrice();
        if (contractPrice != null) {
            Long contractId = domain.getContractId();
            Contract contract = contractRepository.findById(contractId).get();

            contract.setContractPrice(contractPrice);
        }
        return super.update(id, domain);
    }
}

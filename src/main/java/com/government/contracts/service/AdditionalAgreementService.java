package com.government.contracts.service;

import com.government.contracts.entity.AdditionalAgreement;

import java.util.List;

public interface AdditionalAgreementService extends CrudService<AdditionalAgreement, Long> {

    List<AdditionalAgreement> findByContractId(Long contractId);
}

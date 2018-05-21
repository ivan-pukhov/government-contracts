package com.government.contracts.repository;

import com.government.contracts.entity.AdditionalAgreement;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface AdditionalAgreementRepository extends PagingAndSortingRepository<AdditionalAgreement, Long> {

    List<AdditionalAgreement> findByContractId(Long contractId);
}

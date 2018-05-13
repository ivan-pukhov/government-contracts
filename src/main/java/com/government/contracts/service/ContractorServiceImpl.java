package com.government.contracts.service;

import com.government.contracts.entity.Contractor;
import com.government.contracts.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractorServiceImpl extends CrudServiceImpl<Contractor, Long> implements ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    @Override
    public CrudRepository<Contractor, Long> getCrudRepository() {
        return contractorRepository;
    }
}

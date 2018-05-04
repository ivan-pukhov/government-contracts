package com.government.contracts.service;

import com.government.contracts.model.Contractor;
import com.government.contracts.repository.ContractorRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractorServiceImpl extends CrudServiceImpl<Contractor, Long> implements ContractorService {
    public ContractorServiceImpl(ContractorRepository repository) {
        super(repository);
    }
}

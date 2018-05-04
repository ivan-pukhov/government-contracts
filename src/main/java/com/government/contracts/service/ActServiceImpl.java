package com.government.contracts.service;

import com.government.contracts.model.Act;
import com.government.contracts.repository.ActRepository;
import org.springframework.stereotype.Service;

@Service
public class ActServiceImpl extends CrudServiceImpl<Act, Long> implements ActService{
    public ActServiceImpl(ActRepository actRepository) {
        super(actRepository);
    }
}

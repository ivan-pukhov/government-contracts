package com.government.contracts.service;

import com.government.contracts.entity.Act;
import com.government.contracts.repository.ActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ActServiceImpl extends CrudServiceImpl<Act, Long> implements ActService{

    @Autowired
    private ActRepository actRepository;

    @Override
    public CrudRepository<Act, Long> getCrudRepository() {
        return actRepository;
    }
}

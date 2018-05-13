package com.government.contracts.service;

import com.government.contracts.entity.StageStatus;
import com.government.contracts.repository.stage.StageStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class StageStatusServiceImpl extends CrudServiceImpl<StageStatus, Long> implements StageStatusService {

    @Autowired
    private StageStatusRepository stageStatusRepository;

    @Override
    public CrudRepository<StageStatus, Long> getCrudRepository() {
        return stageStatusRepository;
    }
}

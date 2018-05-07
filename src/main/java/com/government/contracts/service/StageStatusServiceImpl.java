package com.government.contracts.service;

import com.government.contracts.model.StageStatus;
import com.government.contracts.repository.stage.StageStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StageStatusServiceImpl extends CrudServiceImpl<StageStatus, Long> implements StageStatusService {

    public StageStatusServiceImpl(StageStatusRepository repository) {
        super(repository);
    }

}

package com.government.contracts.service;

import com.government.contracts.model.Stage;
import com.government.contracts.repository.StageRepository;
import org.springframework.stereotype.Service;

@Service
public class StageServiceImpl extends CrudServiceImpl<Stage, Long> implements StageService {
    public StageServiceImpl(StageRepository stageRepository) {
        super(stageRepository);
    }
}

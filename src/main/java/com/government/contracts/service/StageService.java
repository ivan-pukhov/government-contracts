package com.government.contracts.service;

import com.government.contracts.dto.stage.CombineStageDto;
import com.government.contracts.dto.stage.DivideStageDto;
import com.government.contracts.model.Stage;

import java.util.List;

public interface StageService extends CrudService<Stage, Long> {

    Iterable<Stage> findContractStages(Long contractId);

    Stage combineStage(CombineStageDto combineStageDto);

    Iterable<Stage> divideStage(DivideStageDto divideStageDto);
}

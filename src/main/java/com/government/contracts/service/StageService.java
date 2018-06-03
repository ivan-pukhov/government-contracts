package com.government.contracts.service;

import com.government.contracts.dto.stage.CombineStageDto;
import com.government.contracts.dto.stage.DivideStageDto;
import com.government.contracts.entity.Stage;

public interface StageService extends CrudService<Stage, Long> {

    Iterable<Stage> findCurrentContractStages(Long contractId);
    Iterable<Stage> findContractStages(Long contractId);


    Iterable<Stage> findAdditionalAgreementStages(Long additionalAgreementId);

    Stage combineStage(CombineStageDto combineStageDto);

    Iterable<Stage> divideStage(DivideStageDto divideStageDto);
}

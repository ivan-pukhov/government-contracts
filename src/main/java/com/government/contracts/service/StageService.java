package com.government.contracts.service;

import com.government.contracts.model.Stage;

import java.util.List;

public interface StageService extends CrudService<Stage, Long> {

    List<Stage> findContractStage(Long contractId);
}

package com.government.contracts.dto.stage;

import com.government.contracts.entity.Stage;

import java.util.List;

public class DivideStageDto {
    private Long parentStageId;
    private List<Stage> stages;

    public Long getParentStageId() {
        return parentStageId;
    }

    public void setParentStageId(Long parentStageId) {
        this.parentStageId = parentStageId;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
}

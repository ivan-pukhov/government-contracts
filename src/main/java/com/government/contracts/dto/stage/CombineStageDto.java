package com.government.contracts.dto.stage;

import com.government.contracts.entity.Stage;

import java.util.List;

public class CombineStageDto {
    private List<Long> parentIds;
    private Stage newStage;

    public List<Long> getParentIds() {
        return parentIds;
    }

    public void setParentIds(List<Long> parentIds) {
        this.parentIds = parentIds;
    }

    public Stage getNewStage() {
        return newStage;
    }

    public void setNewStage(Stage newStage) {
        this.newStage = newStage;
    }
}

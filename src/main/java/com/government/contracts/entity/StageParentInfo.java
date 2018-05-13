package com.government.contracts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class StageParentInfo implements Identifiable<Long> {
    @Id
    @GeneratedValue(generator="stage_parent_info_seq")
    @SequenceGenerator(name="stage_parent_info_seq",sequenceName="stage_parent_info_seq")
    private Long id;
    private Long stageId;
    private Long parentStageId;
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getParentStageId() {
        return parentStageId;
    }

    public void setParentStageId(Long parentStageId) {
        this.parentStageId = parentStageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

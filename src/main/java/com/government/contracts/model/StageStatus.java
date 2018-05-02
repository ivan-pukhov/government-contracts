package com.government.contracts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class StageStatus implements Identifiable<Long> {

    @Id
    @GeneratedValue(generator="stage_status_seq")
    @SequenceGenerator(name="stage_status_seq",sequenceName="stage_status_seq")
    private Long id;
    private String stageName;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}

package com.government.contracts.model;

import com.government.contracts.model.Identifiable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Stage implements Identifiable<Long> {

    @Id
    @GeneratedValue(generator="stage_seq")
    @SequenceGenerator(name="stage_seq",sequenceName="stage_seq")
    private Long id;
    private String stageNumber;
    private String stageName;
    private Long stageStatusId;
    private Long contractId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(String stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Long getStageStatusId() {
        return stageStatusId;
    }

    public void setStageStatusId(Long stageStatusId) {
        this.stageStatusId = stageStatusId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

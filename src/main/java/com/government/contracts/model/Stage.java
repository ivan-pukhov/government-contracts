package com.government.contracts.model;

import com.government.contracts.model.Identifiable;

import javax.persistence.*;
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
    @ManyToOne
    private StageStatus stageStatus;
    @ManyToOne
    private Contract contract;
    @ManyToOne
    private AdditionalAgreement additionalAgreement;
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

    public StageStatus getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(StageStatus stageStatus) {
        this.stageStatus = stageStatus;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public AdditionalAgreement getAdditionalAgreement() {
        return additionalAgreement;
    }

    public void setAdditionalAgreement(AdditionalAgreement additionalAgreement) {
        this.additionalAgreement = additionalAgreement;
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

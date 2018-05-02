package com.government.contracts.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Act implements Identifiable<Long> {

    @Id
    @GeneratedValue(generator="act_seq")
    @SequenceGenerator(name="act_seq",sequenceName="act_seq")
    private Long id;
    private Long stageId;
    private String actType;
    private LocalDateTime actDate;
    private String stageNumber;
    private BigDecimal stagePrice;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public LocalDateTime getActDate() {
        return actDate;
    }

    public void setActDate(LocalDateTime actDate) {
        this.actDate = actDate;
    }

    public String getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(String stageNumber) {
        this.stageNumber = stageNumber;
    }

    public BigDecimal getStagePrice() {
        return stagePrice;
    }

    public void setStagePrice(BigDecimal stagePrice) {
        this.stagePrice = stagePrice;
    }
}

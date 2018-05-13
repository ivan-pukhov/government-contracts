package com.government.contracts.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Contract implements Identifiable<Long> {

    @Id
    @GeneratedValue(generator="contract_seq")
    @SequenceGenerator(name="contract_seq",sequenceName="contract_seq")
    private Long id;
    private String name;
    private String contractNumber;
    private LocalDateTime contractDate;
    @ManyToOne
    private Contractor contractor;
    private String subject;
    private String contractCode;
    private BigDecimal contractPrice;
    private Boolean okrFlag;
    private String stopOkrReason;

    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDateTime getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDateTime contractDate) {
        this.contractDate = contractDate;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    public Boolean getOkrFlag() {
        return okrFlag;
    }

    public void setOkrFlag(Boolean okrFlag) {
        this.okrFlag = okrFlag;
    }

    public String getStopOkrReason() {
        return stopOkrReason;
    }

    public void setStopOkrReason(String stopOkrReason) {
        this.stopOkrReason = stopOkrReason;
    }

    public LocalDateTime getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(LocalDateTime contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public LocalDateTime getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(LocalDateTime contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

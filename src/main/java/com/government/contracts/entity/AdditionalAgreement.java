package com.government.contracts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class AdditionalAgreement implements Identifiable<Long> {

    @Id
    @GeneratedValue(generator="additional_agreement_seq")
    @SequenceGenerator(name="additional_agreement_seq",sequenceName="additional_agreement_seq")
    private Long id;

    private String agreementNumber;
    private String agreementName;
    private LocalDateTime agreementDate;
    private Long contractId;
    private BigDecimal contractPrice;
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

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreeementNumber(String agreeementNumber) {
        this.agreementNumber = agreeementNumber;
    }

    public String getAgreementName() {
        return agreementName;
    }

    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }

    public LocalDateTime getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(LocalDateTime agreementDate) {
        this.agreementDate = agreementDate;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
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

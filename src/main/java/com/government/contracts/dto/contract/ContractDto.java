package com.government.contracts.dto.contract;

import com.government.contracts.entity.Contract;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContractDto {
    private Long id;
    private String contractCode;
    private String contractNumber;
    private LocalDateTime contractDate;

    private BigDecimal contractPrice;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private Long contractorId;
    private String contractorName;

    public ContractDto() {
    }

    public ContractDto(Contract contract) {
        this.id = contract.getId();
        this.contractCode = contract.getContractCode();
        this.contractDate = contract.getContractDate();
        this.contractNumber = contract.getContractNumber();
        this.contractPrice = contract.getContractPrice();
        this.contractStartDate = contract.getContractStartDate();
        this.contractEndDate = contract.getContractEndDate();
        this.contractorId = contract.getContractor().getId();
        this.contractorName = contract.getContractor().getFullName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
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

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
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

    public Long getContractorId() {
        return contractorId;
    }

    public void setContractorId(Long contractorId) {
        this.contractorId = contractorId;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }
}

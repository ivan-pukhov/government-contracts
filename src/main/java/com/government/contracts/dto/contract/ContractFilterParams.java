package com.government.contracts.dto.contract;

import com.government.contracts.dto.PeriodDate;

import java.time.LocalDateTime;
import java.time.Period;

public class ContractFilterParams {

    private String contractCode;
    private String contractNumber;

    private PeriodDate startPeriod;
    private PeriodDate endPeriod;

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

    public PeriodDate getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(PeriodDate startPeriod) {
        this.startPeriod = startPeriod;
    }

    public PeriodDate getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(PeriodDate endPeriod) {
        this.endPeriod = endPeriod;
    }
}

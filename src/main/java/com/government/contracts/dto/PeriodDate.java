package com.government.contracts.dto;

import java.time.LocalDateTime;

public class PeriodDate {

    private LocalDateTime startPeriodDate;
    private LocalDateTime endPeriodDate;

    public LocalDateTime getStartPeriodDate() {
        return startPeriodDate;
    }

    public void setStartPeriodDate(LocalDateTime startPeriodDate) {
        this.startPeriodDate = startPeriodDate;
    }

    public LocalDateTime getEndPeriodDate() {
        return endPeriodDate;
    }

    public void setEndPeriodDate(LocalDateTime endPeriodDate) {
        this.endPeriodDate = endPeriodDate;
    }
}

package com.government.contracts.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment implements Identifiable<Long> {
    @Id
    @GeneratedValue(generator="payment_seq")
    @SequenceGenerator(name="payment_seq",sequenceName="payment_seq")
    private Long id;
    private Long paymentTypeId;
    @ManyToOne
    private Stage stage;
    private LocalDateTime paymentDate;
    private BigDecimal paymentSum;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }
}

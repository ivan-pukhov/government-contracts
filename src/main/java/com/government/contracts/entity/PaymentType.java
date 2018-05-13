package com.government.contracts.entity;


import com.government.contracts.enums.PaymentTypeEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class PaymentType implements Identifiable<Long> {

    @Id
    @GeneratedValue(generator="payment_type_seq")
    @SequenceGenerator(name="payment_type_seq",sequenceName="payment_type_seq")
    private Long id;
    private String code;
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

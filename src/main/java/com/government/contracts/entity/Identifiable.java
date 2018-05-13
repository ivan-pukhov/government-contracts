package com.government.contracts.entity;


public interface Identifiable<ID> {
    ID getId();
    void setId(ID id);
}

package com.government.contracts.service;

import com.government.contracts.model.Contract;

import java.util.List;

public interface ContractService {

    List<Contract> findByName(String name);
}

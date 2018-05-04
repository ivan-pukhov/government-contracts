package com.government.contracts.service;

import com.government.contracts.model.Identifiable;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Optional;

public interface CrudService<T extends Identifiable, ID> {
    T save(T domain);

    void deleteById(ID id);

    T update(ID id, T domain);


    Optional<T> findById(ID id);


    Iterable<T>  findAll();

    CrudRepository<T, ID> getRepository();
}

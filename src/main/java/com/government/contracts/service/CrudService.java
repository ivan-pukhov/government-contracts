package com.government.contracts.service;

import com.government.contracts.entity.Identifiable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudService<T extends Identifiable, ID> {
    T save(T domain);

    void deleteById(ID id);

    T update(ID id, T domain);

    Optional<T> findById(ID id);

    Iterable<T>  findAll();

    CrudRepository<T, ID> getCrudRepository();
}

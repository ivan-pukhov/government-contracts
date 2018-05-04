package com.government.contracts.service;

import com.government.contracts.model.Identifiable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class CrudServiceImpl<T extends Identifiable, ID> implements CrudService<T, ID> {

    private CrudRepository<T, ID> repository;

    public CrudServiceImpl(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T domain) {
        return repository.save(domain);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public T update(ID id, T domain) {
        if (repository.existsById(id)) {
            domain.setId(id);
            T savedDomain = repository.save(domain);
            return savedDomain;
        } else {
            String msg = "Entity with id=[" + id + "] is not found";
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    @Override
    public CrudRepository<T, ID> getRepository() {
        return repository;
    }
}

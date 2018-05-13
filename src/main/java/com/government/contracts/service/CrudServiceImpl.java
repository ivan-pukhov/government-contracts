package com.government.contracts.service;

import com.government.contracts.entity.Identifiable;

import java.util.Optional;

public abstract class CrudServiceImpl<T extends Identifiable, ID> implements CrudService<T, ID> {


    @Override
    public T save(T domain) {
        return getRepository().save(domain);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public T update(ID id, T domain) {
        if (getRepository().existsById(id)) {
            domain.setId(id);
            T savedDomain = getRepository().save(domain);
            return savedDomain;
        } else {
            String msg = "Entity with id=[" + id + "] is not found";
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return getRepository().findAll();
    }

}

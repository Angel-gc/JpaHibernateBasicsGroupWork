package org.example.restaurants.service;

import org.example.shared.io.db.Repository;

import java.util.Optional;

public interface RepositoryEnhanced<T> extends Repository {
    public T find(String column, String value);

    @Override
    T save(Object t);

    @Override
    Optional findById(Long id);
}

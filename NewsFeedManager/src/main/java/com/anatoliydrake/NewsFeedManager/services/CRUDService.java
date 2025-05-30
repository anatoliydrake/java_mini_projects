package com.anatoliydrake.NewsFeedManager.services;

import java.util.Collection;

public interface CRUDService<T> {
    T getById(Long id);
    Collection<T> getAll();
    T create(T item);
    void update(T item);
    void deleteById(Long id);
}

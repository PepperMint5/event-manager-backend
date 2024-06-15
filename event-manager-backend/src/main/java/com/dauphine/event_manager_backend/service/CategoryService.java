package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    public List<Category> getAll();

    Optional<Category> getById(UUID id);

}

package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.repository.CategoryRepository;
import com.dauphine.event_manager_backend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundByIdException(id));
    }
}

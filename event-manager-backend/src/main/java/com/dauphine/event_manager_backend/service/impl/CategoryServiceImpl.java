package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.repository.CategoryRepository;
import com.dauphine.event_manager_backend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

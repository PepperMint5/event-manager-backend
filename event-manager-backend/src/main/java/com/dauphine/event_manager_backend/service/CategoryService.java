package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    /**
     *
     * @return
     */
    List<Category> getAll();

    /**
     *
     * @param id
     * @return
     * @throws CategoryNotFoundByIdException
     */
    Category getById(UUID id) throws CategoryNotFoundByIdException;

}

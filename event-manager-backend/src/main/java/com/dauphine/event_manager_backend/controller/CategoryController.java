package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(
        name = "Category API",
        description = "Endpoints for categories"
)
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(
            summary = "Get all categories endpoint",
            description = "Return all categories"
    )
    public ResponseEntity<List<Category>> getAll()  {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }
}

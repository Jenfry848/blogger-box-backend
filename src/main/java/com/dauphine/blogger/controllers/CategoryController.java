package com.dauphine.blogger.controllers;


import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;

    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Retrieve all categories or filter by name"
    )
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name){
        List<Category> categories = name == null || name.isBlank()
                ? service.getAll()
                : service.getAllLikeName(name);
        return categories.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(categories);
    }



    @GetMapping("{id}")
    @Operation(
            summary = "Get categories by ID",
            description = "Retrieve categories or filter by name"
    )
    public ResponseEntity<Category> retrieveCategoryById(@PathVariable UUID id) {
        Category category = service.getById(id);
        return (category == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(category);
    }

    @PostMapping
    @Operation(
            summary = "Create new category",
            description = "Create new category, only required field is the name of the category to create"
    )
    public ResponseEntity<Category> createCategory(@RequestBody String name) {
        Category category = service.create(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);

    }

    @PutMapping("{id}")
    @Operation(
            summary = "Modify categories",
            description = "Write here"
    )
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody String name) {
        Category updated = service.update(id, name);
        return (updated == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete categories by Id",
            description = "Delete"
    )
    public ResponseEntity<Void> DeleteCategory(@PathVariable UUID id) {
        boolean deleted = service.deleteById(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}

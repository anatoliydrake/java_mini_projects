package com.anatoliydrake.NewsFeedManager.controllers;

import com.anatoliydrake.NewsFeedManager.services.CategoryCRUDService;
import com.anatoliydrake.NewsFeedManager.dto.CategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
    private final CategoryCRUDService service;

    public CategoryController(CategoryCRUDService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryDto = service.getById(id);
        if (categoryDto == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Категория с ID " + id + " не найдена.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(service.create(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        service.update(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        CategoryDto categoryDto = service.getById(id);
        if (categoryDto == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Категория с ID " + id + " не найдена.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}

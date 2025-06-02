package com.anatoliydrake.NewsFeedManager.controllers;

import com.anatoliydrake.NewsFeedManager.services.CategoryCRUDService;
import com.anatoliydrake.NewsFeedManager.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/categories")
@Tag(name = "category", description = "Operations related to categories management")
public class CategoryController {
    private final CategoryCRUDService service;

    public CategoryController(CategoryCRUDService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Find category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"Category with ID 1 not found\"}")))
    })
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryDto = service.getById(id);
        if (categoryDto == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Category with ID " + id + " not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(
                            value = """
                                    [
                                      {
                                       "id": 1,
                                       "title": "Sport"
                                      
                                       },
                                       {
                                       "id": 2,
                                       "title": "Science"
                                       }
                                    ]
                                    """
                    )
            }, array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)
            )))
    public ResponseEntity<Collection<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create category")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class)))
    public ResponseEntity<CategoryDto> createCategory(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Create new category",
            required = true,
            content = @Content(mediaType = "application/json",
                    examples = {@io.swagger.v3.oas.annotations.media.ExampleObject(
                            value = """
                                    {
                                        "title": "Sport"
                                    }
                                    """
                    )
                    })) @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(service.create(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update category")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDto.class)))
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        service.update(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"message\": \"Category with ID 1 not found\"}")))
    })
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        CategoryDto categoryDto = service.getById(id);
        if (categoryDto == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Category with ID " + id + " not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

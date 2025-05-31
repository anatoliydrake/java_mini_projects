package com.anatoliydrake.NewsFeedManager.controllers;

import com.anatoliydrake.NewsFeedManager.dto.NewsDto;
import com.anatoliydrake.NewsFeedManager.entities.News;
import com.anatoliydrake.NewsFeedManager.services.NewsCRUDService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api/news")
public class NewsController {
    private final NewsCRUDService service;


    public NewsController(NewsCRUDService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        NewsDto newsDto = service.getById(id);
        if (newsDto == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Новость с ID " + id + " не найдена.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newsDto, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all news")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(
                            value = """
                                    [
                                      {
                                        "id": 1,
                                        "title": "First News",
                                        "text": "Content of the first news",
                                        "date": "2024-05-01T12:00:00",
                                        "categoryId": 2
                                      },
                                      {
                                        "id": 2,
                                        "title": "Second News",
                                        "text": "Content of the second news",
                                        "date": "2024-05-02T12:00:00",
                                        "category": 1
                                      }
                                    ]
                                    """
                    )
            }, array = @ArraySchema(schema = @Schema(implementation = News.class)
            )))
    public ResponseEntity<Collection<NewsDto>> getAllNews() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create news")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewsDto.class)))
    public ResponseEntity<NewsDto> createNews(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Create news",
            required = true,
            content = @Content(mediaType = "application/json",
                    examples = {@io.swagger.v3.oas.annotations.media.ExampleObject(
                            value = """
                                    {
                                        "title": "Some title",
                                        "text": "Some content",
                                        "categoryId": 1
                                    }
                                    """
                    )
            })) @RequestBody NewsDto newsDto) {
        return new ResponseEntity<>(service.create(newsDto), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update news")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = NewsDto.class)))
    public ResponseEntity<NewsDto> updateNews(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Update an existent news",
            required = true,
            content = @Content(mediaType = "application/json")) @RequestBody NewsDto newsDto) {
        service.update(newsDto);
        return new ResponseEntity<>(service.getById(newsDto.getId()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        if (service.getById(id) == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Новость с ID " + id + " не найдена.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "category/{id}")
    public ResponseEntity<Collection<NewsDto>> getNewsByCategoryId(@PathVariable Long id) {
        return new ResponseEntity<>(service.getAll().stream()
                .filter(newsDto -> Objects.equals(newsDto.getCategoryId(), id))
                .toList(), HttpStatus.OK);
    }
}

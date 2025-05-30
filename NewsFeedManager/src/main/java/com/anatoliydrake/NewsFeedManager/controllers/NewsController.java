package com.anatoliydrake.NewsFeedManager.controllers;

import com.anatoliydrake.NewsFeedManager.dto.NewsDto;
import com.anatoliydrake.NewsFeedManager.services.NewsCRUDService;
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
    public ResponseEntity<Collection<NewsDto>> getAllNews() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsDto> createNews(@RequestBody NewsDto newsDto) {
        return new ResponseEntity<>(service.create(newsDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<NewsDto> updateNews(@RequestBody NewsDto newsDto) {
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

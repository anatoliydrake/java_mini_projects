package com.anatoliydrake.NewsFeedManager.services;

import com.anatoliydrake.NewsFeedManager.repositories.CategoryRepository;
import com.anatoliydrake.NewsFeedManager.dto.CategoryDto;
import com.anatoliydrake.NewsFeedManager.entities.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class CategoryCRUDService implements CRUDService<CategoryDto> {

    private final CategoryRepository categoryRepository;

    public CategoryCRUDService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto getById(Long id) {
        log.info("Get category by id: " + id);
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        return mapToDto(category);
    }

    @Override
    public Collection<CategoryDto> getAll() {
        log.info("Get all categories");
        return categoryRepository.findAll().stream()
                .map(CategoryCRUDService::mapToDto).toList();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        log.info("Create");
        Category category = mapToEntity(categoryDto);
        return mapToDto(categoryRepository.save(category));
    }

    @Override
    public void update(CategoryDto categoryDto) {
        log.info("Update " + categoryDto.getId());
        categoryRepository.save(mapToEntity(categoryDto));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete " + id);
        categoryRepository.deleteById(id);
    }

    public static CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        return categoryDto;
    }

    public static Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        return category;
    }
}

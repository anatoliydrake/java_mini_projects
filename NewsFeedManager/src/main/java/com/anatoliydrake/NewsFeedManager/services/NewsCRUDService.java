package com.anatoliydrake.NewsFeedManager.services;

import com.anatoliydrake.NewsFeedManager.repositories.CategoryRepository;
import com.anatoliydrake.NewsFeedManager.repositories.NewsRepository;
import com.anatoliydrake.NewsFeedManager.dto.NewsDto;
import com.anatoliydrake.NewsFeedManager.entities.News;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;

@Service
@Slf4j
public class NewsCRUDService implements CRUDService<NewsDto> {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;

    public NewsCRUDService(NewsRepository newsRepository, CategoryRepository categoryRepository) {
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public NewsDto getById(Long id) {
        log.info("Get news by id: " + id);
        News news = newsRepository.findById(id).orElse(null);
        if (news == null) {
            return null;
        }
        return maptoDto(news);
    }

    @Override
    public Collection<NewsDto> getAll() {
        log.info("Get all news");
        return newsRepository.findAll().stream()
                .map(NewsCRUDService::maptoDto).toList();
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        log.info("Create");
        News news = mapToEntity(newsDto);
        news.setDate(Instant.now());
        Long categoryId = newsDto.getCategoryId();
        news.setCategory(categoryRepository.findById(categoryId).orElseThrow());
        newsRepository.save(news);
        return maptoDto(news);
    }

    @Override
    public void update(NewsDto newsDto) {
        log.info("Update " + newsDto.getId());
        News oldNews = newsRepository.findById(newsDto.getId()).orElseThrow();
        if (newsDto.getTitle() != null) {
            oldNews.setTitle(newsDto.getTitle());
        }
        if (newsDto.getText() != null) {
            oldNews.setText(newsDto.getText());
        }
        if (newsDto.getDate() != null) {
            oldNews.setDate(newsDto.getDate());
        }
        if (newsDto.getCategoryId() != null) {
            Long categoryId = newsDto.getCategoryId();
            oldNews.setCategory(categoryRepository.findById(categoryId).orElseThrow());
        }
        newsRepository.save(oldNews);
    }


    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    public static NewsDto maptoDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setText(news.getText());
        newsDto.setDate(news.getDate());
        newsDto.setCategoryId(news.getCategory().getId());
        return newsDto;
    }

    public static News mapToEntity(NewsDto newsDto) {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        news.setDate(newsDto.getDate());
        return news;
    }
}

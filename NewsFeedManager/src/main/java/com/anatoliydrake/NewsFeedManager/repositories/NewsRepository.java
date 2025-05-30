package com.anatoliydrake.NewsFeedManager.repositories;

import com.anatoliydrake.NewsFeedManager.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}

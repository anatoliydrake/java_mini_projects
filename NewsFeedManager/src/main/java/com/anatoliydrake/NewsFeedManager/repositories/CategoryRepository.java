package com.anatoliydrake.NewsFeedManager.repositories;

import com.anatoliydrake.NewsFeedManager.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

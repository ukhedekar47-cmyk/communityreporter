package com.example.communityreporter.repository;

import com.example.communityreporter.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
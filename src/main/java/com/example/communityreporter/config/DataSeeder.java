package com.example.communityreporter.config;

import com.example.communityreporter.entity.Category;
import com.example.communityreporter.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedCategories(CategoryRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new Category("Road & Potholes"),
                        new Category("Water Supply"),
                        new Category("Electricity"),
                        new Category("Garbage"),
                        new Category("Street Lights")));
            }
        };
    }
}
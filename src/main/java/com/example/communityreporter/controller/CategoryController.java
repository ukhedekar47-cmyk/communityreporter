package com.example.communityreporter.controller;

import com.example.communityreporter.dto.CategoryRequest;
import com.example.communityreporter.dto.CategoryResponse;
import com.example.communityreporter.entity.Category;
import com.example.communityreporter.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CategoryResponse> list() {
        return repository.findAll().stream().map(CategoryResponse::from).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CategoryRequest request) {
        return CategoryResponse.from(repository.save(new Category(request.name())));
    }
}
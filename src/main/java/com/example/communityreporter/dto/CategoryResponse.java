package com.example.communityreporter.dto;

import com.example.communityreporter.entity.Category;

public record CategoryResponse(Long id, String name) {

    public static CategoryResponse from(Category c) {
        return new CategoryResponse(c.getId(), c.getName());
    }
}

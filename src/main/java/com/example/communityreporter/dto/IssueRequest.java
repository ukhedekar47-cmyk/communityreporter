package com.example.communityreporter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IssueRequest(
        @NotBlank @Size(max = 255) String title,
        @NotBlank @Size(max = 1000) String description,
        @NotBlank @Size(max = 255) String location,
        @Size(max = 255) String photoUrl,
        @NotBlank @Size(max = 255) String reportedBy,
        @NotNull Long categoryId) {
}
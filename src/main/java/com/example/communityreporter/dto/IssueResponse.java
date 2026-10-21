package com.example.communityreporter.dto;

import com.example.communityreporter.entity.Issue;
import com.example.communityreporter.entity.IssueStatus;

import java.time.LocalDateTime;

public record IssueResponse(
        Long id,
        String title,
        String description,
        String location,
        String photoUrl,
        String reportedBy,
        IssueStatus status,
        LocalDateTime createdAt,
        Long categoryId,
        String categoryName) {

    public static IssueResponse from(Issue i) {
        return new IssueResponse(i.getId(), i.getTitle(), i.getDescription(), i.getLocation(),
                i.getPhotoUrl(), i.getReportedBy(), i.getStatus(), i.getCreatedAt(),
                i.getCategory().getId(), i.getCategory().getName());
    }
}
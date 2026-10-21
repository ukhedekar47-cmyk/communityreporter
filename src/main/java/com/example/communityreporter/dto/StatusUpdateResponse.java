package com.example.communityreporter.dto;

import com.example.communityreporter.entity.IssueStatus;
import com.example.communityreporter.entity.StatusUpdate;

import java.time.LocalDateTime;

public record StatusUpdateResponse(Long id, IssueStatus status, LocalDateTime updatedAt) {

    public static StatusUpdateResponse from(StatusUpdate s) {
        return new StatusUpdateResponse(s.getId(), s.getStatus(), s.getUpdatedAt());
    }
}
package com.example.communityreporter.dto;

import com.example.communityreporter.entity.IssueStatus;
import jakarta.validation.constraints.NotNull;

public record StatusRequest(@NotNull IssueStatus status) {
}
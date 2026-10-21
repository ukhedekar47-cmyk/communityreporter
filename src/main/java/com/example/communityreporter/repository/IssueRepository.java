package com.example.communityreporter.repository;

import com.example.communityreporter.entity.Issue;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @EntityGraph(attributePaths = "category")
    List<Issue> findAllByOrderByCreatedAtDesc();
}
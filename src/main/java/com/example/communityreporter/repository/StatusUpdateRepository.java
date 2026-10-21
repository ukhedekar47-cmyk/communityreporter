package com.example.communityreporter.repository;

import com.example.communityreporter.entity.StatusUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusUpdateRepository extends JpaRepository<StatusUpdate, Long> {

    List<StatusUpdate> findByIssueIdOrderByUpdatedAtAsc(Long issueId);
}
package com.example.communityreporter.service;

import com.example.communityreporter.dto.IssueRequest;
import com.example.communityreporter.dto.IssueResponse;
import com.example.communityreporter.dto.StatusUpdateResponse;
import com.example.communityreporter.entity.Category;
import com.example.communityreporter.entity.Issue;
import com.example.communityreporter.entity.IssueStatus;
import com.example.communityreporter.entity.StatusUpdate;
import com.example.communityreporter.exception.NotFoundException;
import com.example.communityreporter.repository.CategoryRepository;
import com.example.communityreporter.repository.IssueRepository;
import com.example.communityreporter.repository.StatusUpdateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final CategoryRepository categoryRepository;
    private final StatusUpdateRepository statusUpdateRepository;

    public IssueService(IssueRepository issueRepository,
                        CategoryRepository categoryRepository,
                        StatusUpdateRepository statusUpdateRepository) {
        this.issueRepository = issueRepository;
        this.categoryRepository = categoryRepository;
        this.statusUpdateRepository = statusUpdateRepository;
    }

    @Transactional
    public IssueResponse create(IssueRequest r) {
        Category category = categoryRepository.findById(r.categoryId())
                .orElseThrow(() -> new NotFoundException("Category not found: " + r.categoryId()));
        Issue issue = issueRepository.save(new Issue(r.title(), r.description(), r.location(),
                r.photoUrl(), r.reportedBy(), category));
        statusUpdateRepository.save(new StatusUpdate(issue, issue.getStatus()));
        return IssueResponse.from(issue);
    }

    @Transactional(readOnly = true)
    public List<IssueResponse> list(IssueStatus status, Long categoryId) {
        return issueRepository.findAllByOrderByCreatedAtDesc().stream()
                .filter(i -> status == null || i.getStatus() == status)
                .filter(i -> categoryId == null || i.getCategory().getId().equals(categoryId))
                .map(IssueResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public IssueResponse get(Long id) {
        return IssueResponse.from(find(id));
    }

    @Transactional
    public IssueResponse changeStatus(Long id, IssueStatus status) {
        Issue issue = find(id);
        if (issue.getStatus() != status) {
            issue.setStatus(status);
            statusUpdateRepository.save(new StatusUpdate(issue, status));
        }
        return IssueResponse.from(issue);
    }

    @Transactional(readOnly = true)
    public List<StatusUpdateResponse> history(Long id) {
        find(id);
        return statusUpdateRepository.findByIssueIdOrderByUpdatedAtAsc(id).stream()
                .map(StatusUpdateResponse::from)
                .toList();
    }

    private Issue find(Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Issue not found: " + id));
    }
}
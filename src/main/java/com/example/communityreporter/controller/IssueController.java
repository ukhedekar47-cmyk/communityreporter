package com.example.communityreporter.controller;

import com.example.communityreporter.dto.IssueRequest;
import com.example.communityreporter.dto.IssueResponse;
import com.example.communityreporter.dto.StatusRequest;
import com.example.communityreporter.dto.StatusUpdateResponse;
import com.example.communityreporter.entity.IssueStatus;
import com.example.communityreporter.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IssueResponse create(@Valid @RequestBody IssueRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<IssueResponse> list(@RequestParam(required = false) IssueStatus status,
                                    @RequestParam(required = false) Long categoryId) {
        return service.list(status, categoryId);
    }

    @GetMapping("/{id}")
    public IssueResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PatchMapping("/{id}/status")
    public IssueResponse changeStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) {
        return service.changeStatus(id, request.status());
    }

    @GetMapping("/{id}/history")
    public List<StatusUpdateResponse> history(@PathVariable Long id) {
        return service.history(id);
    }
}
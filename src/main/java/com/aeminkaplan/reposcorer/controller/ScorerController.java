package com.aeminkaplan.reposcorer.controller;

import com.aeminkaplan.reposcorer.model.RepoRequest;
import com.aeminkaplan.reposcorer.model.RepoResponse;
import com.aeminkaplan.reposcorer.service.ScorerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/score")
public class ScorerController {

    private ScorerService scorerService;

    @Autowired
    public ScorerController(ScorerService scorerService) {
        this.scorerService = scorerService;
    }

    @GetMapping
    @Operation(
            summary = "Get scored repositories",
            description = "Fetches and scores GitHub repositories based on stars, forks, and recent updates."
    )
    public ResponseEntity<List<RepoResponse>> getScoredRepos(@Parameter(description = "Keyword to search for", example = "webcrawler") @RequestParam String keyword,
                                                             @Parameter(description = "Filter by programming language", example = "python") @RequestParam(required = false) String language,
                                                             @Parameter(description = "Earliest created date of the repository (ISO format)", example = "2024-01-01T00:00:00")
                                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime earliestCreated) {
        log.info("GET /v1/score called with keyword={}, language={}, earliestCreated={}", keyword, language, earliestCreated);
        RepoRequest request = new RepoRequest();
        request.setKeyword(keyword);
        request.setLanguage(language);
        request.setEarliestCreateDate(earliestCreated);
        List<RepoResponse> results = scorerService.scoreRepositories(request);
        return ResponseEntity.ok(results);
    }


}

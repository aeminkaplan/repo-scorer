package com.aeminkaplan.reposcorer.service;

import com.aeminkaplan.reposcorer.model.RepoRequest;
import com.aeminkaplan.reposcorer.model.RepoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScorerService {

    private Calculator calculator;

    private FetcherService fetcherService;

    @Autowired
    public ScorerService(Calculator calculator,FetcherService fetcherService) {
        this.calculator = calculator;
        this.fetcherService = fetcherService;
    }



    @Cacheable(value = "repos", key = "#request.keyword + '_' + #request.language + '_' + #request.earliestCreateDate")
    public List<RepoResponse> scoreRepositories(RepoRequest request){
        List<RepoResponse> fetchedRepositories = fetcherService.fetch(request);
        fetchedRepositories.forEach(r->r.setPopularityScore(calculator.calculate(r.getStargazers_count(),r.getForks(),r.getUpdated_at())));
        return fetchedRepositories;
    }
}

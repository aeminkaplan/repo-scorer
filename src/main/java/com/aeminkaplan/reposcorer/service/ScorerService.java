package com.aeminkaplan.reposcorer.service;

import com.aeminkaplan.reposcorer.model.RepoRequest;
import com.aeminkaplan.reposcorer.model.RepoResponse;
import org.springframework.beans.factory.annotation.Autowired;
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




    public List<RepoResponse> scoreRepositories(RepoRequest request){
        List<RepoResponse> fetchedRepositories = fetcherService.fetch(request);
        fetchedRepositories.forEach(r->r.setPopularityScore(calculator.calculate(r.getStars(),r.getForks(),r.getLastUpdated())));
        return fetchedRepositories;
    }
}

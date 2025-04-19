package com.aeminkaplan.reposcorer.service;

import com.aeminkaplan.reposcorer.model.RepoRequest;
import com.aeminkaplan.reposcorer.model.RepoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScorerService {

    private Calculator calculator;

    @Autowired
    public ScorerService(Calculator calculator) {
        this.calculator = calculator;
    }



    private List<RepoResponse> fetchRepositories(RepoRequest request){
        //TODO fetch from Github api will be implemented
        return new ArrayList<>();

    }


    public List<RepoResponse> scoreRepositories(RepoRequest request){
        List<RepoResponse> fetchedRepositories = fetchRepositories(request);
        fetchedRepositories.forEach(r->r.setPopularityScore(calculator.calculate(r.getStars(),r.getForks(),r.getLastUpdated())));
        return fetchedRepositories;
    }
}

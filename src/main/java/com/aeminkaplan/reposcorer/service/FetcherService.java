package com.aeminkaplan.reposcorer.service;

import com.aeminkaplan.reposcorer.model.GitHubSearchResponse;
import com.aeminkaplan.reposcorer.model.RepoRequest;
import com.aeminkaplan.reposcorer.model.RepoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class FetcherService {


    private RestClient restClient;

    public List<RepoResponse> fetch(RepoRequest request){


        if(restClient == null){
            restClient = RestClient.builder()
                    .baseUrl("https://api.github.com")
                    .build();
        }


        GitHubSearchResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/repositories")
                        .queryParam("q", request.getKeyword())
                        .build())
                .retrieve()
                .body(GitHubSearchResponse.class);

        return response.getItems();


    }
}

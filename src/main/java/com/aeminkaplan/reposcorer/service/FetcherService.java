package com.aeminkaplan.reposcorer.service;

import com.aeminkaplan.reposcorer.exception.ExternalApiException;
import com.aeminkaplan.reposcorer.model.GitHubSearchResponse;
import com.aeminkaplan.reposcorer.model.RepoRequest;
import com.aeminkaplan.reposcorer.model.RepoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Slf4j
@Service
public class FetcherService {


    private RestClient restClient;

    @Autowired
    public FetcherService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<RepoResponse> fetch(RepoRequest request) throws ExternalApiException {

        try {
            String query = request.getKeyword();
            if(request.getLanguage() != null){
                query = query + String.format(" language:%s",request.getLanguage());
            }
            if(request.getEarliestCreateDate() != null){
                query = query + String.format("  created:>%s",request.getEarliestCreateDate());

            }

            String finalQuery = query;
            GitHubSearchResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search/repositories")
                            .queryParam("q", finalQuery)
                            .queryParam("per_page", 100)
                            .build())
                    .retrieve()
                    .body(GitHubSearchResponse.class);

            return response != null ? response.getItems() : List.of();
        } catch (HttpClientErrorException e) {
            //4xx responses
            throw new ExternalApiException("GitHub API returned an error: " + e.getStatusCode(), e);
        } catch (HttpServerErrorException e) {
            //5xx server errors
            log.error("GitHub API is currently unavailable: {}", e.getMessage());
            throw new ExternalApiException("GitHub server error: " + e.getStatusCode(), e);

        } catch (RestClientException e) {
            log.error("Error while calling GitHub API: {}", e.getMessage());
            throw new ExternalApiException("Error connecting to GitHub API", e);
        }
    }
}

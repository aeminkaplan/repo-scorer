package com.aeminkaplan.reposcorer.service;

import com.aeminkaplan.reposcorer.model.RepoRequest;
import com.aeminkaplan.reposcorer.model.RepoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ScorerServiceTest {

    @Autowired
    ScorerService scorerService;
    @MockBean
    FetcherService fetcherService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testScoreRepositories() {
        RepoRequest request = new RepoRequest("webcrawler","python", LocalDateTime.now());
        RepoResponse repoResponse =  new RepoResponse("Python3Webcrawler","https://github.com/mochazi/Python3Webcrawler",10L,10L,LocalDateTime.now(),null,"Python");

        when(fetcherService.fetch(any())).thenReturn(Stream.of(repoResponse).collect(Collectors.toList()));

        List<RepoResponse> result = scorerService.scoreRepositories(request);
        assertEquals(40, result.getFirst().getPopularityScore());
    }
}
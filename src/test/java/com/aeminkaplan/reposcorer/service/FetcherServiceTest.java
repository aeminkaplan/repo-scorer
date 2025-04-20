package com.aeminkaplan.reposcorer.service;

import com.aeminkaplan.reposcorer.model.RepoRequest;
import com.aeminkaplan.reposcorer.model.RepoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class FetcherServiceTest {

    @Autowired
    FetcherService fetcherService;

    @Test
    void fetch() {
        RepoRequest request = new RepoRequest("webcrawler","python", LocalDateTime.now().minusMonths(6));

        List<RepoResponse> responseList = fetcherService.fetch(request);
        assertNotNull(responseList);
        assertNotEquals(0,responseList.size());
        assertEquals(request.getLanguage().toLowerCase(),responseList.getFirst().getLanguage().toLowerCase());
    }
}
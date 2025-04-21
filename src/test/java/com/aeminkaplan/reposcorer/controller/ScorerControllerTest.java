package com.aeminkaplan.reposcorer.controller;

import com.aeminkaplan.reposcorer.model.RepoResponse;
import com.aeminkaplan.reposcorer.service.FetcherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ScorerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FetcherService fetcherService;

    @Test
    void getRepos_shouldReturnListOfRepos_withKeyword() throws Exception {

        RepoResponse repo1 =  new RepoResponse("Python3Webcrawler","https://github.com/mochazi/Python3Webcrawler",10L,10L,LocalDateTime.now(),null,"Python");
        RepoResponse repo2 =  new RepoResponse("PyWebcrawler","https://github.com/userSw/PyWebcrawler",15L,15L,LocalDateTime.now(),null,"Python");

        List<RepoResponse> mockRepos = List.of(
            repo1,repo2
        );

        when(fetcherService.fetch(any())).thenReturn(mockRepos);
        mockMvc.perform(get("/v1/score")
                        .param("keyword","webcrawler")
                        .param("language", "python")
                        .param("earliestCreated", "2024-04-01T00:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Python3Webcrawler"))
                .andExpect(jsonPath("$[1].name").value("PyWebcrawler"));
    }

    @Test
    void getRepos_shouldReturn400_when_no_keyword_provided_as_parameter() throws Exception {

        mockMvc.perform(get("/v1/score")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
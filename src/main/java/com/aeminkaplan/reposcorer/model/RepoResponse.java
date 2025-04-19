package com.aeminkaplan.reposcorer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RepoResponse {

    private String name;
    private String url;
    private Long stargazers_count;
    private Long forks;
    private LocalDateTime updated_at;
    private Long popularityScore;
}

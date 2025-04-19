package com.aeminkaplan.reposcorer.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepoResponse {

    private String name;
    private String url;
    private long stars;
    private long forks;
    private LocalDateTime lastUpdated;
    private long popularityScore;
}

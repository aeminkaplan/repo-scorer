package com.aeminkaplan.reposcorer.model;

import lombok.Data;

import java.util.List;

@Data
public class GitHubSearchResponse {
    private List<RepoResponse> items;
}

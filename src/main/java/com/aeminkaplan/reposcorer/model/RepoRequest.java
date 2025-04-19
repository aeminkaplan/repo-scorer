package com.aeminkaplan.reposcorer.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepoRequest {
    private String keyword;
    private String language;
    private LocalDateTime earliestCreateDate;
}

package com.aeminkaplan.reposcorer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepoRequest {
    private String keyword;
    private String language;
    private LocalDateTime earliestCreateDate;
}

package com.aeminkaplan.reposcorer.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class Calculator {
    public long calculate(int numberOfStars, int numberOfForks, LocalDateTime lastUpdatedTime){

        //initial score is sum of forks and stars
        int score = numberOfForks + numberOfStars;
        long numberOfDaysAfterLastUpdate = Duration.between(lastUpdatedTime, LocalDateTime.now()).toDays();

        if(numberOfDaysAfterLastUpdate <= 30){
            score += score;
        }
        else if(numberOfDaysAfterLastUpdate <= 180){
            score += score/2;
        }
        else if(numberOfDaysAfterLastUpdate <= 365){
            score += score/3;
        }
        return  score;
    }
}

package com.intuit.homeAssignment.persistence;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Player {

    private String playerId;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private Integer deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private float weight;
    private float height;
    private String playerBats;
    private String playerThrows;
    private LocalDate debut;
    private LocalDate finalGame;
    private String retroID;
    private String bbrefID;
}


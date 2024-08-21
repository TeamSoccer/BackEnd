package soccerTeam.dto;

import lombok.Data;

@Data
public class SoccerTeamInsertRequest {
    private String title;
    private String teamName;
    private String region;
    private String teamDay;
    private int teamTime;
    private int teamPeriod;
    private String teamNumber;
    private int teamOld;
    private String needPosition;
    private int needPositionNumber;
    private int athleteNumber;
    private String contents;

    // Getters and setters
}


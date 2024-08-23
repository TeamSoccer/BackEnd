package soccerTeam.team.dto.request;

import lombok.Data;
import soccerTeam.team.repository.SoccerTeamEntity;

import java.sql.Time;

@Data
public class SoccerTeamInsertRequest {
    private String title;
    private String name;
    private String region;
    private String phoneNumber;
    private Integer period;
    private String day;
    private Time startTime;
    private Time endTime;
    private Integer ageAverage;
    private String needPosition;
    private String needPositionCnt;
    private Integer athleteCnt;
    private String contents;
    private Integer hitCnt;
}


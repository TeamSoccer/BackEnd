package soccerTeam.team.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import soccerTeam.team.repository.SoccerTeamEntity;

import java.sql.Time;
import java.time.LocalTime;

@Data
public class SoccerTeamInsertRequest {
    private String title;
    private String name;
    private String region;
    private String phoneNumber;
    private Integer period;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer ageAverage;
    private String needPosition;
    private String needPositionCnt;
    private Integer athleteCnt;
    private String contents;
}


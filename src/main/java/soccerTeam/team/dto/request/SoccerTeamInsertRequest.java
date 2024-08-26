package soccerTeam.team.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoccerTeamInsertRequest {
    private String title;
    private String name;
    private String region;
    private String phoneNumber;
    private Integer period;
    private List<String> day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer ageAverage;
    private String needPosition;
    private String needPositionCnt;
    private Integer athleteCnt;
    private String contents;
}


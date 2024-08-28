package soccerTeam.team.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import soccerTeam.player.dto.response.PlayerProfileResponse;

@Data
@AllArgsConstructor
public class SoccerTeamDto {
    private Long id;
    private PlayerProfileResponse player;
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
    private Integer hitCnt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	private List<SoccerTeamFileDto> fileInfoList;
    private Boolean isOwner;
}

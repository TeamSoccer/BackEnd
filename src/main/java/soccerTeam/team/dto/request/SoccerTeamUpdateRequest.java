package soccerTeam.team.dto.request;

import java.time.LocalTime;

public record SoccerTeamUpdateRequest(
        Long id,
        String title,
        String name,
        String region,
        String phoneNumber,
        Integer period,
        String day,
        LocalTime startTime,
        LocalTime endTime,
        Integer ageAverage,
        String needPosition,
        String needPositionCnt,
        Integer athleteCnt,
        String contents) {
}

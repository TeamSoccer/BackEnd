package soccerTeam.team;

import soccerTeam.team.dto.request.SoccerTeamUpdateRequest;

import java.time.LocalTime;

public record SoccerTeamUpdateDto(
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
    public static SoccerTeamUpdateDto from(SoccerTeamUpdateRequest request) {
        return new SoccerTeamUpdateDto(
                request.id(),
                request.title(),
                request.name(),
                request.region(),
                request.phoneNumber(),
                request.period(),
                request.day(),
                request.startTime(),
                request.endTime(),
                request.ageAverage(),
                request.needPosition(),
                request.needPositionCnt(),
                request.athleteCnt(),
                request.contents());
    }
}

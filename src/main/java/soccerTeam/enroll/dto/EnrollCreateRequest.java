package soccerTeam.enroll.dto;

public record EnrollCreateRequest(
        Long teamId,
        String title,
        String content) {
}

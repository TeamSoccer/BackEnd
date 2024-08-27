package soccerTeam.enroll.dto;

import soccerTeam.enroll.repository.EnrollEntity;
import soccerTeam.player.repository.PlayerEntity;

import java.time.LocalDateTime;

public record EnrollListResponse(
        Long id,
        String title,
        String playerName,
        String region,
        Boolean athlete,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static EnrollListResponse from(EnrollEntity enroll) {
        PlayerEntity player = enroll.getPlayer();
        return new EnrollListResponse(
                enroll.getId(),
                enroll.getTitle(),
                player.getName(),
                player.getPhoneNumber(),
                player.getAthlete(),
                enroll.getCreatedAt(),
                enroll.getUpdatedAt()
        );
    }
}

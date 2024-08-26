package soccerTeam.enroll.dto;

import soccerTeam.enroll.repository.EnrollEntity;

import java.time.LocalDateTime;

public record EnrollCreateResponse(
        Long id,
        String title,
        String content,
        Integer hitCnt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public static EnrollCreateResponse of(EnrollEntity enroll) {
        return new EnrollCreateResponse(
                enroll.getId(),
                enroll.getTitle(),
                enroll.getContent(),
                enroll.getHitCnt(),
                enroll.getCreatedAt(),
                enroll.getUpdatedAt());
    }
}

package soccerTeam.enroll.dto;

import java.time.LocalDateTime;

public record EnrollCreateResponse(
        Long id,
        String title,
        String content,
        Integer hitCnt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}

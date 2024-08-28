package soccerTeam.enroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import soccerTeam.enroll.repository.EnrollEntity;

@Schema(description = "선수 입단신청 수정 DTO")
public record EnrollUpdateDto(
        Long id,
        String title,
        String content,
        String position) {

    public static EnrollUpdateDto of(EnrollEntity enrollEntity) {
        return new EnrollUpdateDto(
                enrollEntity.getId(),
                enrollEntity.getTitle(),
                enrollEntity.getContent(),
                enrollEntity.getPosition()
        );
    }
}

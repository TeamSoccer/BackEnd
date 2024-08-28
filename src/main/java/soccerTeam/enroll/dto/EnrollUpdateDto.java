package soccerTeam.enroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "선수 입단신청 수정 DTO")
public record EnrollUpdateDto(
        Long id,
        Long teamId,
        String title,
        String content,
        String position) {

    public static EnrollUpdateDto from(EnrollUpdateRequest request) {
        return new EnrollUpdateDto(
                request.id(),
                request.teamId(),
                request.title(),
                request.content(),
                request.position()
        );
    }
}

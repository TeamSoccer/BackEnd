package soccerTeam.enroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "선수 입단신청 수정 DTO")
public record EnrollUpdateDto(
        Long id,
        String title,
        String content,
        String position) {

    public static EnrollUpdateDto from(EnrollUpdateRequest request) {
        return new EnrollUpdateDto(
                request.id(),
                request.title(),
                request.content(),
                request.position()
        );
    }
}

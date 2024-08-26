package soccerTeam.enroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "선수 입단신청 등록 요청 객체")
public record EnrollCreateRequest(

        @Schema(description = "팀 ID", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Long teamId,

        @NotBlank(message = "제목을 입력해주세요.")
        @Schema(description = "등록할 선수 입단신청의 제목", example = "선수A 입단 신청입니다.", requiredMode = Schema.RequiredMode.REQUIRED)
        String title,

        @NotBlank(message = "내용을 입력해주세요.")
        @Schema(description = "등록할 선수 입단신청의 내용", example = "선수A 잘 부탁드립니다.", requiredMode = Schema.RequiredMode.REQUIRED)
        String content) {
}

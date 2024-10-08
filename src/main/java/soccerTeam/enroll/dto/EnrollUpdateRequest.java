package soccerTeam.enroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import soccerTeam.enroll.repository.EnrollEntity;

@Schema(description = "선수 입단신청 수정 요청 객체")
public record EnrollUpdateRequest(

        @NotNull
        @Schema(description = "입단신청 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Long id,

        @NotBlank(message = "제목을 입력해주세요.")
        @Schema(description = "등록할 선수 입단신청의 제목", example = "선수A 입단 신청입니다.", requiredMode = Schema.RequiredMode.REQUIRED)
        String title,

        @NotBlank(message = "내용을 입력해주세요.")
        @Schema(description = "등록할 선수 입단신청의 내용", example = "선수A 잘 부탁드립니다.", requiredMode = Schema.RequiredMode.REQUIRED)
        String content,

        @NotBlank(message = "포지션을 입력해주세요.")
        @Schema(description = "선수가 선호하는 포지션의 내용", example = "공격수.", requiredMode = Schema.RequiredMode.REQUIRED)
        String position
        ) {

        public void updateTo(EnrollEntity enrollEntity) {
                enrollEntity.setTitle(this.title);
                enrollEntity.setContent(this.content);
                enrollEntity.setPosition(this.position);
                enrollEntity.setUpdatedAt(java.time.LocalDateTime.now());
        }
}

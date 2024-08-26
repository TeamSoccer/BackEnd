package soccerTeam.team.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalTime;

@Schema(description = "축구 팀 수정 요청 객체")
public record SoccerTeamUpdateRequest(

        @Schema(description = "팀 ID", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Long id,

        @NotBlank(message = "제목을 입력해주세요.")
        @Schema(description = "수정할 팀의 제목", example = "수정 축구 팀 제목", requiredMode = Schema.RequiredMode.REQUIRED)
        String title,

        @NotBlank(message = "팀 이름을 입력해주세요.")
        @Schema(description = "수정할 팀 이름", example = "수정 TEST FC", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,

        @NotBlank(message = "활동 지역을 입력해주세요.")
        @Schema(description = "수정할 활동 지역", example = "인천", requiredMode = Schema.RequiredMode.REQUIRED)
        String region,

        @NotBlank(message = "전화번호를 입력해주세요.")
        @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호는 000-0000-0000 형식이어야 합니다.")
        @Schema(description = "수정할 전화번호", example = "010-8765-4321", requiredMode = Schema.RequiredMode.REQUIRED)
        String phoneNumber,

        @Schema(description = "수정할 운영 기간", example = "11", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Integer period,

        @NotBlank(message = "활동 요일을 선택해주세요.")
        @Schema(description = "수정할 활동 요일", example = "화,목", requiredMode = Schema.RequiredMode.REQUIRED)
        String day,

        @NotBlank(message = "활동 시작 시간을 입력해주세요.")
        @Schema(description = "수정할 활동 시작 시간", example = "20:00", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalTime startTime,

        @NotBlank(message = "활동 종료 시간을 입력해주세요.")
        @Schema(description = "수정할 활동 종료 시간", example = "22:00", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalTime endTime,

        @Schema(description = "수정할 팀의 평균 연령", example = "40", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Integer ageAverage,

        @Schema(description = "수정할 필요한 포지션", example = "골키퍼", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String needPosition,

        @Schema(description = "수정할 필요한 포지션 수", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String needPositionCnt,

        @Schema(description = "수정할 선수 출신 수", example = "3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Integer athleteCnt,

        @Schema(description = "수정할 팀 소개 내용", example = "저희 팀 실력은 중입니다.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String contents) {
}

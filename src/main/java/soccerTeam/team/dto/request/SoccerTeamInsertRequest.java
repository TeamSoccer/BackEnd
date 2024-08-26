package soccerTeam.team.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.List;

@Data
@Schema(description = "축구 팀 등록 요청 객체")
@AllArgsConstructor
@NoArgsConstructor
public class SoccerTeamInsertRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    @Schema(description = "팀의 제목", example = "축구 팀 모집", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank(message = "팀 이름을 입력해주세요.")
    @Schema(description = "팀 이름", example = "TEST FC", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "활동 지역을 입력해주세요.")
    @Schema(description = "활동 지역", example = "서울", requiredMode = Schema.RequiredMode.REQUIRED)
    private String region;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호는 000-0000-0000 형식이어야 합니다.")
    @Schema(description = "전화번호", example = "010-1234-5678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phoneNumber;

    @Schema(description = "운영 기간", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer period;

    @NotBlank(message = "활동 요일을 선택해주세요.")
    @Schema(description = "활동 요일", example = "월,수,금", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> day;

    @NotBlank(message = "활동 시작 시간을 입력해주세요.")
    @Schema(description = "활동 시작 시간", example = "18:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalTime startTime;

    @NotBlank(message = "활동 종료 시간을 입력해주세요.")
    @Schema(description = "활동 종료 시간", example = "20:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalTime endTime;

    @Schema(description = "팀의 평균 연령", example = "30", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer ageAverage;

    @Schema(description = "필요한 포지션", example = "공격수", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String needPosition;

    @Schema(description = "필요한 포지션 수", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String needPositionCnt;

    @Schema(description = "선수 출신 수", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer athleteCnt;

    @Schema(description = "팀 소개 내용", example = "저희 팀 실력은 하하하입니다.")
    private String contents;
}


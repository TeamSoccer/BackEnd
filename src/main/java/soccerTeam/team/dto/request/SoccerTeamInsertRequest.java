package soccerTeam.team.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import soccerTeam.team.repository.SoccerTeamEntity;

import java.sql.Time;
import java.time.LocalTime;

@Data
public class SoccerTeamInsertRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private String name; // 자동 생성

    @NotBlank(message = "활동지역을 입력해주세요.")
    private String region;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호는 000-0000-0000 형식이어야 합니다.")
    private String phoneNumber;

    private Integer period;

    @NotBlank(message = "활동 요일을 입력해주세요.")
    private String day;

    @NotBlank(message = "활동 시작 시간을 입력해주세요.")
    private LocalTime startTime;

    @NotBlank(message = "활동 종료 시간을 입력해주세요.")
    private LocalTime endTime;

    private Integer ageAverage;
    private String needPosition;
    private String needPositionCnt;
    private Integer athleteCnt;
    private String contents;
}


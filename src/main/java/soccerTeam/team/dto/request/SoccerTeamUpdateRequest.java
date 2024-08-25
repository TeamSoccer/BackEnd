package soccerTeam.team.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalTime;

public record SoccerTeamUpdateRequest(
        Long id,

        @NotBlank(message = "제목을 입력해주세요.")
        String title,

        String name,

        @NotBlank(message = "활동지역을 입력해주세요.")
        String region,

        @Pattern(regexp = "^[0-9]{10,15}$", message = "전화번호를 입력해주세요. 전화번호는 10자리에서 15자리 사이입니다.")
        String phoneNumber,

        Integer period,

        @NotBlank(message = "활동 요일을 입력해주세요.")
        String day,

        @NotBlank(message = "활동 시작 시간을 입력해주세요.")
        LocalTime startTime,

        @NotBlank(message = "활동 종료 시간을 입력해주세요.")
        LocalTime endTime,

        Integer ageAverage,
        String needPosition,
        String needPositionCnt,
        Integer athleteCnt,
        String contents) {
}

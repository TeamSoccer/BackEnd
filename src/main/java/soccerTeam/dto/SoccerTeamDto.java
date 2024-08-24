package soccerTeam.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import soccerTeam.player.dto.response.PlayerProfileResponse;

@Data
@AllArgsConstructor
public class SoccerTeamDto {
    private Long id;
    private PlayerProfileResponse player;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private String name;

    @NotBlank(message = "활동지역을 입력해주세요.")
    private String region;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "전화번호를 입력해주세요. 전화번호는 10자리에서 15자리 사이입니다.")
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
    private Integer hitCnt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	private List<SoccerTeamFileDto> fileInfoList;
}


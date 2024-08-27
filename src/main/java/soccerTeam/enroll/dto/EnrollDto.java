package soccerTeam.enroll.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import soccerTeam.enroll.repository.EnrollEntity;
import soccerTeam.player.dto.response.PlayerProfileResponse;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "입단 신청 요청 객체")
public class EnrollDto {

    @Schema(description = "Enroll ID", example = "1")
    private Long id;

    @Schema(description = "사용자의 정보")
    private PlayerProfileResponse player;

    @NotBlank(message = "제목을 입력해주세요.")
    @Schema(description = "Title of the enrollment", example = "Join the team")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Schema(description = "Content of the enrollment", example = "I would like to join the soccer team.")
    private String content;

    @NotBlank(message = "선호 포지션을 입력해주세요.")
    @Schema(description = "Position of the player", example = "Forward")
    private String position;

    @Schema(description = "조회수", example = "10")
    private Integer hitCnt;

    @Schema(description = "사용자의 권한", example = "ROLE_USER")
    private String role;

    @Schema(description = "사용자의 지역", example = "Seoul")
    private String region;

    @Schema(description = "신청 팀의 ID", example = "2")
    private Long teamId;

    @Schema(description = "생성된 시간", example = "2024-08-27T10:15:30")
    private LocalDateTime createdAt;

    @Schema(description = "마지막으로 수정된 시간", example = "2024-08-27T10:15:30")
    private LocalDateTime updatedAt;

    public static EnrollDto of(EnrollEntity enroll) {
        return EnrollDto.builder()
                .id(enroll.getId())
                .player(PlayerProfileResponse.of(enroll.getPlayer()))
                .title(enroll.getTitle())
                .content(enroll.getContent())
                .position(enroll.getPosition())
                .hitCnt(enroll.getHitCnt())
                .role(enroll.getPlayer().getRole())
                .region(enroll.getPlayer().getRegion())
                .teamId(enroll.getTeam().getId())
                .createdAt(enroll.getCreatedAt())
                .updatedAt(enroll.getUpdatedAt())
                .build();
    }
}
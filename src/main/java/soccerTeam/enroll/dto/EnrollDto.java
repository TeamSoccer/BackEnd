package soccerTeam.enroll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Schema(description = "신청서 상세 내역 응답 객체")
public class EnrollDto {

    @Schema(description = "신청서 ID", example = "1")
    private Long id;

    @Schema(description = "사용자의 정보")
    private PlayerProfileResponse player;

    @NotBlank(message = "제목을 입력해주세요.")
    @Schema(description = "신청서 제목", example = "입단 신청합니다.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Schema(description = "신청서 내용", example = "이 팀에 합류하고 싶습니다.")
    private String content;

    @NotBlank(message = "선호 포지션을 입력해주세요.")
    @Schema(description = "선호하는 포지션", example = "공격수")
    private String position;

    @Schema(description = "조회수", example = "10")
    private Integer hitCnt;

    @Schema(description = "사용자의 권한", example = "ROLE_USER")
    private String role;

    @Schema(description = "사용자의 지역", example = "서울")
    private String region;

    @Schema(description = "생성된 시간", example = "2024-08-27T10:15:30")
    private LocalDateTime createdAt;

    @Schema(description = "마지막으로 수정된 시간", example = "2024-08-27T10:15:30")
    private LocalDateTime updatedAt;

    @Schema(description = "현재 사용자가 작성자인지 여부", example = "true")
    private Boolean isOwner;

    public static EnrollDto of(EnrollEntity enroll, boolean isOwner) {
        return EnrollDto.builder()
                .id(enroll.getId())
                .player(PlayerProfileResponse.of(enroll.getPlayer()))
                .title(enroll.getTitle())
                .content(enroll.getContent())
                .position(enroll.getPosition())
                .hitCnt(enroll.getHitCnt())
                .role(enroll.getPlayer().getRole())
                .region(enroll.getPlayer().getRegion())
                .createdAt(enroll.getCreatedAt())
                .updatedAt(enroll.getUpdatedAt())
                .isOwner(isOwner) // 작성자인지 여부 설정
                .build();
    }
}
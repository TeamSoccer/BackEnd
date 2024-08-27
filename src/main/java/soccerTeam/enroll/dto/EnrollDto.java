package soccerTeam.enroll.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import soccerTeam.enroll.repository.EnrollEntity;
import soccerTeam.player.dto.response.PlayerProfileResponse;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class EnrollDto {
    private Long id;
    private PlayerProfileResponse player;
    private String title;
    private String content;
    private Integer hitCnt;
    private String role;
    private String region;
    private Long teamId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static EnrollDto of(EnrollEntity enroll) {
        return EnrollDto.builder()
                .id(enroll.getId())
                .player(PlayerProfileResponse.of(enroll.getPlayer()))
                .title(enroll.getTitle())
                .content(enroll.getContent())
                .hitCnt(enroll.getHitCnt())
                .role(enroll.getPlayer().getRole())
                .region(enroll.getPlayer().getRegion())
                .teamId(enroll.getTeam().getId())
                .createdAt(enroll.getCreatedAt())
                .updatedAt(enroll.getUpdatedAt())
                .build();
    }
}

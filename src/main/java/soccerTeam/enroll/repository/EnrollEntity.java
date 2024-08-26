package soccerTeam.enroll.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.team.repository.SoccerTeamEntity;

import java.time.LocalDateTime;

@Data
@Entity
public class EnrollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SoccerTeamEntity team;

    @JoinColumn(name = "player_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PlayerEntity player;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Integer hitCnt;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public EnrollEntity(
            Long id,
            SoccerTeamEntity team,
            PlayerEntity player,
            String title,
            String content,
            Integer hitCnt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.team = team;
        this.player = player;
        this.title = title;
        this.content = content;
        this.hitCnt = hitCnt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static EnrollEntity from(PlayerEntity player, SoccerTeamEntity soccerTeam, EnrollCreateRequest enrollCreateRequest) {
        return EnrollEntity.builder()
                .team(soccerTeam)
                .player(player)
                .title(enrollCreateRequest.title())
                .content(enrollCreateRequest.content())
                .hitCnt(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

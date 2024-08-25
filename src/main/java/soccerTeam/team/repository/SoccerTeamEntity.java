package soccerTeam.team.repository;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import soccerTeam.dto.SoccerTeamListResponseDto;
import soccerTeam.team.dto.SoccerTeamDto;
import soccerTeam.dto.SoccerTeamFileDto;
import soccerTeam.player.dto.response.PlayerProfileResponse;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.team.dto.request.SoccerTeamInsertRequest;

@Data
@Entity
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SoccerTeamEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "player_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private PlayerEntity player;
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String region;

	@Column(nullable = false)
	private String phoneNumber;

	@Column
	private Integer period;

	@Column
	private String day;

	@Column
	private LocalTime startTime;

	@Column
	private LocalTime endTime;

	@Column
	private Integer ageAverage;

	@Column
	private String needPosition;

	@Column
	private String needPositionCnt;

	@Column
	private Integer athleteCnt;

	@Column(nullable = false)
	private String contents;
	
	@Column
	@ColumnDefault("0")
	private Integer hitCnt;

	@Column
	@CreatedDate
	private LocalDateTime createdAt;

	@Column
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Builder
	public SoccerTeamEntity(
			Long id,
			PlayerEntity player,
			String title,
			String name,
			String region,
			String phoneNumber,
			Integer period,
			String day,
			LocalTime startTime,
			LocalTime endTime,
			Integer ageAverage,
			String needPosition,
			String needPositionCnt,
			Integer athleteCnt,
			String contents,
			Integer hitCnt,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.player = player;
		this.title = title;
		this.name = name;
		this.region = region;
		this.phoneNumber = phoneNumber;
		this.period = period;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.ageAverage = ageAverage;
		this.needPosition = needPosition;
		this.needPositionCnt = needPositionCnt;
		this.athleteCnt = athleteCnt;
		this.contents = contents;
		this.hitCnt = hitCnt;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static SoccerTeamEntity from(SoccerTeamInsertRequest request, PlayerEntity player) {
		return SoccerTeamEntity.builder()
				.player(player)
				.title(request.getTitle())
				.name(request.getName())
				.region(request.getRegion())
				.phoneNumber(request.getPhoneNumber())
				.period(request.getPeriod())
				.day(request.getDay())
				.startTime(request.getStartTime())
				.endTime(request.getEndTime())
				.ageAverage(request.getAgeAverage())
				.needPosition(request.getNeedPosition())
				.needPositionCnt(request.getNeedPositionCnt())
				.athleteCnt(request.getAthleteCnt())
				.contents(request.getContents())
				.hitCnt(0)
				.build();
	}

	public SoccerTeamDto toModel(List<SoccerTeamFileDto> files) {
		return new SoccerTeamDto(
				id,
				PlayerProfileResponse.of(player),
				title,
				name,
				region,
				phoneNumber,
				period,
				day,
				startTime,
				endTime,
				ageAverage,
				needPosition,
				needPositionCnt,
				athleteCnt,
				contents,
				hitCnt,
				createdAt,
				updatedAt,
				files
		);
	}

	public SoccerTeamListResponseDto toDto() {
		return new SoccerTeamListResponseDto(
				this.getId(),
				this.getTitle(),
				this.getName(),
				this.getRegion(),
				this.getDay(),
				this.getStartTime(),
				this.getEndTime(),
				this.getCreatedAt(),
				this.getUpdatedAt()
		);
	}
}

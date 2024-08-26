package soccerTeam.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SoccerTeamFileDto {
	private Long id;
	private Long teamId;
	private String originImageName;
	private String imageUrl;
	private Long size;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@Builder
	public SoccerTeamFileDto(
			Long id,
			Long teamId,
			String originImageName,
			String imageUrl,
			Long size,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.teamId = teamId;
		this.originImageName = originImageName;
		this.imageUrl = imageUrl;
		this.size = size;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}

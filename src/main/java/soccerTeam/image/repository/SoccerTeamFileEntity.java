package soccerTeam.image.repository;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import soccerTeam.dto.SoccerTeamFileDto;
import soccerTeam.team.repository.SoccerTeamEntity;

@Data
@Entity
public class SoccerTeamFileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id", nullable = false)
	private SoccerTeamEntity soccerTeam;
	
	@Column(nullable = false)
	private String originImageName;
	
	@Column(nullable = false)
	private String imageUrl;
	
	@Column(nullable = false)
	private Long size;

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public SoccerTeamFileEntity(
			Long id,
			SoccerTeamEntity soccerTeam,
			String originImageName,
			String imageUrl,
			Long size,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.soccerTeam = soccerTeam;
		this.originImageName = originImageName;
		this.imageUrl = imageUrl;
		this.size = size;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public SoccerTeamFileDto toModel() {
		return SoccerTeamFileDto.builder()
				.teamId(soccerTeam.getId())
				.originImageName(originImageName)
				.imageUrl(imageUrl)
				.size(size)
				.createdAt(createdAt)
				.updatedAt(updatedAt)
				.build();
	}
}

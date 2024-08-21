package soccerTeam.entity;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team_jpa_file")
@NoArgsConstructor
@Data
public class SoccerTeamFileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int teamFileIdx;
	
//	@Column(nullable = false)
//	private int teamIdx;
	
	@Column(nullable = false)
	private String originalFileName;
	
	@Column(nullable = false)
	private String storedFilePath;
	
	@Column(nullable = false)
	private String fileSize;
	
	@Column(nullable = false)
	private LocalDateTime createdDatetime = LocalDateTime.now();
	
	@Column(nullable = false)
	private String creatorId;
	
	private LocalDateTime updatedDatetime;
	
	private String updatorId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_idx", nullable = false)
	private SoccerTeamEntity soccerTeam;
}

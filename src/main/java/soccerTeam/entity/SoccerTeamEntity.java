package soccerTeam.entity;


import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team_jpa_soccerTeam")
@NoArgsConstructor
@Data
public class SoccerTeamEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int teamIdx;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String contents;
	
	@Column(nullable = false)
	private int hitCnt = 0;
	
	@Column(nullable = false)
	private LocalDateTime createdDatetime = LocalDateTime.now();
	
	@Column(nullable = false)
	private String creatorId;
	
	private LocalDateTime updatedDatetime;
	
	private String updatorId;
	
	@OneToMany(mappedBy = "soccerTeam", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		private Collection<SoccerTeamFileEntity> fileInfoList;
}

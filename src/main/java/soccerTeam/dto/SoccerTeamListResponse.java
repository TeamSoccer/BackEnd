package soccerTeam.dto;

import lombok.Data;
import soccerTeam.team.repository.SoccerTeamEntity;

@Data
public class SoccerTeamListResponse {
	private int teamIdx;
	private String title;
	private int hitCnt;
	private String createdDatetime;
}

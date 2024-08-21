package soccerTeam.dto;

import lombok.Data;

@Data
public class SoccerTeamListResponse {
	private int teamIdx;
	private String title;
	private int hitCnt;
	private String createdDatetime;
}

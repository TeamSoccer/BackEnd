package soccerTeam.dto;

import lombok.Data;

@Data
public class SoccerTeamFileDto {
	private int teamFileIdx;
	private int teamIdx;
	private String originalFileName;
	private String storedFilePath;
	private String fileSize;
	private String createdDatetime;
	private String creatorId;
	private String upadtedDatetime;
	private String updatorId;
}

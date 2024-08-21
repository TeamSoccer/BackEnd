package soccerTeam.service;

import java.util.List;

import soccerTeam.dto.PlayerDto;

public interface PlayerService {
	
	List<PlayerDto> selectPlayerList();

	void insertPlayer(PlayerDto playerDto);
	
	void updatePlayer(PlayerDto playerDto);

	PlayerDto selectPlayerDetail(int playerIdx);

	void deletePlayer(int playerIdx);
	
	// teamIdx로 필터링된 플레이어 목록 조회 메서드
    List<PlayerDto> selectPlayersByTeamIdx(int teamIdx);

}

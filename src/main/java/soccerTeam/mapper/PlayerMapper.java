package soccerTeam.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import soccerTeam.dto.PlayerDto;

@Mapper
public interface PlayerMapper {
	
    List<PlayerDto> selectPlayerList();
    
    void insertPlayer(PlayerDto playerDto);
    
    void updatePlayer(PlayerDto playerDto);
    
    PlayerDto selectPlayerDetail(int playerIdx);
    
    void updateHitCount(int playerIdx);
    
    void deletePlayer(PlayerDto playerDto);
    
 // teamIdx로 필터링된 플레이어 목록 조회 메서드 추가
    List<PlayerDto> selectPlayersByTeamIdx(int teamIdx);
}


package soccerTeam.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import soccerTeam.dto.PlayerDto;
import soccerTeam.mapper.PlayerMapper;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerMapper playerMapper;
	
	@Override
	public List<PlayerDto> selectPlayerList() {
		return playerMapper.selectPlayerList();
	}
	
	@Override
	public void insertPlayer(PlayerDto playerDto) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		playerDto.setCreatorId(username);
		
		playerMapper.insertPlayer(playerDto);
	}
	
	@Override
    public void updatePlayer(PlayerDto playerDto) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
        playerDto.setUpdatorId(username);
        playerMapper.updatePlayer(playerDto);
    }

	@Override
	public PlayerDto selectPlayerDetail(int playerIdx) {
		playerMapper.updateHitCount(playerIdx);
		return playerMapper.selectPlayerDetail(playerIdx);
	}

	@Override
	public void deletePlayer(int playerIdx) {
		PlayerDto playerDto = new PlayerDto();
		playerDto.setPlayerIdx(playerIdx);
		playerDto.setUpdatorId("go");
		playerMapper.deletePlayer(playerDto);
		
	}
	
	@Override
    public List<PlayerDto> selectPlayersByTeamIdx(int teamIdx) {
        return playerMapper.selectPlayersByTeamIdx(teamIdx);
    }
}

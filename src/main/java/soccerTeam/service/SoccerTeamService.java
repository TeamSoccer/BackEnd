package soccerTeam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import soccerTeam.dto.SoccerTeamDto;
import soccerTeam.dto.SoccerTeamFileDto;

@Service
public interface SoccerTeamService {
	
	List<SoccerTeamDto> selectSoccerTeamList();

	void insertSoccerTeam(SoccerTeamDto soccerTeamDto, MultipartFile[] files) throws Exception;
	
	void updateSoccerTeam(SoccerTeamDto soccerTeamDto);

	SoccerTeamDto selectSoccerTeamDetail(int teamIdx);

	void deleteSoccerTeam(int teamIdx);
	
	SoccerTeamFileDto selectSoccerTeamFileInfo(int teamFileIdx, int teamIdx);

	void insertSoccerTeamWithFile(SoccerTeamDto soccerTeamDto, MultipartFile[] files) throws Exception;

}

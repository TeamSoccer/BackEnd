package soccerTeam.team.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import soccerTeam.dto.SoccerTeamDto;
import soccerTeam.dto.SoccerTeamFileDto;
import soccerTeam.team.dto.request.SoccerTeamInsertRequest;
import soccerTeam.team.repository.SoccerTeamEntity;

@Service
public interface SoccerTeamService {
	
	List<SoccerTeamEntity> selectSoccerTeamList();

	void insertSoccerTeam(SoccerTeamInsertRequest soccerTeamInsertRequest, MultipartFile[] files);
	
	void updateSoccerTeam(SoccerTeamDto soccerTeamDto);

	SoccerTeamDto selectSoccerTeamDetail(Long teamIdx);

	void deleteSoccerTeam(Long teamIdx);
	
	SoccerTeamFileDto selectSoccerTeamFileInfo(Long id);
}

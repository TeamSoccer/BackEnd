package soccerTeam.team.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import soccerTeam.dto.SoccerTeamListResponseDto;
import soccerTeam.team.dto.SoccerTeamDto;
import soccerTeam.dto.SoccerTeamFileDto;
import soccerTeam.team.dto.request.SoccerTeamInsertRequest;
import soccerTeam.team.dto.request.SoccerTeamUpdateRequest;
import soccerTeam.team.repository.SoccerTeamEntity;

@Service
public interface SoccerTeamService {
	
	List<SoccerTeamListResponseDto> selectSoccerTeamList();

	void insertSoccerTeam(String username, SoccerTeamInsertRequest soccerTeamInsertRequest, MultipartFile[] files);
	
	void updateSoccerTeam(String username, SoccerTeamUpdateRequest updateRequest, MultipartFile[] files);

	SoccerTeamDto selectSoccerTeamDetail(Long teamIdx);

	void deleteSoccerTeam(Long teamIdx);
	
	SoccerTeamFileDto selectSoccerTeamFileInfo(Long id);
}

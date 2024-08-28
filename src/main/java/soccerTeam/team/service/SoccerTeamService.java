package soccerTeam.team.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import soccerTeam.team.dto.response.SoccerTeamListResponseDto;
import soccerTeam.team.dto.SoccerTeamDto;
import soccerTeam.team.dto.SoccerTeamFileDto;
import soccerTeam.team.dto.request.SoccerTeamInsertRequest;
import soccerTeam.team.dto.request.SoccerTeamUpdateRequest;

@Service
public interface SoccerTeamService {
	
	List<SoccerTeamListResponseDto> selectSoccerTeamList();

	void insertSoccerTeam(String username, SoccerTeamInsertRequest soccerTeamInsertRequest, MultipartFile[] files);
	
	void updateSoccerTeam(String username, SoccerTeamUpdateRequest updateRequest, MultipartFile[] files);

	SoccerTeamDto selectSoccerTeamDetail(Long teamIdx, String token);

	void deleteSoccerTeam(Long teamIdx);
	
	SoccerTeamFileDto selectSoccerTeamFileInfo(Long id);

	boolean checkMethod(Long teamIdx, String token);
}

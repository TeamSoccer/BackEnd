package soccerTeam.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import soccerTeam.entity.SoccerTeamEntity;
import soccerTeam.entity.SoccerTeamFileEntity;

public interface JpaSoccerTeamService {
	List<SoccerTeamEntity> selectSoccerTeamList();
	// 등록과 수정을 분리
	void insertSoccerTeam(SoccerTeamEntity soccerTeamEntity, MultipartHttpServletRequest request) throws Exception;
	void insertSoccerTeam(SoccerTeamEntity soccerTeamEntity, MultipartFile[] files);
	void updateSoccerTeam(SoccerTeamEntity soccerTeamEntity) throws Exception;
	void deleteSoccerTeam(int teamIdx);
	SoccerTeamFileEntity selectSoccerTeamFileInfo(int teamFileIdx, int teamIdx);
	SoccerTeamEntity selectSoccerTeamDetail(int teamIdx) throws Exception;
	

}

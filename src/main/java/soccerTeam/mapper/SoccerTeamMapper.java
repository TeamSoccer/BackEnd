package soccerTeam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import soccerTeam.dto.SoccerTeamDto;
import soccerTeam.dto.SoccerTeamFileDto;

@Mapper
public interface SoccerTeamMapper {
	List<SoccerTeamDto> selectSoccerTeamList();

	void insertSoccerTeam(SoccerTeamDto soccerTeamDto);

	void updateSoccerTeam(SoccerTeamDto soccerTeamDto);
	
	SoccerTeamDto selectSoccerTeamDetail(int teamIdx);

	void updateHitCount(int teamIdx);

	void deleteSoccerTeam(SoccerTeamDto soccerTeamDto);
	
	void insertSoccerTeamFileList(List<SoccerTeamFileDto> fileInfoList);
	
	List<SoccerTeamFileDto> selectSoccerTeamFileList(int teamIdx);
	
	SoccerTeamFileDto selectSoccerTeamFileInfo(@Param("teamFileIdx") int teamFileIdx, @Param("teamIdx") int teamIdx);
}

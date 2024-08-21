package soccerTeam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import soccerTeam.entity.SoccerTeamEntity;
import soccerTeam.entity.SoccerTeamFileEntity;

public interface JpaSoccerTeamRepository extends CrudRepository<SoccerTeamEntity, Integer> {
	// teamIdx를 내림차순으로 정렬한 데이터를 조회
	List<SoccerTeamEntity> findAllByOrderByTeamIdxDesc();
	
	@Query("SELECT file FROM SoccerTeamFileEntity file WHERE file.teamFileIdx = :teamFileIdx AND file.soccerTeam.teamIdx = :teamIdx")
	public SoccerTeamFileEntity findSoccerTeamFile(@Param("teamFileIdx") int teamFileIdx, @Param("teamIdx") int teamIdx);
}

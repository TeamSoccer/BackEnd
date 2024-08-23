package soccerTeam.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaSoccerTeamFileRepository extends JpaRepository<SoccerTeamFileEntity, Long> {
    @Query("SELECT sfe FROM SoccerTeamFileEntity sfe WHERE sfe.soccerTeam = :teamId")
    List<SoccerTeamFileEntity> findByTeamId(@Param("teamId") Long teamId);
}

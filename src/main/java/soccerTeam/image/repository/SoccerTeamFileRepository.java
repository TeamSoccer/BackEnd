package soccerTeam.image.repository;

import java.util.List;
import java.util.Optional;

public interface SoccerTeamFileRepository {
    void saveAll(List<SoccerTeamFileEntity> fileInfoList);

    List<SoccerTeamFileEntity> findByTeamId(Long teamId);

    Optional<SoccerTeamFileEntity> findById(Long id);
}

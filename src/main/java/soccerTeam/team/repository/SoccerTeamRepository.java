package soccerTeam.team.repository;

import soccerTeam.team.SoccerTeamUpdateDto;
import soccerTeam.team.dto.SoccerTeamDto;

import java.util.List;
import java.util.Optional;

public interface SoccerTeamRepository {
    List<SoccerTeamEntity> findAll();

    SoccerTeamEntity save(SoccerTeamEntity soccerTeamEntity);

    Optional<SoccerTeamEntity> updateHitCount(Long teamIdx);

    Optional<SoccerTeamEntity> findById(Long id);

    Optional<SoccerTeamEntity> update(String username, SoccerTeamUpdateDto soccerTeamDto);

    void deleteById(Long id);
}

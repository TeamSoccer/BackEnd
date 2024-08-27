package soccerTeam.enroll.repository;

import soccerTeam.team.repository.SoccerTeamEntity;

import java.util.List;
import java.util.Optional;

public interface EnrollRepository {
    EnrollEntity save(EnrollEntity enroll);

    List<EnrollEntity> findByTeam(SoccerTeamEntity soccerTeam);

    Optional<EnrollEntity> findByIdAndUpdateHitCnt(Long id);

    void deleteById(Long id);
}
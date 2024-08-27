package soccerTeam.enroll.repository;

import soccerTeam.enroll.dto.EnrollUpdateDto;
import soccerTeam.team.SoccerTeamUpdateDto;
import soccerTeam.team.repository.SoccerTeamEntity;

import java.util.List;
import java.util.Optional;

public interface EnrollRepository {
    EnrollEntity save(EnrollEntity enroll);

    List<EnrollEntity> findByTeam(SoccerTeamEntity soccerTeam);

    Optional<EnrollEntity> findById(Long id);

    Optional<EnrollEntity> update(String username, EnrollUpdateDto enrollUpdateDto);
}

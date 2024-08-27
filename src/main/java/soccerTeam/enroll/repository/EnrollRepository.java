package soccerTeam.enroll.repository;

import soccerTeam.team.repository.SoccerTeamEntity;

import java.util.List;

public interface EnrollRepository {
    EnrollEntity save(EnrollEntity enroll);

    List<EnrollEntity> findByTeam(SoccerTeamEntity soccerTeam);
}

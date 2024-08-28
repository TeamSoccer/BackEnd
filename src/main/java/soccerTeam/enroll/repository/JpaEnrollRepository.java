package soccerTeam.enroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccerTeam.team.repository.SoccerTeamEntity;

import java.util.List;

public interface JpaEnrollRepository extends JpaRepository<EnrollEntity, Long> {
    List<EnrollEntity> findByTeam(SoccerTeamEntity team);
}

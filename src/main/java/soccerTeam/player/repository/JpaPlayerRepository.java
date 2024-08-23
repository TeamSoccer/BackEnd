package soccerTeam.player.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPlayerRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByUsername(String username);
}

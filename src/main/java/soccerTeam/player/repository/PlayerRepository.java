package soccerTeam.player.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import soccerTeam.player.repository.PlayerEntity;

import java.util.Optional;

public interface PlayerRepository {
	// username 중복 여부를 검사하는 쿼리 메서드
	boolean existsByUsername(String username);

	Optional<PlayerEntity> findByUsername(String username);

	PlayerEntity save(PlayerEntity playerEntity);
}

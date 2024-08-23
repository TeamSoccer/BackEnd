package soccerTeam.player.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository {
    private final JpaPlayerRepository jpaPlayerRepository;

    @Override
    public boolean existsByUsername(String username) {
        return jpaPlayerRepository.findByUsername(username).isPresent();
    }

    @Override
    public Optional<PlayerEntity> findByUsername(String username) {
        return jpaPlayerRepository.findByUsername(username);
    }

    @Override
    public PlayerEntity save(PlayerEntity playerEntity) {
        return jpaPlayerRepository.save(playerEntity);
    }
}

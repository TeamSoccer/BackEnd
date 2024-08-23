package soccerTeam.player.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soccerTeam.exception.NotFoundException;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.player.repository.PlayerRepository;
import soccerTeam.type.player.PlayerErrorType;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public PlayerEntity findByUsername(String username) {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(PlayerErrorType.PLAYER_NOT_FOUND));
    }
}

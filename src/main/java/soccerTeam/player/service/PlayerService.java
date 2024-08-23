package soccerTeam.player.service;

import soccerTeam.player.repository.PlayerEntity;

public interface PlayerService {
    PlayerEntity findByUsername(String username);
}

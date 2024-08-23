package soccerTeam.player.service;

import org.springframework.security.core.userdetails.UserDetails;
import soccerTeam.player.repository.PlayerEntity;

public interface PlayerService {
    PlayerEntity findByUsername(String username);
}

package soccerTeam.player.service;

import soccerTeam.dto.JoinDto;
import soccerTeam.player.dto.response.PlayerSimpleResponse;

public interface JoinService {
    PlayerSimpleResponse joinProcess(JoinDto joinDto);
}

package soccerTeam.player.service;

import soccerTeam.player.dto.request.JoinDto;
import soccerTeam.player.dto.response.PlayerSimpleResponse;

public interface JoinService {
    PlayerSimpleResponse joinProcess(JoinDto joinDto);
}

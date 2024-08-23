package soccerTeam.player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import soccerTeam.dto.ApiResponse;
import soccerTeam.dto.JoinDto;
import soccerTeam.player.dto.response.PlayerProfileResponse;
import soccerTeam.player.dto.response.PlayerSimpleResponse;
import soccerTeam.player.service.JoinService;
import soccerTeam.type.player.PlayerSuccessType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/join")
public class RestJoinController {
    private final JoinService joinService;

    @PostMapping
    public ApiResponse<PlayerSimpleResponse> join(@RequestBody JoinDto joinDto) {
        PlayerSimpleResponse response = joinService.joinProcess(joinDto);
        return ApiResponse.success(PlayerSuccessType.JOIN_SUCCESS, response);
    }
}

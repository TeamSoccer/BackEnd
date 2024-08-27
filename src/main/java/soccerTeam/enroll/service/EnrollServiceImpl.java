package soccerTeam.enroll.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soccerTeam.enroll.dto.*;
import soccerTeam.enroll.repository.EnrollEntity;
import soccerTeam.enroll.repository.EnrollRepository;
import soccerTeam.exception.NotFoundException;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.player.repository.PlayerRepository;
import soccerTeam.team.SoccerTeamUpdateDto;
import soccerTeam.team.repository.SoccerTeamEntity;
import soccerTeam.team.repository.SoccerTeamRepository;
import soccerTeam.type.enroll.EnrollErrorType;
import soccerTeam.type.player.PlayerErrorType;
import soccerTeam.type.soccerTeam.SoccerTeamErrorType;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollServiceImpl implements EnrollService {
    private final EnrollRepository enrollRepository;
    private final PlayerRepository playerRepository;
    private final SoccerTeamRepository soccerTeamRepository;

    @Override
    public EnrollCreateResponse create(String username, EnrollCreateRequest enrollCreateRequest) {
        PlayerEntity player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(PlayerErrorType.PLAYER_NOT_FOUND));
        SoccerTeamEntity soccerTeam = soccerTeamRepository.findById(enrollCreateRequest.teamId())
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));
        EnrollEntity enroll = EnrollEntity.from(player, soccerTeam, enrollCreateRequest);
        enroll = enrollRepository.save(enroll);
        return EnrollCreateResponse.of(enroll);
    }

    // enroll-post
    @Override
    public List<EnrollListResponse> getEnrollListByTeam(Long teamIdx) {
        SoccerTeamEntity soccerTeam = soccerTeamRepository.findById(teamIdx)
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));
        List<EnrollEntity> enrolls = enrollRepository.findByTeam(soccerTeam);
        return enrolls.stream()
                .map(EnrollListResponse::from)
                .toList();
    }

    @Override
    public void updateEnroll(String username, EnrollUpdateRequest updateRequest) {
        EnrollEntity enroll = enrollRepository.findById(updateRequest.id())
                .orElseThrow(() -> new NotFoundException(EnrollErrorType.ENROLL_NOT_FOUND));

        enrollRepository.update(username, EnrollUpdateDto.from(updateRequest));
    }
}

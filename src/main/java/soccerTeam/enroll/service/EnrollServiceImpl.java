package soccerTeam.enroll.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.dto.EnrollDto;
import soccerTeam.enroll.dto.EnrollListResponse;
import soccerTeam.enroll.repository.EnrollEntity;
import soccerTeam.enroll.repository.EnrollRepository;
import soccerTeam.exception.NotFoundException;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.player.repository.PlayerRepository;
import soccerTeam.team.repository.SoccerTeamEntity;
import soccerTeam.team.repository.SoccerTeamRepository;
import soccerTeam.type.player.PlayerErrorType;
import soccerTeam.type.soccerTeam.SoccerTeamErrorType;

import java.util.List;
import java.util.stream.Collectors;
import soccerTeam.enroll.dto.EnrollDto;

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
    public EnrollDto selectEnrollDetail(Long enrollId) {
        EnrollEntity enrollEntity = enrollRepository.updateHitCount(enrollId)
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));

        return EnrollDto.of(enrollEntity);
    }
}

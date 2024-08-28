package soccerTeam.enroll.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.dto.EnrollDto;
import soccerTeam.enroll.dto.EnrollListResponse;
import soccerTeam.enroll.repository.EnrollEntity;
import soccerTeam.enroll.repository.EnrollRepository;
import soccerTeam.enroll.repository.JpaEnrollRepository;
import soccerTeam.exception.NotFoundException;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.player.repository.PlayerRepository;
import soccerTeam.team.repository.SoccerTeamEntity;
import soccerTeam.team.repository.SoccerTeamRepository;
import soccerTeam.type.enroll.EnrollErrorType;
import soccerTeam.type.enroll.EnrollSuccessType;
import soccerTeam.type.player.PlayerErrorType;
import soccerTeam.type.soccerTeam.SoccerTeamErrorType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollServiceImpl implements EnrollService {
    private final EnrollRepository enrollRepository;
    private final PlayerRepository playerRepository;
    private final SoccerTeamRepository soccerTeamRepository;
    private final JpaEnrollRepository jpaEnrollRepository;

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
    public EnrollDto findByIdAndUpdateHitCnt(Long id) {
        EnrollEntity enrollEntity = enrollRepository.findByIdAndUpdateHitCnt(id)
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));

        return EnrollDto.of(enrollEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id, String username) {
        try {
            EnrollEntity enroll = jpaEnrollRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(EnrollErrorType.NOT_FOUND));

            if (!enroll.getPlayer().getUsername().equals(username)) {
                throw new NotFoundException(EnrollErrorType.NOT_OWNED_BY_USER);
            }
            jpaEnrollRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("삭제 중 예기치 못한 오류가 발생했습니다.", e);
        }
    }
}

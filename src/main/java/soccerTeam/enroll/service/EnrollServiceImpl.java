package soccerTeam.enroll.service;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import soccerTeam.common.JwtUtils;
import soccerTeam.enroll.dto.*;
import org.springframework.transaction.annotation.Transactional;
import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.dto.EnrollDto;
import soccerTeam.enroll.dto.EnrollListResponse;
import soccerTeam.enroll.repository.EnrollEntity;
import soccerTeam.enroll.repository.EnrollRepository;
import soccerTeam.exception.BadRequestException;
import soccerTeam.enroll.repository.JpaEnrollRepository;
import soccerTeam.exception.NotFoundException;
import soccerTeam.exception.UnauthorizedException;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.player.repository.PlayerRepository;
import soccerTeam.team.repository.SoccerTeamEntity;
import soccerTeam.team.repository.SoccerTeamRepository;
import soccerTeam.type.enroll.EnrollErrorType;
import soccerTeam.type.enroll.EnrollSuccessType;
import soccerTeam.type.player.PlayerErrorType;
import soccerTeam.type.soccerTeam.SoccerTeamErrorType;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j  // 이 애너테이션으로 log 객체를 초기화합니다.
@Service
@RequiredArgsConstructor
public class EnrollServiceImpl implements EnrollService {
    private final EnrollRepository enrollRepository;
    private final PlayerRepository playerRepository;
    private final SoccerTeamRepository soccerTeamRepository;
    private final JpaEnrollRepository jpaEnrollRepository;
    private final JwtUtils jwtUtils;

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

    // update-enroll
    @Override
    @Transactional
    public EnrollUpdateDto updateEnroll(String username, EnrollUpdateRequest updateRequest) {
        EnrollEntity enroll = enrollRepository.findById(updateRequest.id())
                .orElseThrow(() -> new NotFoundException(EnrollErrorType.ENROLL_NOT_FOUND));

        PlayerEntity player = enroll.getPlayer();
        if (!player.getUsername().equals(username)) {
            throw new BadRequestException(EnrollErrorType.ONLY_OWNER_CAN_MODIFY);
        }

        updateRequest.updateTo(enroll);
        return EnrollUpdateDto.of(enroll);
    }

    public EnrollDto findByIdAndUpdateHitCnt(Long id, String token) {
        EnrollEntity enrollEntity = enrollRepository.findByIdAndUpdateHitCnt(id)
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));

        boolean isOwner = checkOwner(enrollEntity, token);

        return EnrollDto.of(enrollEntity, isOwner);
    }

    private boolean checkOwner(EnrollEntity enrollEntity, String token) {
        String jwtToken = jwtUtils.extractTokenFromAuthorizationHeader(token);
        if (jwtToken == null) return false;
        String username = jwtUtils.getUsername(jwtToken);
        return enrollEntity.getPlayer().getUsername().equals(username);
    }

    @Override
    @Transactional
    public void deleteById(Long id, String token) {
        EnrollEntity enroll = jpaEnrollRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EnrollErrorType.ENROLL_NOT_FOUND));

        boolean isOwner = checkOwner(enroll, token);
        if (!isOwner) {
            throw new UnauthorizedException(EnrollErrorType.ONLY_OWNER_CAN_MODIFY);
        }
        jpaEnrollRepository.deleteById(id);
    }
}

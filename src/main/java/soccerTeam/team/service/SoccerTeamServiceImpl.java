package soccerTeam.team.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import soccerTeam.common.FileUtils;
import soccerTeam.common.JwtUtils;
import soccerTeam.team.dto.response.SoccerTeamListResponseDto;
import soccerTeam.team.SoccerTeamUpdateDto;
import soccerTeam.team.dto.SoccerTeamDto;
import soccerTeam.team.dto.SoccerTeamFileDto;
import soccerTeam.exception.NotFoundException;
import soccerTeam.image.repository.SoccerTeamFileEntity;
import soccerTeam.image.repository.SoccerTeamFileRepository;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.player.repository.PlayerRepository;
import soccerTeam.team.dto.request.SoccerTeamInsertRequest;
import soccerTeam.team.dto.request.SoccerTeamUpdateRequest;
import soccerTeam.team.repository.SoccerTeamEntity;
import soccerTeam.team.repository.SoccerTeamRepository;
import soccerTeam.type.player.PlayerErrorType;
import soccerTeam.type.soccerTeam.SoccerTeamErrorType;

@Slf4j
@Service
@RequiredArgsConstructor
public class SoccerTeamServiceImpl implements SoccerTeamService {

    private final SoccerTeamRepository soccerTeamRepository;
    private final SoccerTeamFileRepository soccerTeamFileRepository;
    private final PlayerRepository playerRepository;
    private final FileUtils fileUtils;
    private final JwtUtils jwtUtils;

    @Override
    public List<SoccerTeamListResponseDto> selectSoccerTeamList() {
        return soccerTeamRepository.findAll().stream()
                .map(SoccerTeamEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void insertSoccerTeam(String username, SoccerTeamInsertRequest soccerTeamInsertRequest, MultipartFile[] files) {
        PlayerEntity player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(PlayerErrorType.PLAYER_NOT_FOUND));
        SoccerTeamEntity soccerTeamEntity = soccerTeamRepository
                .save(SoccerTeamEntity.from(soccerTeamInsertRequest, player));

        saveFiles(files, soccerTeamEntity);
    }

    @Override
    @Transactional
    public void updateSoccerTeam(String username, SoccerTeamUpdateRequest updateRequest, MultipartFile[] files) {
        SoccerTeamEntity team = soccerTeamRepository.findById(updateRequest.id())
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));
        deleteFilesByTeamId(updateRequest.id());
        saveFiles(files, team);
        soccerTeamRepository.update(username, SoccerTeamUpdateDto.from(updateRequest))
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));
    }

    @Override
    @Transactional
    public SoccerTeamDto selectSoccerTeamDetail(Long teamIdx, String token) {
        SoccerTeamEntity soccerTeam = soccerTeamRepository.updateHitCount(teamIdx)
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));

        List<SoccerTeamFileEntity> soccerTeamFileInfo = soccerTeamFileRepository.findByTeamId(teamIdx);
        List<SoccerTeamFileDto> files = soccerTeamFileInfo.stream().map(file ->
                SoccerTeamFileDto.builder()
                        .id(file.getId())
                        .teamId(file.getSoccerTeam().getId())
                        .originImageName(file.getOriginImageName())
                        .imageUrl(file.getImageUrl())
                        .size(file.getSize())
                        .createdAt(file.getCreatedAt())
                        .updatedAt(file.getUpdatedAt())
                        .build()
        ).toList();
        boolean isOwner = checkMethod(teamIdx, token); // 팀 소유자 여부 확인
        return soccerTeam.toModel(files, isOwner); // isOwner 값을 추가로 전달
    }
    @Override
    public boolean checkMethod(Long teamIdx, String token) {
        SoccerTeamEntity soccerTeam = soccerTeamRepository.findById(teamIdx)
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));
        String jwtToken = extractTokenFromAuthorizationHeader(token);
        if (jwtToken == null) return false;
        String username = jwtUtils.getUsername(jwtToken);

        log.info("JWT username: {}", username);
        log.info("Team owner's username: {}", soccerTeam.getPlayer().getUsername());

        if (soccerTeam.getPlayer().getUsername().equals(username)) {
            return true;
        }
        return false;
    }
    private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteSoccerTeam(Long teamIdx) {
        soccerTeamRepository.deleteById(teamIdx);
        deleteFilesByTeamId(teamIdx);
    }

    private void deleteFilesByTeamId(Long teamIdx) {
        List<SoccerTeamFileEntity> files = soccerTeamFileRepository.findByTeamId(teamIdx);
        fileUtils.deleteFiles(files.stream().map(SoccerTeamFileEntity::getImageUrl).toList());
        soccerTeamFileRepository.deleteByTeamId(teamIdx);
    }

    private void saveFiles(MultipartFile[] files, SoccerTeamEntity soccerTeamEntity) {
        // 첨부 파일을 저장하고 첨부 파일 정보를 반환
        List<SoccerTeamFileDto> fileInfoList = fileUtils.parseFileInfo(soccerTeamEntity.getId(), files);

        // 첨부 파일 정보를 저장
        if (CollectionUtils.isEmpty(fileInfoList)) {
            return;
        }
        List<SoccerTeamFileEntity> fileList = fileInfoList
                .stream()
                .map(file -> SoccerTeamFileEntity.from(file, soccerTeamEntity))
                .toList();
        soccerTeamFileRepository.saveAll(fileList);
    }

    @Override
    public SoccerTeamFileDto selectSoccerTeamFileInfo(Long id) {
        SoccerTeamFileEntity file = soccerTeamFileRepository.findById(id)
                .orElse(null);
        return file == null ? null : file.toModel();
    }
}

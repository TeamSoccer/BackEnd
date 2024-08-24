package soccerTeam.team.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import soccerTeam.common.FileUtils;
import soccerTeam.dto.SoccerTeamListResponseDto;
import soccerTeam.team.SoccerTeamUpdateDto;
import soccerTeam.team.dto.SoccerTeamDto;
import soccerTeam.dto.SoccerTeamFileDto;
import soccerTeam.exception.NotFoundException;
import soccerTeam.image.repository.SoccerTeamFileEntity;
import soccerTeam.image.repository.SoccerTeamFileRepository;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.player.repository.PlayerRepository;
import soccerTeam.team.dto.request.SoccerTeamInsertRequest;
import soccerTeam.team.dto.request.SoccerTeamUpdateRequest;
import soccerTeam.team.repository.SoccerTeamEntity;
import soccerTeam.team.repository.SoccerTeamRepository;
import soccerTeam.type.soccerTeam.SoccerTeamErrorType;

@Slf4j
@Service
@RequiredArgsConstructor
public class SoccerTeamServiceImpl implements SoccerTeamService {

    private final SoccerTeamRepository soccerTeamRepository;
    private final SoccerTeamFileRepository soccerTeamFileRepository;
    private final PlayerRepository playerRepository;
    private final FileUtils fileUtils;

    @Override
    public List<SoccerTeamListResponseDto> selectSoccerTeamList() {
        return soccerTeamRepository.findAll().stream()
                .map(SoccerTeamEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void insertSoccerTeam(SoccerTeamInsertRequest soccerTeamInsertRequest, MultipartFile[] files) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        PlayerEntity player = playerRepository.findByUsername(username)
                .orElseThrow();
        SoccerTeamEntity soccerTeamEntity = soccerTeamRepository
                .save(SoccerTeamEntity.from(soccerTeamInsertRequest, player));

        // 첨부 파일을 저장하고 첨부 파일 정보를 반환
        List<SoccerTeamFileDto> fileInfoList = fileUtils.parseFileInfo(soccerTeamEntity.getId(), files);

        // 첨부 파일 정보를 저장
        if (CollectionUtils.isEmpty(fileInfoList)) {
            return;
        }
        List<SoccerTeamFileEntity> fileList = fileInfoList.stream().map(file -> SoccerTeamFileEntity.builder()
                .soccerTeam(soccerTeamEntity)
                .originImageName(file.getOriginImageName())
                .imageUrl(file.getImageUrl())
                .size(file.getSize())
                .build()
        ).toList();
        soccerTeamFileRepository.saveAll(fileList);
    }

    @Override
    @Transactional
    public void updateSoccerTeam(String username, SoccerTeamUpdateRequest updateRequest) {
        soccerTeamRepository.update(username, SoccerTeamUpdateDto.from(updateRequest))
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));
    }

    @Override
    @Transactional
    public SoccerTeamDto selectSoccerTeamDetail(Long teamIdx) {
        SoccerTeamEntity soccerTeam = soccerTeamRepository.updateHitCount(teamIdx)
                .orElseThrow(() -> new NotFoundException(SoccerTeamErrorType.TEAM_NOT_FOUND));

        List<SoccerTeamFileEntity> soccerTeamFileInfo = soccerTeamFileRepository.findByTeamId(teamIdx);
        List<SoccerTeamFileDto> files = soccerTeamFileInfo.stream().map(file ->
                SoccerTeamFileDto.builder()
                        .teamId(file.getSoccerTeam().getId())
                        .originImageName(file.getOriginImageName())
                        .imageUrl(file.getImageUrl())
                        .size(file.getSize())
                        .createdAt(file.getCreatedAt())
                        .updatedAt(file.getUpdatedAt())
                        .build()
        ).toList();
        return soccerTeam.toModel(files);
    }

    @Override
    public void deleteSoccerTeam(Long teamIdx) {
        soccerTeamRepository.deleteById(teamIdx);
    }

    @Override
    public SoccerTeamFileDto selectSoccerTeamFileInfo(Long id) {
        SoccerTeamFileEntity file = soccerTeamFileRepository.findById(id)
                .orElse(null);
        return file == null ? null : file.toModel();
    }
}

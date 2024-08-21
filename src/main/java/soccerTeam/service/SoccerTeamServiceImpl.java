package soccerTeam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import soccerTeam.common.FileUtils;
import soccerTeam.dto.SoccerTeamDto;
import soccerTeam.dto.SoccerTeamFileDto;
import soccerTeam.mapper.SoccerTeamMapper;

@Slf4j
@Service
public class SoccerTeamServiceImpl implements SoccerTeamService {

    @Autowired
    private SoccerTeamMapper soccerTeamMapper;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public List<SoccerTeamDto> selectSoccerTeamList() {
        return soccerTeamMapper.selectSoccerTeamList();
    }

    @Override
    public void insertSoccerTeam(SoccerTeamDto soccerTeamDto, MultipartFile[] files) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	soccerTeamDto.setCreatorId(username);
        soccerTeamMapper.insertSoccerTeam(soccerTeamDto);

        // 첨부 파일을 저장하고 첨부 파일 정보를 반환
        List<SoccerTeamFileDto> fileInfoList = fileUtils.parseFileInfo(soccerTeamDto.getTeamIdx(), files);

        // 첨부 파일 정보를 저장
        if (!CollectionUtils.isEmpty(fileInfoList)) {
            soccerTeamMapper.insertSoccerTeamFileList(fileInfoList);
        }
    }

    @Override
    public void updateSoccerTeam(SoccerTeamDto soccerTeamDto) {
        soccerTeamDto.setUpdatorId("go");
        soccerTeamMapper.updateSoccerTeam(soccerTeamDto);
    }

    @Transactional
    @Override
    public SoccerTeamDto selectSoccerTeamDetail(int teamIdx) {
        soccerTeamMapper.updateHitCount(teamIdx);

        SoccerTeamDto soccerTeamDto = soccerTeamMapper.selectSoccerTeamDetail(teamIdx);
        if (soccerTeamDto != null) {
            List<SoccerTeamFileDto> soccerTeamFileInfo = soccerTeamMapper.selectSoccerTeamFileList(teamIdx);
            soccerTeamDto.setFileInfoList(soccerTeamFileInfo);
        }
        return soccerTeamDto;
    }

    @Override
    public void deleteSoccerTeam(int teamIdx) {
        SoccerTeamDto soccerTeamDto = new SoccerTeamDto();
        soccerTeamDto.setTeamIdx(teamIdx);

        soccerTeamDto.setUpdatorId("go");
        soccerTeamMapper.deleteSoccerTeam(soccerTeamDto);
    }

    @Override
    public SoccerTeamFileDto selectSoccerTeamFileInfo(int teamFileIdx, int teamIdx) {
        return soccerTeamMapper.selectSoccerTeamFileInfo(teamFileIdx, teamIdx);
    }

    @Override
    public void insertSoccerTeamWithFile(SoccerTeamDto soccerTeamDto, MultipartFile[] files) throws Exception {
        // TODO. 로그인한 사용자의 ID로 변경
        soccerTeamDto.setCreatorId("hong");
        soccerTeamMapper.insertSoccerTeam(soccerTeamDto);

        // 첨부 파일을 저장하고 첨부 파일 정보를 반환
        // TODO 내일 이어서 구현 ...
        List<SoccerTeamFileDto> fileInfoList = fileUtils.parseFileInfo(soccerTeamDto.getTeamIdx(), files);

        // 첨부 파일 정보를 저장
        if (!CollectionUtils.isEmpty(fileInfoList)) {
            soccerTeamMapper.insertSoccerTeamFileList(fileInfoList);
        }
    }
}

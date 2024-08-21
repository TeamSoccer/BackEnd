package soccerTeam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import soccerTeam.common.FileUtils;
import soccerTeam.entity.SoccerTeamEntity;
import soccerTeam.entity.SoccerTeamFileEntity;
import soccerTeam.repository.JpaSoccerTeamRepository;

@Service
public class JpaSoccerTeamServiceImpl implements JpaSoccerTeamService {

	@Autowired
	private JpaSoccerTeamRepository jpaSoccerTeamRepository;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<SoccerTeamEntity> selectSoccerTeamList() {
		return jpaSoccerTeamRepository.findAllByOrderByTeamIdxDesc();
	}

	@Override
	public void insertSoccerTeam(SoccerTeamEntity soccerTeamEntity, MultipartHttpServletRequest request) throws Exception {
		List<SoccerTeamFileEntity> list = fileUtils.parseFileInfo(request);
		if (list != null) {
			for (SoccerTeamFileEntity file : list) {
				file.setSoccerTeam(soccerTeamEntity);
			}
			soccerTeamEntity.setFileInfoList(list);
		}
		// TODO. 로그인한 사용자 계정으로 변경
		soccerTeamEntity.setCreatorId("tester");
		
		// 리포지터의 save 메서드는 insert와 update 두 가지 역할을 수행
		jpaSoccerTeamRepository.save(soccerTeamEntity);
	}

	@Override
	public void updateSoccerTeam(SoccerTeamEntity soccerTeamEntity) throws Exception {
		// TODO. 로그인한 사용자 계정으로 변경
		soccerTeamEntity.setCreatorId("tester");
		
		// 리포지터의 save 메서드는 insert와 update 두 가지 역할을 수행
		jpaSoccerTeamRepository.save(soccerTeamEntity);

	}

	@Override
	public void deleteSoccerTeam(int teamIdx) {
		jpaSoccerTeamRepository.deleteById(teamIdx);

	}

	@Override
	public SoccerTeamFileEntity selectSoccerTeamFileInfo(int teamFileIdx, int teamIdx) {
		return jpaSoccerTeamRepository.findSoccerTeamFile(teamFileIdx, teamIdx);
	}

	@Override
	public SoccerTeamEntity selectSoccerTeamDetail(int teamIdx) throws Exception {
		Optional<SoccerTeamEntity> optional = jpaSoccerTeamRepository.findById(teamIdx);
		if (optional.isPresent()) {
			SoccerTeamEntity soccerTeamEntity = optional.get();
			soccerTeamEntity.setHitCnt(soccerTeamEntity.getHitCnt() + 1);
			jpaSoccerTeamRepository.save(soccerTeamEntity);
			return soccerTeamEntity;
		} else {
			throw new Exception("일치하는 데이터가 없음");
		}
	}

	@Override
	public void insertSoccerTeam(SoccerTeamEntity soccerTeamEntity, MultipartFile[] files) {
		
	}

}

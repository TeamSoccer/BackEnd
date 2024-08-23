package soccerTeam.team.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soccerTeam.dto.SoccerTeamDto;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SoccerTeamRepositoryImpl implements SoccerTeamRepository {
    private final JpaSoccerTeamRepository jpaSoccerTeamRepository;

    @Override
    public List<SoccerTeamEntity> findAll() {
        return jpaSoccerTeamRepository.findAll();
    }

    @Override
    public SoccerTeamEntity save(SoccerTeamEntity soccerTeamEntity) {
        return jpaSoccerTeamRepository.save(soccerTeamEntity);
    }

    @Override
    public Optional<SoccerTeamEntity> updateHitCount(Long teamIdx) {
        Optional<SoccerTeamEntity> soccerTeam = findById(teamIdx);
        if (soccerTeam.isPresent()) {
            SoccerTeamEntity soccerTeamEntity = soccerTeam.get();
            soccerTeamEntity.setHitCnt(soccerTeamEntity.getHitCnt() + 1);
            return Optional.of(soccerTeamEntity);
        }
        return soccerTeam;
    }

    @Override
    public Optional<SoccerTeamEntity> findById(Long id) {
        return jpaSoccerTeamRepository.findById(id);
    }

    @Override
    public Optional<SoccerTeamEntity> update(SoccerTeamDto soccerTeamDto) {
        Optional<SoccerTeamEntity> soccerTeam = findById(soccerTeamDto.getId());
        if (soccerTeam.isPresent()) {
            SoccerTeamEntity soccerTeamEntity = soccerTeam.get();
            soccerTeamEntity.setTitle(soccerTeamDto.getTitle());
            soccerTeamEntity.setName(soccerTeamDto.getName());
            soccerTeamEntity.setRegion(soccerTeamDto.getRegion());
            soccerTeamEntity.setPhoneNumber(soccerTeamDto.getPhoneNumber());
            soccerTeamEntity.setPeriod(soccerTeamDto.getPeriod());
            soccerTeamEntity.setDay(soccerTeamDto.getDay());
            soccerTeamEntity.setEndTime(soccerTeamDto.getEndTime());
            soccerTeamEntity.setStartTime(soccerTeamDto.getStartTime());
            soccerTeamEntity.setEndTime(soccerTeamDto.getEndTime());
            soccerTeamEntity.setAgeAverage(soccerTeamDto.getAgeAverage());
            soccerTeamEntity.setNeedPosition(soccerTeamDto.getNeedPosition());
            soccerTeamEntity.setNeedPositionCnt(soccerTeamDto.getNeedPositionCnt());
            soccerTeamEntity.setAthleteCnt(soccerTeamDto.getAthleteCnt());
            soccerTeamEntity.setContents(soccerTeamDto.getContents());
            return Optional.of(soccerTeamEntity);
        }
        return soccerTeam;
    }

    @Override
    public void deleteById(Long id) {
        jpaSoccerTeamRepository.deleteById(id);
    }
}

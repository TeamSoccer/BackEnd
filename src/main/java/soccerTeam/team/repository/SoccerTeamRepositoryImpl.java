package soccerTeam.team.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soccerTeam.exception.BadRequestException;
import soccerTeam.team.SoccerTeamUpdateDto;
import soccerTeam.team.dto.SoccerTeamDto;
import soccerTeam.team.dto.request.SoccerTeamUpdateRequest;
import soccerTeam.type.soccerTeam.SoccerTeamErrorType;

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
    public Optional<SoccerTeamEntity> update(String username, SoccerTeamUpdateDto soccerTeamDto) {
        Optional<SoccerTeamEntity> soccerTeam = findById(soccerTeamDto.id());
        if (soccerTeam.isPresent()) {
            SoccerTeamEntity soccerTeamEntity = soccerTeam.get();
            if (!soccerTeamEntity.getPlayer().getUsername().equals(username)) {
                throw new BadRequestException(SoccerTeamErrorType.TEAM_OWNER_CAN_MODIFY);
            }
            soccerTeamEntity.setTitle(soccerTeamDto.title());
            soccerTeamEntity.setName(soccerTeamDto.name());
            soccerTeamEntity.setRegion(soccerTeamDto.region());
            soccerTeamEntity.setPhoneNumber(soccerTeamDto.phoneNumber());
            soccerTeamEntity.setPeriod(soccerTeamDto.period());
            soccerTeamEntity.setDay(soccerTeamDto.day());
            soccerTeamEntity.setEndTime(soccerTeamDto.endTime());
            soccerTeamEntity.setStartTime(soccerTeamDto.startTime());
            soccerTeamEntity.setEndTime(soccerTeamDto.endTime());
            soccerTeamEntity.setAgeAverage(soccerTeamDto.ageAverage());
            soccerTeamEntity.setNeedPosition(soccerTeamDto.needPosition());
            soccerTeamEntity.setNeedPositionCnt(soccerTeamDto.needPositionCnt());
            soccerTeamEntity.setAthleteCnt(soccerTeamDto.athleteCnt());
            soccerTeamEntity.setContents(soccerTeamDto.contents());
            return Optional.of(soccerTeamEntity);
        }
        return soccerTeam;
    }

    @Override
    public void deleteById(Long id) {
        jpaSoccerTeamRepository.deleteById(id);
    }
}

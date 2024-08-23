package soccerTeam.image.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SoccerTeamFileRepositoryImpl implements SoccerTeamFileRepository {
    private final JpaSoccerTeamFileRepository jpaSoccerTeamFileRepository;

    @Override
    public void saveAll(List<SoccerTeamFileEntity> fileInfoList) {
        jpaSoccerTeamFileRepository.saveAll(fileInfoList);
    }

    @Override
    public List<SoccerTeamFileEntity> findByTeamId(Long teamId) {
        return jpaSoccerTeamFileRepository.findByTeamId(teamId);
    }

    @Override
    public Optional<SoccerTeamFileEntity> findById(Long id) {
        return jpaSoccerTeamFileRepository.findById(id);
    }
}

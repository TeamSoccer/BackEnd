package soccerTeam.enroll.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soccerTeam.team.repository.SoccerTeamEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EnrollRepositoryImpl implements EnrollRepository {
    private final JpaEnrollRepository jpaEnrollRepository;

    @Override
    public EnrollEntity save(EnrollEntity enroll) {
        return jpaEnrollRepository.save(enroll);
    }

    @Override
    public List<EnrollEntity> findByTeam(SoccerTeamEntity team) {
        return jpaEnrollRepository.findByTeam(team);
    }

    @Override
    @Transactional
    public Optional<EnrollEntity> findByIdAndUpdateHitCnt(Long id) {
        return jpaEnrollRepository.findById(id)
                .map(enroll -> {
                    enroll.setHitCnt(enroll.getHitCnt() + 1);
                    return enroll;
                });
    }

    @Override
    public void deleteById(Long id) {
        jpaEnrollRepository.deleteById(id);
    }
}

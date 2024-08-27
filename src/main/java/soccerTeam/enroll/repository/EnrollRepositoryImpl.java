package soccerTeam.enroll.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import soccerTeam.exception.BadRequestException;
import soccerTeam.enroll.dto.EnrollUpdateDto;
import soccerTeam.player.repository.PlayerEntity;
import soccerTeam.team.repository.SoccerTeamEntity;
import soccerTeam.type.enroll.EnrollErrorType;

import java.time.LocalDateTime;
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
    public Optional<EnrollEntity> findById(Long id) {
        return jpaEnrollRepository.findById(id);
    }

    @Override
    public Optional<EnrollEntity> update(String username, EnrollUpdateDto enrollUpdateDto) {
        Optional<EnrollEntity> enroll = findById(enrollUpdateDto.id());
        if (enroll.isPresent()) {
            EnrollEntity enrollEntity = enroll.get();
            PlayerEntity player = enrollEntity.getPlayer();

            if (!player.getUsername().equals(username)) {
                throw new BadRequestException(EnrollErrorType.ONLY_OWNER_CAN_MODIFY);
            }

            enrollEntity.setTitle(enrollUpdateDto.title());
            enrollEntity.setContent(enrollUpdateDto.content());
            enrollEntity.setUpdatedAt(LocalDateTime.now());
            return Optional.of(jpaEnrollRepository.save(enrollEntity));
        }
        return enroll;
    }
}

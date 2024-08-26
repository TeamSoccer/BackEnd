package soccerTeam.enroll.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EnrollRepositoryImpl implements EnrollRepository {
    private final JpaEnrollRepository jpaEnrollRepository;

    @Override
    public EnrollEntity save(EnrollEntity enroll) {
        return jpaEnrollRepository.save(enroll);
    }
}

package soccerTeam.enroll.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.repository.EnrollRepository;

@Service
@RequiredArgsConstructor
public class EnrollServiceImpl implements EnrollService {
    private final EnrollRepository enrollRepository;

    @Override
    public EnrollCreateResponse create(EnrollCreateRequest enrollCreateRequest) {
        return null;
    }
}

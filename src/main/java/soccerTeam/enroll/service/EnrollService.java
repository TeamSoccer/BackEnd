package soccerTeam.enroll.service;

import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.dto.EnrollDto;
import soccerTeam.enroll.dto.EnrollListResponse;
import soccerTeam.enroll.dto.EnrollUpdateRequest;

import java.util.List;

public interface EnrollService {
    EnrollCreateResponse create(String username, EnrollCreateRequest enrollCreateRequest);

    List<EnrollListResponse> getEnrollListByTeam(Long teamId);

    void updateEnroll(String username, EnrollUpdateRequest updateRequest);

    EnrollDto findByIdAndUpdateHitCnt(Long id);

    void deleteById(Long id);
}

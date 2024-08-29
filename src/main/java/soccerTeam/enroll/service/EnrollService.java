package soccerTeam.enroll.service;

import soccerTeam.enroll.dto.*;

import java.util.List;

public interface EnrollService {
    EnrollCreateResponse create(String username, EnrollCreateRequest enrollCreateRequest);

    List<EnrollListResponse> getEnrollListByTeam(Long teamId);

    EnrollUpdateDto updateEnroll(String username, EnrollUpdateRequest updateRequest);

    EnrollDto findByIdAndUpdateHitCnt(Long id, String token);

    void deleteById(Long id, String username);
}

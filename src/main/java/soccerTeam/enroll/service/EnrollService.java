package soccerTeam.enroll.service;

import jakarta.validation.Valid;
import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.dto.EnrollListResponse;
import soccerTeam.enroll.dto.EnrollUpdateRequest;

import java.util.List;

public interface EnrollService {
    EnrollCreateResponse create(String username, EnrollCreateRequest enrollCreateRequest);

    // enroll-post
    List<EnrollListResponse> getEnrollListByTeam(Long teamId);

    void updateEnroll(String username, @Valid EnrollUpdateRequest updateRequest);
}

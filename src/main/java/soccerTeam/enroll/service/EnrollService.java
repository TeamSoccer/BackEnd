package soccerTeam.enroll.service;

import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;

public interface EnrollService {
    EnrollCreateResponse create(String username, EnrollCreateRequest enrollCreateRequest);
}

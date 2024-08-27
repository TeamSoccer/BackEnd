package soccerTeam.enroll.service;

import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.dto.EnrollDto;
import soccerTeam.enroll.dto.EnrollListResponse;

import java.util.List;

public interface EnrollService {
    EnrollCreateResponse create(String username, EnrollCreateRequest enrollCreateRequest);

    List<EnrollListResponse> getEnrollListByTeam(Long teamId);

    EnrollDto findByIdAndUpdateHitCnt(Long id);

    void deleteById(Long id);

}

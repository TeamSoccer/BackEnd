package soccerTeam.enroll.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soccerTeam.dto.ApiResponse;
import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.service.EnrollService;
import soccerTeam.type.enroll.EnrollSuccessType;

@RestController
@RequestMapping("/api/enroll")
@RequiredArgsConstructor
public class EnrollController {
    private final EnrollService enrollService;

    @PostMapping
    public ApiResponse<EnrollCreateResponse> create(@RequestBody EnrollCreateRequest enrollCreateRequest) {
        return ApiResponse.success(EnrollSuccessType.CREATE_SUCCESS, enrollService.create(enrollCreateRequest));
    }
}

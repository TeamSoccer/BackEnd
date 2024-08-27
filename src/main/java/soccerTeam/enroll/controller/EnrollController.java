package soccerTeam.enroll.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import soccerTeam.dto.ApiResponse;
import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.dto.EnrollDto;
import soccerTeam.enroll.dto.EnrollListResponse;
import soccerTeam.enroll.service.EnrollService;
import soccerTeam.security.LoginMember;
import soccerTeam.type.enroll.EnrollSuccessType;

import java.util.List;

@RestController
@RequestMapping("/api/enroll")
@RequiredArgsConstructor
public class EnrollController {
    private final EnrollService enrollService;

    @PostMapping
    public ApiResponse<EnrollCreateResponse> create(
            @LoginMember String username,
            @Valid @RequestBody EnrollCreateRequest enrollCreateRequest) {
        EnrollCreateResponse response = enrollService.create(username, enrollCreateRequest);
        return ApiResponse.success(EnrollSuccessType.CREATE_SUCCESS, response);
    }

    // enroll-post
    @GetMapping("/team/{teamIdx}")
    public ApiResponse<List<EnrollListResponse>> getEnrollListByTeam(@PathVariable("teamIdx") Long teamIdx) {
        List<EnrollListResponse> response = enrollService.getEnrollListByTeam(teamIdx);
        return ApiResponse.success(EnrollSuccessType.LIST_SUCCESS, response);
    }

    @GetMapping("/{enrollId}")
    public ApiResponse<EnrollDto> getEnrollDetail(@PathVariable("enrollId") Long enrollId) {
        EnrollDto enrollDto = enrollService.selectEnrollDetail(enrollId);
        return ApiResponse.success(EnrollSuccessType.ENROLL_SUCCESS, enrollDto);
    }
}

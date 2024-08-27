package soccerTeam.enroll.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Enroll API", description = "Enroll API 관련 작업")
public class EnrollController {

    private final EnrollService enrollService;

    @Operation(summary = "Enroll 생성", description = "새로운 Enroll을 생성합니다.")
    @PostMapping
    public ApiResponse<EnrollCreateResponse> create(
            @LoginMember
            @Parameter(description = "로그인한 사용자명", required = true) String username,
            @Valid @RequestBody
            @Parameter(description = "Enroll 생성 요청 객체", required = true) EnrollCreateRequest enrollCreateRequest) {
        EnrollCreateResponse response = enrollService.create(username, enrollCreateRequest);
        return ApiResponse.success(EnrollSuccessType.CREATE_SUCCESS, response);
    }

    @Operation(summary = "입단 신청자 목록 조회", description = "입단 신청자 목록을 조회합니다.")
    @GetMapping("/team/{teamIdx}")
    public ApiResponse<List<EnrollListResponse>> getEnrollListByTeam(
            @Parameter(description = "팀 인덱스", required = true) @PathVariable("teamIdx") Long teamIdx) {
        List<EnrollListResponse> response = enrollService.getEnrollListByTeam(teamIdx);
        return ApiResponse.success(EnrollSuccessType.LIST_SUCCESS, response);
    }

    @Operation(summary = "입단 신청자 상세 조회", description = "입단 신청자의 상세 정보를 조회합니다.")
    @GetMapping("/{enrollId}")
    public ApiResponse<EnrollDto> getEnrollDetail(
            @Parameter(description = "Enroll ID", required = true) @PathVariable("enrollId") Long enrollId) {
        EnrollDto enrollDto = enrollService.findByIdAndUpdateHitCnt(enrollId);
        return ApiResponse.success(EnrollSuccessType.ENROLL_SUCCESS, enrollDto);
    }
}

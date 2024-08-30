package soccerTeam.enroll.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import soccerTeam.dto.ApiResponse;
import soccerTeam.enroll.dto.*;
import soccerTeam.enroll.service.EnrollService;
import soccerTeam.security.LoginMember;
import soccerTeam.type.enroll.EnrollSuccessType;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/enroll")
@RequiredArgsConstructor
@Tag(name = "입단 신청서 API", description = "입단 신청서 API 관련 작업")
public class EnrollController {

    private final EnrollService enrollService;

    @Operation(summary = "입단 신청서 생성", description = "새로운 신청서를 생성합니다.")
    @PostMapping
    public ApiResponse<EnrollCreateResponse> create(
            @LoginMember String username,
            @Valid @RequestBody EnrollCreateRequest enrollCreateRequest) {
        EnrollCreateResponse response = enrollService.create(username, enrollCreateRequest);
        return ApiResponse.success(EnrollSuccessType.CREATE_SUCCESS, response);
    }

    @Operation(summary = "입단 신청서 목록 조회", description = "입단 신청서 목록을 조회합니다.")
    @GetMapping("/team/{teamId}")
    public ApiResponse<List<EnrollListResponse>> getEnrollListByTeam(
            @Parameter(description = "팀 인덱스", required = true) @PathVariable("teamId") Long teamId) {
        List<EnrollListResponse> response = enrollService.getEnrollListByTeam(teamId);
        return ApiResponse.success(EnrollSuccessType.LIST_SUCCESS, response);
    }

    @Operation(summary = "입단 신청서 수정", description = "입단 신청서를 수정합니다.")
    @PutMapping
    public ApiResponse<EnrollUpdateDto> updateEnroll(
            @LoginMember String username,
            @Valid @RequestBody EnrollUpdateRequest updateRequest) {
        EnrollUpdateDto updatedEnroll = enrollService.updateEnroll(username, updateRequest);
        return ApiResponse.success(EnrollSuccessType.UPDATE_SUCCESS, updatedEnroll);
    }

    @Operation(summary = "입단 신청서 상세 조회", description = "입단 신청서를 상세 조회합니다.")
    @GetMapping("/{enrollId}")
    public ApiResponse<EnrollDto> getEnrollDetail(
            @PathVariable("enrollId") Long enrollId,
            @RequestHeader("Authorization") String token) {
        EnrollDto enrollDto = enrollService.findByIdAndUpdateHitCnt(enrollId, token);
        return ApiResponse.success(EnrollSuccessType.ENROLL_SUCCESS, enrollDto);
    }

    @Operation(summary = "입단 신청서 삭제", description = "입단 신청서를 삭제합니다.")
    @DeleteMapping("/{enrollId}")
    public ApiResponse<Void> deleteEnroll(
            @PathVariable("enrollId") Long enrollId,
            @LoginMember String username) {
        enrollService.deleteById(enrollId, username);
        return ApiResponse.success(EnrollSuccessType.DELETE_SUCCESS, null);
    }
}

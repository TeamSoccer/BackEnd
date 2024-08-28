package soccerTeam.enroll.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccerTeam.dto.ApiResponse;
import soccerTeam.enroll.dto.EnrollCreateRequest;
import soccerTeam.enroll.dto.EnrollCreateResponse;
import soccerTeam.enroll.dto.EnrollDto;
import soccerTeam.enroll.dto.EnrollListResponse;
import soccerTeam.enroll.dto.EnrollUpdateRequest;
import soccerTeam.enroll.service.EnrollService;
import soccerTeam.security.LoginMember;
import soccerTeam.type.enroll.EnrollSuccessType;
import lombok.extern.slf4j.Slf4j;

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
            @LoginMember
            String username,
            @Valid @RequestBody
            EnrollCreateRequest enrollCreateRequest) {
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
    public ApiResponse<?> updateEnroll(
            @LoginMember String username,
            @Valid @RequestBody EnrollUpdateRequest updateRequest) {
        enrollService.updateEnroll(username, updateRequest);
        return ApiResponse.success(EnrollSuccessType.UPDATE_SUCCESS);
    }

    @Operation(summary = "입단 신청서 상세 조회", description = "입단 신청서를 상세 조회합니다.")
    @GetMapping("/{enrollId}")
    public ApiResponse<EnrollDto> getEnrollDetail(
            @Parameter(description = "Enroll id", required = true) @PathVariable("enrollId") Long enrollId) {
        EnrollDto enrollDto = enrollService.findByIdAndUpdateHitCnt(enrollId);
        return ApiResponse.success(EnrollSuccessType.ENROLL_SUCCESS, enrollDto);
    }

    @Operation(summary = "입단 신청서 삭제", description = "입단 신청서를 삭제합니다.")
    @DeleteMapping("/{enrollId}")
    public ResponseEntity<Void> deleteEnroll(@PathVariable("enrollId") Long enrollId) {
        log.info("Delete request received for enrollId: {}", enrollId);
        try {
            enrollService.deleteById(enrollId);
            log.info("Successfully deleted enrollId: {}", enrollId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Error occurred while deleting enrollId: {}", enrollId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

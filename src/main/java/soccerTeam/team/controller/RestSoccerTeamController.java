package soccerTeam.team.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import soccerTeam.dto.ApiResponse;
import soccerTeam.team.dto.response.SoccerTeamListResponseDto;
import soccerTeam.security.LoginMember;
import soccerTeam.team.dto.SoccerTeamDto;
import soccerTeam.team.dto.SoccerTeamFileDto;
import soccerTeam.team.dto.request.SoccerTeamInsertRequest;
import soccerTeam.team.dto.request.SoccerTeamUpdateRequest;
import soccerTeam.team.service.SoccerTeamService;
import soccerTeam.type.soccerTeam.SoccerTeamSuccessType;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/soccerTeam")
public class RestSoccerTeamController {

    private final SoccerTeamService soccerTeamService;
    @Operation(summary = "게시판 목록 조회", description = "등록된 게시물 목록을 조회해서 반환합니다.")
    @GetMapping
    public ApiResponse<List<SoccerTeamListResponseDto>> getAllSoccerTeams() {
        List<SoccerTeamListResponseDto> response = soccerTeamService.selectSoccerTeamList();
        return ApiResponse.success(SoccerTeamSuccessType.GET_SOCCER_TEAM_LIST_SUCCESS, response);
    }

    @Operation(summary = "게시판 등록", description = "게시물 제목과 내용을 저장합니다.")
    @Parameter(name = "soccerTeamDto", description = "게시물 정보를 담고 있는 객체", required = true)
    @PostMapping("/write")
    public ApiResponse<?> insertSoccerTeam(
            @LoginMember String username,
            @Valid @RequestPart(value = "data") SoccerTeamInsertRequest soccerTeamInsertRequest,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {

        // 로깅 추가
        System.out.println("Received data: " + soccerTeamInsertRequest);
        System.out.println("Received files: " + (files != null ? files.length : "No files"));

        soccerTeamService.insertSoccerTeam(username, soccerTeamInsertRequest, files);
        return ApiResponse.success(SoccerTeamSuccessType.CREATE_SOCCER_TEAM_SUCCESS);
    }

    @GetMapping("/{teamIdx}")
    public ApiResponse<SoccerTeamDto> getSoccerTeamDetail(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("teamIdx") Long teamIdx) {
        SoccerTeamDto soccerTeamDtoResult = soccerTeamService.selectSoccerTeamDetail(teamIdx, authorizationHeader);
        return ApiResponse.success(SoccerTeamSuccessType.GET_SOCCER_TEAM_SUCCESS,soccerTeamDtoResult);
    }

    @PutMapping
    public ApiResponse<?> updateSoccerTeam(
            @LoginMember String username,
            @Valid @RequestPart(value = "data") SoccerTeamUpdateRequest updateRequest,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {
        soccerTeamService.updateSoccerTeam(username, updateRequest, files);
        return ApiResponse.success(SoccerTeamSuccessType.UPDATE_SOCCER_TEAM_SUCCESS);
    }

    @DeleteMapping("/{teamIdx}")
    public ResponseEntity<Void> deleteSoccerTeam(@PathVariable("teamIdx") Long teamIdx) {
        soccerTeamService.deleteSoccerTeam(teamIdx);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/file/{teamFileIdx}")
    public void downloadSoccerTeamFile(@PathVariable("teamFileIdx") Long teamFileIdx, HttpServletResponse response) throws IOException {
        SoccerTeamFileDto soccerTeamFileDto = soccerTeamService.selectSoccerTeamFileInfo(teamFileIdx);
        if (ObjectUtils.isEmpty(soccerTeamFileDto)) {
            return;
        }

        Path path = Paths.get(soccerTeamFileDto.getImageUrl());
        byte[] file = Files.readAllBytes(path);

        response.setContentType("application/octet-stream");
        response.setContentLength(file.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(soccerTeamFileDto.getOriginImageName(), "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(file);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
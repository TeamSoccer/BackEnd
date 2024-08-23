package soccerTeam.team.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import soccerTeam.dto.*;
import soccerTeam.security.LoginMember;
import soccerTeam.team.dto.request.SoccerTeamInsertRequest;
import soccerTeam.team.service.SoccerTeamService;
import soccerTeam.team.repository.SoccerTeamEntity;
import soccerTeam.type.soccerTeam.SoccerTeamSuccessType;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/soccerTeam")
public class RestSoccerTeamController {

    private final SoccerTeamService soccerTeamService;

    @Operation(summary = "게시판 목록 조회", description = "등록된 게시물 목록을 조회해서 반환합니다.")
    @GetMapping
    public ApiResponse<List<SoccerTeamEntity>> getAllSoccerTeams() {
        return ApiResponse.success(SoccerTeamSuccessType.GET_SOCCER_TEAM_LIST_SUCCESS, soccerTeamService.selectSoccerTeamList());
    }
    
    @Operation(summary = "게시판 등록", description = "게시물 제목과 내용을 저장합니다.")
    @Parameter(name = "soccerTeamDto", description = "게시물 정보를 담고 있는 객체", required = true)
    @PostMapping("/write")
    public ResponseEntity<Void> insertSoccerTeam(
            @ModelAttribute SoccerTeamInsertRequest soccerTeamInsertRequest,
            @RequestParam(value = "files", required = false) MultipartFile[] files) {
        try {
            // 로깅 추가
            System.out.println("Received data: " + soccerTeamInsertRequest);
            System.out.println("Received files: " + (files != null ? files.length : "No files"));

            soccerTeamService.insertSoccerTeam(soccerTeamInsertRequest, files);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace(); // 예외 로그 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{teamIdx}")
    public ApiResponse<SoccerTeamDto> getSoccerTeamDetail(@PathVariable("teamIdx") Long teamIdx) throws Exception {
        SoccerTeamDto soccerTeamDtoResult = soccerTeamService.selectSoccerTeamDetail(teamIdx);
//        if (soccerTeamDtoResult == null) {
//            Map<String, Object> result = new HashMap<>();
//            result.put("code", HttpStatus.NOT_FOUND.toString());
//            result.put("message", "일치하는 팀이 존재하지 않습니다.");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
//        } else {
//            List<PlayerDto> playerList = playerService.selectPlayersByTeamIdx(teamIdx);
//            Map<String, Object> response = new HashMap<>();
//            response.put("soccerTeam", soccerTeamDtoResult);
//            response.put("playerList", playerList);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }
        return ApiResponse.success(SoccerTeamSuccessType.GET_SOCCER_TEAM_SUCCESS, soccerTeamDtoResult);
    }

    @PutMapping
    public ResponseEntity<Void> updateSoccerTeam(@RequestBody SoccerTeamDto soccerTeamDto) {
        soccerTeamService.updateSoccerTeam(soccerTeamDto);
        return ResponseEntity.status(HttpStatus.OK).build();
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
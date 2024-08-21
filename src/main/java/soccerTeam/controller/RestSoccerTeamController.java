package soccerTeam.controller;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import soccerTeam.dto.PlayerDto;
import soccerTeam.dto.SoccerTeamDto;
import soccerTeam.dto.SoccerTeamFileDto;
import soccerTeam.dto.SoccerTeamInsertRequest;
import soccerTeam.service.PlayerService;
import soccerTeam.service.SoccerTeamService;

@Slf4j
@RestController
@RequestMapping("/api/soccerTeam")
public class RestSoccerTeamController {

    @Autowired
    private SoccerTeamService soccerTeamService;

    @Autowired
    private PlayerService playerService;

    @CrossOrigin(origins="http://localhost:3000")
    @Operation(summary = "게시판 목록 조회", description = "등록된 게시물 목록을 조회해서 반환합니다.")
    @GetMapping
    public List<SoccerTeamDto> getAllSoccerTeams() throws Exception {
        return soccerTeamService.selectSoccerTeamList();
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @Operation(summary = "게시판 등록", description = "게시물 제목과 내용을 저장합니다.")
    @Parameter(name = "soccerTeamDto", description = "게시물 정보를 담고 있는 객체", required = true)
    @PostMapping("/write")
    public ResponseEntity<Void> insertSoccerTeam(
            @RequestPart(value = "data", required = true) SoccerTeamInsertRequest soccerTeamInsertRequest,
            @RequestPart(value = "files", required = false) MultipartFile[] files) throws Exception {
        try {
            // 로깅 추가
            System.out.println("Received data: " + soccerTeamInsertRequest);
            System.out.println("Received files: " + (files != null ? files.length : "No files"));

            SoccerTeamDto soccerTeamDto = new ModelMapper().map(soccerTeamInsertRequest, SoccerTeamDto.class);
            soccerTeamService.insertSoccerTeam(soccerTeamDto, files);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace(); // 예외 로그 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/{teamIdx}")
    public ResponseEntity<Map<String, Object>> getSoccerTeamDetail(@PathVariable("teamIdx") int teamIdx) throws Exception {
        SoccerTeamDto soccerTeamDtoResult = soccerTeamService.selectSoccerTeamDetail(teamIdx);
        if (soccerTeamDtoResult == null) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", HttpStatus.NOT_FOUND.toString());
            result.put("message", "일치하는 팀이 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            List<PlayerDto> playerList = playerService.selectPlayersByTeamIdx(teamIdx);
            Map<String, Object> response = new HashMap<>();
            response.put("soccerTeam", soccerTeamDtoResult);
            response.put("playerList", playerList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @CrossOrigin(origins="http://localhost:3000")
    @PutMapping("/{teamIdx}/edit")
    public ResponseEntity<Void> updateSoccerTeam(@PathVariable("teamIdx") int teamIdx, @RequestBody SoccerTeamDto soccerTeamDto) throws Exception {
        soccerTeamService.updateSoccerTeam(soccerTeamDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin(origins="http://localhost:3000")
    @DeleteMapping("/{teamIdx}")
    public ResponseEntity<Void> deleteSoccerTeam(@PathVariable("teamIdx") int teamIdx) throws Exception {
        soccerTeamService.deleteSoccerTeam(teamIdx);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @CrossOrigin(origins="http://localhost:3000")
//    @GetMapping("/player")
//    public List<PlayerDto> getAllPlayers() throws Exception {
//        return playerService.selectPlayerList();
//    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/player/{playerIdx}")
    public ResponseEntity<PlayerDto> getPlayerDetail(@PathVariable("playerIdx") int playerIdx) {
        try {
            PlayerDto player = playerService.selectPlayerDetail(playerIdx);
            if (player == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(player);
        } catch (Exception e) {
            log.error("Error fetching player details: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/player/write")
    public ResponseEntity<Void> insertPlayer(@RequestBody PlayerDto playerDto) {
        try {
            // 로깅 추가
            log.info("Received player data: {}", playerDto);
            
            playerService.insertPlayer(playerDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error("Error inserting player: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin(origins="http://localhost:3000")
    @PutMapping("/player/{playerIdx}/edit")
    public ResponseEntity<Void> updatePlayer(@PathVariable("playerIdx") int playerIdx, @RequestParam("teamIdx") int teamIdx, @RequestBody PlayerDto playerDto) throws Exception {
        playerDto.setPlayerIdx(playerIdx);
        playerDto.setTeamIdx(teamIdx);
        playerService.updatePlayer(playerDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin(origins="http://localhost:3000")
    @DeleteMapping("/player/{playerIdx}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("playerIdx") int playerIdx, @RequestParam("teamIdx") int teamIdx) throws Exception {
        playerService.deletePlayer(playerIdx);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/file/{teamIdx}/{teamFileIdx}")
    public void downloadSoccerTeamFile(@PathVariable("teamFileIdx") int teamFileIdx, @PathVariable("teamIdx") int teamIdx, HttpServletResponse response) throws Exception {
        SoccerTeamFileDto soccerTeamFileDto = soccerTeamService.selectSoccerTeamFileInfo(teamFileIdx, teamIdx);
        if (ObjectUtils.isEmpty(soccerTeamFileDto)) {
            return;
        }

        Path path = Paths.get(soccerTeamFileDto.getStoredFilePath());
        byte[] file = Files.readAllBytes(path);

        response.setContentType("application/octet-stream");
        response.setContentLength(file.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(soccerTeamFileDto.getOriginalFileName(), "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(file);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
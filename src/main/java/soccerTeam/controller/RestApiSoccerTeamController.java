//package soccerTeam.controller;
//
//import java.net.URLEncoder;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import soccerTeam.dto.PlayerDto;
//import soccerTeam.dto.SoccerTeamDto;
//import soccerTeam.dto.SoccerTeamFileDto;
//import soccerTeam.dto.SoccerTeamListResponse;
//import soccerTeam.service.PlayerService;
//import soccerTeam.service.SoccerTeamService;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/v2/soccerTeam")
//public class RestApiSoccerTeamController {
//
//    @Autowired
//    private SoccerTeamService soccerTeamService;
//
//    @Autowired
//    private PlayerService playerService;
//
//    @CrossOrigin(origins="http://localhost:3000", allowedHeaders="GET")
//    @Operation(summary = "게시판 목록 조회", description ="등록된 게시물 목록을 조회해서 반환합니다.")
//    @GetMapping
//    public List<SoccerTeamListResponse> openSoccerTeamList(HttpServletRequest request) throws Exception {
//        List<SoccerTeamDto> soccerTeamList = soccerTeamService.selectSoccerTeamList();
//        
//        List<SoccerTeamListResponse> results = new ArrayList<>();
//        soccerTeamList.forEach(dto -> {
//            results.add(new ModelMapper().map(dto, SoccerTeamListResponse.class));
//        });
//        
//        return results;
//    }
//
//    @GetMapping("/write")
//    public String openSoccerTeamWrite() throws Exception {
//        return "soccerTeamWrite";
//    }
//
//    @Operation(summary = "게시판 등록", description = "게시물 제목과 내용을 저장합니다.")
//    @Parameter(name = "soccerTeamDto", description = "게시물 정보를 담고 있는 객체", required = true)
//    @PostMapping("/write")
//    public void insertSoccerTeam(@RequestPart(value="data", required=true) SoccerTeamDto soccerTeamDto, @RequestPart(value="files", required=false) MultipartFile[] files) throws Exception {
//        soccerTeamService.insertSoccerTeamWithFile(soccerTeamDto, files);
//    }
//
//    @GetMapping("/{teamIdx}")
//    public ResponseEntity<Object> openSoccerTeamDetail(@PathVariable("teamIdx") int teamIdx) throws Exception {
//        SoccerTeamDto soccerTeamDtoResult = soccerTeamService.selectSoccerTeamDetail(teamIdx);
//        if (soccerTeamDtoResult == null) {
//            Map<String, String> result = new HashMap<>();
//            result.put("code", HttpStatus.NOT_FOUND.toString());
//            result.put("message", "일치하는 팀이 존재하지 않습니다.");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
//        } else {
//            List<PlayerDto> playerList = playerService.selectPlayersByTeamIdx(teamIdx); // teamIdx로 필터링된 플레이어 목록 조회
//            Map<String, Object> response = new HashMap<>();
//            response.put("soccerTeam", soccerTeamDtoResult);
//            response.put("playerList", playerList);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }
//    }
//
//    @PostMapping("/{teamIdx}")
//    public String deleteSoccerTeam(@PathVariable("teamIdx") int teamIdx) throws Exception {
//        soccerTeamService.deleteSoccerTeam(teamIdx);
//        return "redirect:/api/v2/soccerTeam";
//    }
//
//    @GetMapping("/{teamIdx}/edit")
//    public ModelAndView openSoccerTeamModify(@PathVariable("teamIdx") int teamIdx) throws Exception {
//        ModelAndView mv = new ModelAndView("soccerTeamModify");
//        SoccerTeamDto soccerTeamDtoResult = soccerTeamService.selectSoccerTeamDetail(teamIdx);
//        mv.addObject("soccerTeam", soccerTeamDtoResult);
//        return mv;
//    }
//
//    @PostMapping("/{teamIdx}/edit")
//    public String updateSoccerTeam(@PathVariable("teamIdx") int teamIdx, @RequestBody SoccerTeamDto soccerTeamDto) throws Exception {
//        soccerTeamService.updateSoccerTeam(soccerTeamDto);
//        return "redirect:/api/v2/soccerTeam";
//    }
//
//    @GetMapping("/player")
//    public ModelAndView openPlayerList() throws Exception {
//        ModelAndView mv = new ModelAndView("playerList");
//        List<PlayerDto> player = playerService.selectPlayerList();
//        mv.addObject("player", player);
//        return mv;
//    }
//
//    @GetMapping("/player/write")
//    public String openPlayerWrite(@RequestParam("teamIdx") int teamIdx, Model model) {
//        model.addAttribute("teamIdx", teamIdx);
//        return "playerWrite";
//    }
//
//    @PostMapping("/player/write")
//    public String insertPlayer(@RequestParam("teamIdx") int teamIdx, @RequestBody PlayerDto playerDto) throws Exception {
//        playerDto.setTeamIdx(teamIdx);
//        playerService.insertPlayer(playerDto);
//        return "redirect:/api/v2/soccerTeam/" + teamIdx;
//    }
//
//    @GetMapping("/player/{playerIdx}")
//    public ModelAndView openPlayerDetail(@PathVariable("playerIdx") int playerIdx) throws Exception {
//        ModelAndView mv = new ModelAndView("playerDetail");
//        PlayerDto playerDtoResult = playerService.selectPlayerDetail(playerIdx);
//        mv.addObject("player", playerDtoResult);
//        return mv;
//    }
//
//    @PostMapping("/player/{playerIdx}")
//    public String deletePlayer(@PathVariable("playerIdx") int playerIdx, @RequestParam("teamIdx") int teamIdx) throws Exception {
//        playerService.deletePlayer(playerIdx);
//        return "redirect:/api/v2/soccerTeam/" + teamIdx;
//    }
//
//    @GetMapping("/player/{playerIdx}/edit")
//    public ModelAndView openPlayerModify(@PathVariable("playerIdx") int playerIdx) throws Exception {
//        ModelAndView mv = new ModelAndView("playerModify");
//        PlayerDto playerDtoResult = playerService.selectPlayerDetail(playerIdx);
//        mv.addObject("player", playerDtoResult);
//        return mv;
//    }
//
//    @PostMapping("/player/{playerIdx}/edit")
//    public String updatePlayer(@PathVariable("playerIdx") int playerIdx, @RequestParam("teamIdx") int teamIdx, PlayerDto playerDto) throws Exception {
//        playerDto.setPlayerIdx(playerIdx);
//        playerDto.setTeamIdx(teamIdx);
//        playerService.updatePlayer(playerDto);
//        return "redirect:/player/" + playerIdx;
//    }
//
//    @GetMapping("/file/{teamIdx}/{teamFileIdx}")
//    public void downloadSoccerTeamFile(@PathVariable("teamFileIdx") int teamFileIdx, @PathVariable("teamIdx") int teamIdx, HttpServletResponse response) throws Exception {
//        // teamFileIdx와 teamIdx가 일치하는 파일 정보를 조회
//        SoccerTeamFileDto soccerTeamFileDto = soccerTeamService.selectSoccerTeamFileInfo(teamFileIdx, teamIdx);
//        if (ObjectUtils.isEmpty(soccerTeamFileDto)) {
//            return;
//        }
//
//        // 원본 파일 저장 위치에서 파일을 읽어서 호출한 곳으로 첨부파일을 응답으로 전달
//        Path path = Paths.get(soccerTeamFileDto.getStoredFilePath());
//        byte[] file = Files.readAllBytes(path);
//
//        response.setContentType("application/octet-stream");
//        response.setContentLength(file.length);
//        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(soccerTeamFileDto.getOriginalFileName(), "UTF-8") + "\";");
//        response.setHeader("Content-Transfer-Encoding", "binary");
//
//        response.getOutputStream().write(file);
//        response.getOutputStream().flush();
//        response.getOutputStream().close();
//    }
//}

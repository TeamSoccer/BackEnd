package soccerTeam.controller;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import soccerTeam.dto.PlayerDto;
import soccerTeam.dto.SoccerTeamDto;
import soccerTeam.dto.SoccerTeamFileDto;
import soccerTeam.dto.SoccerTeamInsertRequest;
import soccerTeam.service.PlayerService;
import soccerTeam.service.SoccerTeamService;

@Controller
@RequestMapping("/soccerTeam")
public class SoccerTeamController {
    
    private final SoccerTeamService soccerTeamService;
    private final PlayerService playerService;
    
    // 생성자 주입
    public SoccerTeamController(SoccerTeamService soccerTeamService, PlayerService playerService) {
        this.soccerTeamService = soccerTeamService;
        this.playerService = playerService;
    }
    
    @GetMapping("openSoccerTeamList.do")
    public ModelAndView openSoccerTeamList() throws Exception {
        ModelAndView mv = new ModelAndView("soccerTeamList");
        
        List<SoccerTeamDto> list = soccerTeamService.selectSoccerTeamList();
        mv.addObject("list", list);
        
        return mv;
    }
    
    @GetMapping("openSoccerTeamWrite.do")
    public String openSoccerTeamWrite() throws Exception {
        return "soccerTeamWrite";
    }
    
    @PostMapping("insertSoccerTeam.do")
    public String insertSoccerTeam(SoccerTeamDto soccerTeamInsertDto, @RequestPart("files") MultipartFile[] files) throws Exception {
//        SoccerTeamDto soccerTeamDto = new SoccerTeamDto();
//        soccerTeamDto.setTitle(soccerTeamInsertRequest.getTitle());
//        soccerTeamDto.setContents(soccerTeamInsertRequest.getContents());
        SoccerTeamDto soccerTeamDto = new ModelMapper().map(soccerTeamInsertDto, SoccerTeamDto.class);
    	
    	soccerTeamService.insertSoccerTeam(soccerTeamDto, files);
        return "redirect:/soccerTeam/openSoccerTeamList.do";
    }
    
    @GetMapping("/openSoccerTeamDetail.do")
    public ModelAndView openSoccerTeamDetail(@RequestParam("teamIdx") int teamIdx) throws Exception {
        ModelAndView mv = new ModelAndView("soccerTeamDetail");
        SoccerTeamDto soccerTeamDtoResult = soccerTeamService.selectSoccerTeamDetail(teamIdx);
        List<PlayerDto> playerList = playerService.selectPlayersByTeamIdx(teamIdx); // teamIdx로 필터링된 플레이어 목록 조회
        mv.addObject("soccerTeam", soccerTeamDtoResult);
        mv.addObject("playerList", playerList); // 필터링된 플레이어 목록 추가
        return mv;
    }

    
    // 삭제
    @PostMapping("deleteSoccerTeam.do")
    public String deleteSoccerTeam(@RequestParam("teamIdx") int teamIdx) throws Exception {
    	soccerTeamService.deleteSoccerTeam(teamIdx);
    	return "redirect:/soccerTeam/openSoccerTeamList.do";
    }
    
    // 수정하기
    @GetMapping("/openSoccerTeamModify.do")
    public ModelAndView openSoccerTeamModify(@RequestParam("teamIdx") int teamIdx) throws Exception {
        ModelAndView mv = new ModelAndView("soccerTeamModify");
        SoccerTeamDto soccerTeamDtoResult = soccerTeamService.selectSoccerTeamDetail(teamIdx);
        mv.addObject("soccerTeam", soccerTeamDtoResult);
        return mv;
    }
    
    // 수정 내용 저장
    @PostMapping("updateSoccerTeam.do")
    public String updateSoccerTeam(SoccerTeamDto soccerTeamDto) throws Exception {
    	soccerTeamService.updateSoccerTeam(soccerTeamDto);
    	return "redirect:/soccerTeam/openSoccerTeamList.do";
    }
    
    
    @GetMapping("openPlayerList.do")
    public ModelAndView openPlayerList() throws Exception {
        ModelAndView mv = new ModelAndView("playerList");
        List<PlayerDto> player = playerService.selectPlayerList();
        mv.addObject("player", player);
        return mv;
    }
    
    @GetMapping("openPlayerWrite.do")
    public String openPlayerWrite(@RequestParam("teamIdx") int teamIdx, Model model) {
        model.addAttribute("teamIdx", teamIdx);
        return "playerWrite";
    }

    @PostMapping("insertPlayer.do")
    public String insertPlayer(@RequestParam("teamIdx") int teamIdx, PlayerDto playerDto) throws Exception {
        playerDto.setTeamIdx(teamIdx);
        playerService.insertPlayer(playerDto);
        return "redirect:/soccerTeam/openSoccerTeamDetail.do?teamIdx=" + teamIdx;
    }
    
    @GetMapping("/openPlayerDetail.do")
    public ModelAndView openPlayerDetail(@RequestParam("playerIdx") int playerIdx) throws Exception {
        ModelAndView mv = new ModelAndView("playerDetail");
        PlayerDto playerDtoResult = playerService.selectPlayerDetail(playerIdx);
        mv.addObject("player", playerDtoResult);
        return mv;
    }
    
    @PostMapping("deletePlayer.do")
    public String deletePlayer(@RequestParam("playerIdx") int playerIdx, @RequestParam("teamIdx") int teamIdx) throws Exception {
        playerService.deletePlayer(playerIdx);
        return "redirect:/soccerTeam/openSoccerTeamDetail.do?teamIdx=" + teamIdx;
    }

    @GetMapping("/openPlayerModify.do")
    public ModelAndView openPlayerModify(@RequestParam("playerIdx") int playerIdx) throws Exception {
        ModelAndView mv = new ModelAndView("playerModify");
        PlayerDto playerDtoResult = playerService.selectPlayerDetail(playerIdx);
        mv.addObject("player", playerDtoResult);
        return mv;
    }
    
    @PostMapping("updatePlayer.do")
    public String updatePlayer(@RequestParam("playerIdx") int playerIdx, @RequestParam("teamIdx") int teamIdx, PlayerDto playerDto) throws Exception {
        playerDto.setPlayerIdx(playerIdx);
        playerDto.setTeamIdx(teamIdx);
        playerService.updatePlayer(playerDto);
        return "redirect:/soccerTeam/openPlayerDetail.do?playerIdx=" + playerIdx;
    }
    
    @GetMapping("downloadSoccerTeamFile.do")
    public void downloadSoccerTemaFile(@RequestParam("teamFileIdx") int teamFileIdx, @RequestParam("teamIdx") int teamIdx, HttpServletResponse response) throws Exception {
    	// teamFileIdx와 teamIdx가 일치하는 파일 정보를 조회
    	SoccerTeamFileDto soccerTeamFileDto = soccerTeamService.selectSoccerTeamFileInfo(teamFileIdx, teamIdx);
    	if (ObjectUtils.isEmpty(soccerTeamFileDto)) {
    		return;
    	}
    	
    	// 원본 파일 저장 위치에서 파일을 읽어서 호출한 곳으로 첨부파일을 응답으로 전달
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

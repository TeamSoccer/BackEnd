package soccerTeam.controller;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import soccerTeam.dto.PlayerDto;
import soccerTeam.entity.SoccerTeamEntity;
import soccerTeam.entity.SoccerTeamFileEntity;
import soccerTeam.service.JpaSoccerTeamService;
import soccerTeam.service.PlayerService;

@Slf4j
@RestController
@RequestMapping("/jpa/soccerTeam")
public class JpaSoccerTeamController {

    @Autowired
    private JpaSoccerTeamService jpaSoccerTeamService;

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ModelAndView openSoccerTeamList() throws Exception {
        ModelAndView mv = new ModelAndView("jpaSoccerTeamList");

        List<SoccerTeamEntity> list = jpaSoccerTeamService.selectSoccerTeamList();
        mv.addObject("list", list);

        return mv;
    }

    @GetMapping("/write")
    public String openSoccerTeamWrite() throws Exception {
        return "jpaSoccerTeamWrite";
    }

    @PostMapping("/write")
    public String insertSoccerTeam(@ModelAttribute SoccerTeamEntity soccerTeamEntity, @RequestParam("files") MultipartFile[] files) throws Exception {
        jpaSoccerTeamService.insertSoccerTeam(soccerTeamEntity, files);
        return "redirect:/jpa/soccerTeam";
    }

    @GetMapping("/{teamIdx}")
    public ResponseEntity<Object> openSoccerTeamDetail(@PathVariable("teamIdx") int teamIdx) throws Exception {
        SoccerTeamEntity soccerTeamEntity = jpaSoccerTeamService.selectSoccerTeamDetail(teamIdx);
        if (soccerTeamEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("일치하는 팀이 존재하지 않습니다.");
        }
        return ResponseEntity.ok(soccerTeamEntity);
    }

    @PostMapping("/{teamIdx}")
    public String deleteSoccerTeam(@PathVariable("teamIdx") int teamIdx) throws Exception {
        jpaSoccerTeamService.deleteSoccerTeam(teamIdx);
        return "redirect:/jpa/soccerTeam";
    }

    @GetMapping("/{teamIdx}/edit")
    public ModelAndView openSoccerTeamModify(@PathVariable("teamIdx") int teamIdx) throws Exception {
        ModelAndView mv = new ModelAndView("jpaSoccerTeamModify");
        SoccerTeamEntity soccerTeamEntity = jpaSoccerTeamService.selectSoccerTeamDetail(teamIdx);
        mv.addObject("soccerTeam", soccerTeamEntity);
        return mv;
    }

    @PostMapping("/{teamIdx}/edit")
    public String updateSoccerTeam(@PathVariable("teamIdx") int teamIdx, @RequestBody SoccerTeamEntity soccerTeamEntity) throws Exception {
        jpaSoccerTeamService.updateSoccerTeam(soccerTeamEntity);
        return "redirect:/jpa/soccerTeam";
    }

    @GetMapping("/player")
    public ModelAndView openPlayerList() throws Exception {
        ModelAndView mv = new ModelAndView("playerList");
        List<PlayerDto> playerList = playerService.selectPlayerList();
        mv.addObject("player", playerList);
        return mv;
    }

    @GetMapping("/player/write")
    public String openPlayerWrite(@RequestParam("teamIdx") int teamIdx, Model model) {
        model.addAttribute("teamIdx", teamIdx);
        return "playerWrite";
    }

    @PostMapping("/player/write")
    public String insertPlayer(@RequestParam("teamIdx") int teamIdx, @RequestBody PlayerDto playerDto) throws Exception {
        playerDto.setTeamIdx(teamIdx);
        playerService.insertPlayer(playerDto);
        return "redirect:/jpa/soccerTeam/" + teamIdx;
    }

    @GetMapping("/player/{playerIdx}")
    public ModelAndView openPlayerDetail(@PathVariable("playerIdx") int playerIdx) throws Exception {
        ModelAndView mv = new ModelAndView("playerDetail");
        PlayerDto playerDtoResult = playerService.selectPlayerDetail(playerIdx);
        mv.addObject("player", playerDtoResult);
        return mv;
    }

    @PostMapping("/player/{playerIdx}")
    public String deletePlayer(@PathVariable("playerIdx") int playerIdx, @RequestParam("teamIdx") int teamIdx) throws Exception {
        playerService.deletePlayer(playerIdx);
        return "redirect:/jpa/soccerTeam/" + teamIdx;
    }

    @GetMapping("/player/{playerIdx}/edit")
    public ModelAndView openPlayerModify(@PathVariable("playerIdx") int playerIdx) throws Exception {
        ModelAndView mv = new ModelAndView("playerModify");
        PlayerDto playerDtoResult = playerService.selectPlayerDetail(playerIdx);
        mv.addObject("player", playerDtoResult);
        return mv;
    }

    @PostMapping("/player/{playerIdx}/edit")
    public String updatePlayer(@PathVariable("playerIdx") int playerIdx, @RequestParam("teamIdx") int teamIdx, PlayerDto playerDto) throws Exception {
        playerDto.setPlayerIdx(playerIdx);
        playerDto.setTeamIdx(teamIdx);
        playerService.updatePlayer(playerDto);
        return "redirect:/player/" + playerIdx;
    }

    @GetMapping("/file/{teamIdx}/{teamFileIdx}")
    public void downloadSoccerTeamFile(@PathVariable("teamFileIdx") int teamFileIdx, @PathVariable("teamIdx") int teamIdx, HttpServletResponse response) throws Exception {
        SoccerTeamFileEntity soccerTeamFileEntity = jpaSoccerTeamService.selectSoccerTeamFileInfo(teamFileIdx, teamIdx);
        if (ObjectUtils.isEmpty(soccerTeamFileEntity)) {
            return;
        }

        Path path = Paths.get(soccerTeamFileEntity.getStoredFilePath());
        byte[] file = Files.readAllBytes(path);

        response.setContentType("application/octet-stream");
        response.setContentLength(file.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(soccerTeamFileEntity.getOriginalFileName(), "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(file);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}

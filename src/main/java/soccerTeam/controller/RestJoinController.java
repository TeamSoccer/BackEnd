package soccerTeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import soccerTeam.dto.JoinDto;
import soccerTeam.service.JoinService;

@RestController
@RequestMapping("/api/join")
public class RestJoinController {
    @Autowired
    JoinService joinService;

    @PostMapping
    public ResponseEntity<?> join(@RequestBody JoinDto joinDto) {
        if (joinService.joinProcess(joinDto)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Registration failed");
        }
    }
}

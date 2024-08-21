//package soccerTeam.controller;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import soccerTeam.common.JwtUtils;
//import soccerTeam.dto.LoginRequest;
//import soccerTeam.entity.UserEntity;
//import soccerTeam.repository.UserRepository;
//
//@RestController
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/api/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//            );
//
//            UserEntity user = (UserEntity) authentication.getPrincipal();
//            String token = jwtUtils.generateToken(user);
//
//            return ResponseEntity.ok().body(Map.of("token", token));
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }
//}

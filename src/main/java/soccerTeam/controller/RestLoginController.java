//package soccerTeam.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import soccerTeam.dto.LoginRequest;
//import soccerTeam.dto.JwtResponse;
//import soccerTeam.common.JwtUtils;
//
//@RestController
//@RequestMapping("/api/auth")
//public class RestLoginController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String jwt = jwtUtils.generateToken(authentication);
//
//            return ResponseEntity.ok(new JwtResponse(jwt));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//}

package soccerTeam.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(Model model, CsrfToken csrfToken) {
        model.addAttribute("_csrf", csrfToken);
        return "login";
    }
}

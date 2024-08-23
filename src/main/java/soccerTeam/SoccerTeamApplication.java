package soccerTeam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaAuditing
@SpringBootApplication
public class SoccerTeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerTeamApplication.class, args);
	}
}

package soccerTeam.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;
import soccerTeam.security.CustomAuthenticationSuccessHandler;
import soccerTeam.security.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //.cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/**", "/login", "/loginProc", "/join", "/joinProc", "/home", "/api/join", "/api/login", "/api/soccerTeam/**").permitAll()
//              .requestMatchers("/admin").hasRole("ADMIN")
//              .requestMatchers("/board/**", "/api/**").hasAnyRole("ADMIN", "USER")
            .anyRequest().authenticated()
            )
            .formLogin(form -> form
                //.loginPage("/login")
                .loginProcessingUrl("/api/login")
                .permitAll()
                .successHandler(successHandler)
            )
            .csrf(csrf -> csrf.disable())
            
            .sessionManagement(session -> session
                .sessionFixation().newSession()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
            )
            
            .httpBasic(customizer -> customizer.authenticationEntryPoint((request, response, authException) -> {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //@Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


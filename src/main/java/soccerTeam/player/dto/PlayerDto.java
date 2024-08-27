package soccerTeam.player.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class PlayerDto {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;
    private String phoneNumber;
    private String region;
    private Integer period;
    private Integer age;
    private Boolean athlete;
}

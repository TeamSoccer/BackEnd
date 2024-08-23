package soccerTeam.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class JoinDto {
    private final String name;
    private final String email;
    private final String username;
    private final String password;
    private final String passwordConfirm;
    private final String phoneNumber;
    private final Integer period;
    private final Integer age;
    private final Boolean athlete;

    @Builder
    public JoinDto(
            String name,
            String email,
            String username,
            String password,
            String passwordConfirm,
            String phoneNumber,
            Integer period,
            Integer age,
            Boolean athlete) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.phoneNumber = phoneNumber;
        this.period = period;
        this.age = age;
        this.athlete = athlete;
    }

    public boolean checkPassword() {
        return this.password != null && this.password.equals(this.passwordConfirm);
    }
}

package soccerTeam.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JoinDto {
    private String name;
    private String email;
    private String username;
    private String password;
    private String passwordConfirm;
    private String phoneNumber;
    private Integer period;
    private Integer age;
    private Boolean athlete;

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

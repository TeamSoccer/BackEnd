package soccerTeam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JoinDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 3, max = 17, message = "이름은 실명으로 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "ID를 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 확인해주세요.")
    private String passwordConfirm;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호는 000-0000-0000 형식이어야 합니다.")
    private String phoneNumber;

    @NotNull(message = "나이를 입력해주세요.")
    @Size(min = 10, max = 100, message = "올바른 나이를 입력해주세요.")
    private Integer age;

    private Integer period;
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

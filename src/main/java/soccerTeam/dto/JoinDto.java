package soccerTeam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "회원가입 요청 객체")
public class JoinDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 3, max = 17, message = "이름은 실명으로 입력해주세요.")
    @Schema(description = "사용자의 이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    @Schema(description = "사용자의 이메일", example = "test@test.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "ID를 입력해주세요.")
    @Schema(description = "사용자의 아이디", example = "testid", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Schema(description = "사용자의 비밀번호", example = "testpassword", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank(message = "비밀번호를 확인해주세요.")
    @Schema(description = "비밀번호 확인", example = "testpassword", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordConfirm;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "전화번호는 000-0000-0000 형식이어야 합니다.")
    @Schema(description = "전화번호", example = "010-1234-5678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phoneNumber;

    @NotNull(message = "나이를 입력해주세요.")
    @Size(min = 10, max = 100, message = "올바른 나이를 입력해주세요.")
    @Schema(description = "사용자의 나이", example = "29", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer age;

    @Schema(description = "사용자의 구력", example = "5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer period;

    @Schema(description = "선출 여부", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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

package soccerTeam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "로그인 요청 객체")
@Data
public class LoginRequest {

    @NotBlank(message = "ID를 입력해주세요.")
    @Schema(description = "사용자 ID", example = "testid", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Schema(description = "사용자 비밀번호", example = "testpassword", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}

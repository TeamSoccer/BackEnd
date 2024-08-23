package soccerTeam.type.player;

import soccerTeam.type.ErrorType;

public enum PlayerErrorType implements ErrorType {
    USERNAME_ALREADY_EXIST("PLAYER_1", "이미 존재하는 사용자 이름입니다"),
    CONFIRM_PASSWORD_NOT_MATCH("PLAYER_2", "비밀번호와 비밀번호 확인이 일치하지 않습니다"),
    PLAYER_NOT_FOUND("PLAYER_3", "사용자를 찾을 수 없습니다");

    private final String code;
    private final String message;

    PlayerErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

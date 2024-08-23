package soccerTeam.type.player;

import soccerTeam.type.SuccessType;

public enum PlayerSuccessType implements SuccessType {
    JOIN_SUCCESS("PLAYER_1", "플레이어 가입에 성공하였습니다");

    private final String code;
    private final String message;

    PlayerSuccessType(String code, String message) {
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

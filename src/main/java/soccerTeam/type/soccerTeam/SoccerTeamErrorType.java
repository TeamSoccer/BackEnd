package soccerTeam.type.soccerTeam;

import soccerTeam.type.ErrorType;

public enum SoccerTeamErrorType implements ErrorType {
    TEAM_NOT_FOUND("SOCCER_TEAM_1", "팀을 찾을 수 없습니다");

    private final String code;
    private final String message;

    SoccerTeamErrorType(String code, String message) {
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

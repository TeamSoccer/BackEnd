package soccerTeam.type.soccerTeam;

import soccerTeam.type.ErrorType;

public enum SoccerTeamErrorType implements ErrorType {
    TEAM_NOT_FOUND("SOCCER_TEAM_1", "팀을 찾을 수 없습니다"),
    TEAM_OWNER_CAN_MODIFY("SOCCER_TEAM_2", "팀을 만든 사람만 팀을 수정할 수 있습니다");

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

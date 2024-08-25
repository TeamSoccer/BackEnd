package soccerTeam.type.soccerTeam;

import soccerTeam.type.SuccessType;

public enum SoccerTeamSuccessType implements SuccessType {
    GET_SOCCER_TEAM_LIST_SUCCESS("SOCCER_TEAM_1", "팀 목록 조회에 성공하였습니다"),
    GET_SOCCER_TEAM_SUCCESS("SOCCER_TEAM_2", "팀 단일 조회에 성공하였습니다"),
    UPDATE_SOCCER_TEAM_SUCCESS("SOCCER_TEAM_3", "팀 수정에 성공하였습니다");

    private final String code;
    private final String message;

    SoccerTeamSuccessType(String code, String message) {
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

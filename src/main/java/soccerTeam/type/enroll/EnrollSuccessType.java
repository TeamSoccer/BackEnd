package soccerTeam.type.enroll;

import soccerTeam.type.SuccessType;

public enum EnrollSuccessType implements SuccessType {
    CREATE_SUCCESS("ENROLL_1", "팀 가입 신청에 성공하였습니다");

    private final String code;
    private final String message;

    EnrollSuccessType(String code, String message) {
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

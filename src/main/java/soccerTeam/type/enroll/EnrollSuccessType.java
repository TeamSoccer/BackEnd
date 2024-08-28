package soccerTeam.type.enroll;

import soccerTeam.type.SuccessType;

public enum EnrollSuccessType implements SuccessType {
    CREATE_SUCCESS("ENROLL_1", "팀 가입 신청에 성공하였습니다"),
    LIST_SUCCESS("ENROLL_2", "선수 목록 조회에 성공했습니다"),
    ENROLL_SUCCESS("ENROLL_3", "입단 신청서 조회에 성공했습니다"),
    DELETE_SUCCESS("ENROLL_4", "입단 신청서 삭제에 성공했습니다");

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

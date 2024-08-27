package soccerTeam.type.enroll;

import soccerTeam.type.SuccessType;

public enum EnrollSuccessType implements SuccessType {
    CREATE_SUCCESS("ENROLL_1", "팀 가입 신청에 성공하였습니다"),
    LIST_SUCCESS("ENROLL_2", "팀을 만든 사람만 팀을 수정할 수 있습니다"),
    UPDATE_SUCCESS("ENROLL_3", "입단 신청 수정이 완료되었습니다.");

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

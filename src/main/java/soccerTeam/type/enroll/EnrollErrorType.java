package soccerTeam.type.enroll;

import soccerTeam.type.ErrorType;

public enum EnrollErrorType implements ErrorType {
    ENROLL_NOT_FOUND("ENROLL_1", "입단 신청서를 찾을 수 없습니다"),
    ONLY_OWNER_CAN_MODIFY("ENROLL_2", "본인의 입단 신청서만 수정 가능합니다");

    private final String code;
    private final String message;

    EnrollErrorType(String code, String message) {
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

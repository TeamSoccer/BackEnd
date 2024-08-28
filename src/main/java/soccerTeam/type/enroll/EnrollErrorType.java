package soccerTeam.type.enroll;

import soccerTeam.type.ErrorType;

public enum EnrollErrorType implements ErrorType {
    NOT_FOUND("ENROLL_1", "입단 신청서를 찾을 수 없습니다."),
    NOT_OWNED_BY_USER("ENROLL_2", "입단 신청서를 작성한 사용자가 아닙니다."),
    DELETE_FAILED("ENROLL_3", "사용자를 찾을 수 없습니다");

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

package soccerTeam.exception;

import soccerTeam.type.ErrorType;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(ErrorType errorType) {
        super(errorType, HttpStatus.BAD_REQUEST);
    }
}

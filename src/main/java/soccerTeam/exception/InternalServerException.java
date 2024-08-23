package soccerTeam.exception;

import soccerTeam.type.ErrorType;
import org.springframework.http.HttpStatus;

public class InternalServerException extends BaseException {
    public InternalServerException(ErrorType errorType) {
        super(errorType, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

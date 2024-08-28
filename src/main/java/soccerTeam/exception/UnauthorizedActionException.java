package soccerTeam.exception;

import org.springframework.http.HttpStatus;
import soccerTeam.type.ErrorType;

public class UnauthorizedActionException extends BaseException {
    public UnauthorizedActionException(ErrorType errorType) {super(errorType, HttpStatus.FORBIDDEN);}
}

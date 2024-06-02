package cpe.atelier3.commons.api.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus()
public class ApiNokException extends Exception {

    private int errorCode;
    public ApiNokException(int errorCode, String message) {
        super(errorCode + ": " + message);
        this.errorCode = errorCode;
    }

}

package cpe.atelier3.commons.api.exception;

public class ApiNokException extends Exception {

    private int errorCode;
    public ApiNokException(int errorCode, String message) {
        super(errorCode + ": " + message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}

package cpe.atelier3.commons.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "A user cannot buy their own item")
public class UserSelfPaymentException extends Exception { }
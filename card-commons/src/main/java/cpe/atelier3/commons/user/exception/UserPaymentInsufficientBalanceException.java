package cpe.atelier3.commons.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Your balance is too low for this transaction")
public class UserPaymentInsufficientBalanceException extends Exception {
}

package cpe.atelier3.commons.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "You cannot buy an item on the behalf of another user")
public class UserPaymentImpersonationException extends Exception {
}

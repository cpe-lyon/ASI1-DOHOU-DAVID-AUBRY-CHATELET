package cpe.atelier3.commons.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User does not have this card")
public class CardNotOwnedException extends Exception {
}

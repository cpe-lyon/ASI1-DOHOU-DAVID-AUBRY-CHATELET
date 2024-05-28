package cpe.atelier3.commons.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Card id was not found")
public class CardNotFoundException extends Exception {
}

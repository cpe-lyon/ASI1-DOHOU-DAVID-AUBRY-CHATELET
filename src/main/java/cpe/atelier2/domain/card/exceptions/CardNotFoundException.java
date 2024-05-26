package cpe.atelier2.domain.card.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Card id was not found")
public class CardNotFoundException extends Exception {
}

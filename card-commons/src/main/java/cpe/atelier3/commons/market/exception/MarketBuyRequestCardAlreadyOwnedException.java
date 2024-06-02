package cpe.atelier3.commons.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "User already owns this card")
public class MarketBuyRequestCardAlreadyOwnedException extends Exception {
}

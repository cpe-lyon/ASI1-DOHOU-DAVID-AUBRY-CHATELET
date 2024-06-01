package cpe.atelier3.commons.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Trying to sell the card multiple times on the marketplace")
public class MarketSellProposalAlreadyExistsException extends Throwable {
}

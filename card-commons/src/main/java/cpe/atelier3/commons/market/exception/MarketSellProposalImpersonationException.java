package cpe.atelier3.commons.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "You are trying to sell the card from another user. This is not authorized.")
public class MarketSellProposalImpersonationException extends Throwable {
}

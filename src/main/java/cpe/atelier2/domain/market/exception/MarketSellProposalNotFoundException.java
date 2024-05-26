package cpe.atelier2.domain.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "This market sale doesn't exist")
public class MarketSellProposalNotFoundException extends Throwable {
}

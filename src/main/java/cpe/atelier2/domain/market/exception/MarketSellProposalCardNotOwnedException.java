package cpe.atelier2.domain.market.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Card not owned by user")
public class MarketSellProposalCardNotOwnedException extends Throwable {
}

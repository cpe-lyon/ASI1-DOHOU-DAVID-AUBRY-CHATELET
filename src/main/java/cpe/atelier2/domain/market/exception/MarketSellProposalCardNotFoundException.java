package cpe.atelier2.domain.market.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Card id was not found in database")
public class MarketSellProposalCardNotFoundException extends Throwable {
}

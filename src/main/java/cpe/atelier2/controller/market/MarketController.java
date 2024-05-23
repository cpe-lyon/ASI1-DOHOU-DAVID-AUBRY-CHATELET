package cpe.atelier2.controller.market;

import cpe.atelier2.domain.auth.AuthenticationService;
import cpe.atelier2.domain.auth.exception.ExpiredTokenException;
import cpe.atelier2.domain.market.MarketService;
import cpe.atelier2.domain.market.exception.MarketSellProposalCardNotFoundException;
import cpe.atelier2.domain.market.exception.MarketSellProposalCardNotOwnedException;
import cpe.atelier2.domain.market.exception.MarketSellProposalUserNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market")
public class MarketController {

    private final AuthenticationService authenticationService;
    private final MarketService marketService;
    private final MarketSellProposalDtoMapper mapMarketSellProposalRequestDtoMapper;

    public MarketController(AuthenticationService authenticationService, MarketService marketService, MarketSellProposalDtoMapper mapMarketSellProposalRequestDtoMapper) {
        this.authenticationService = authenticationService;
        this.marketService = marketService;
        this.mapMarketSellProposalRequestDtoMapper = mapMarketSellProposalRequestDtoMapper;
    }

    @PostMapping("/sell")
    public void sell(@CookieValue("token") String token, @RequestBody MarketSellProposalRequestDTO marketSellRequest) throws ExpiredTokenException, MarketSellProposalCardNotFoundException, MarketSellProposalUserNotFoundException, MarketSellProposalCardNotOwnedException {
        authenticationService.checkAuthentication(token);
        marketService.sell(mapMarketSellProposalRequestDtoMapper.mapMarketSellProposalRequestDtoToMarketSellProposal(marketSellRequest));
    }
}

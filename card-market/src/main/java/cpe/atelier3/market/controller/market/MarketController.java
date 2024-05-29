package cpe.atelier3.market.controller.market;

import cpe.atelier3.commons.api.auth.AuthenticationApi;
import cpe.atelier3.commons.api.exception.ApiURIException;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.market.dto.MarketSellProposalDto;
import cpe.atelier3.commons.market.dto.MarketSellProposalDtoMapper;
import cpe.atelier3.commons.market.dto.MarketSellProposalRequestDTO;
import cpe.atelier3.commons.market.exception.MarketSellProposalAlreadyExistsException;
import cpe.atelier3.commons.market.exception.MarketSellProposalCardNotOwnedException;
import cpe.atelier3.commons.user.exception.InvalidTokenException;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import cpe.atelier3.market.domain.market.MarketService;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {

    private final AuthenticationApi authenticationService;
    private final MarketService marketService;
    private final MarketSellProposalDtoMapper marketSellProposalDtoMapper;

    public MarketController(AuthenticationApi authenticationService, MarketService marketService, MarketSellProposalDtoMapper marketSellProposalDtoMapper) {
        this.authenticationService = authenticationService;
        this.marketService = marketService;
        this.marketSellProposalDtoMapper = marketSellProposalDtoMapper;
    }

    @PostMapping("/sell")
    public void sell(@CookieValue("token") String token, @RequestBody MarketSellProposalRequestDTO marketSellRequest) throws InvalidTokenException,
            MarketSellProposalCardNotOwnedException,
            UserNotFoundException,
            CardNotFoundException, MarketSellProposalAlreadyExistsException, ApiURIException, URISyntaxException {
        authenticationService.checkAuthentication(token);
        marketService.sell(marketSellProposalDtoMapper.mapMarketSellProposalRequestDtoToMarketSellProposal(marketSellRequest));
    }

    @GetMapping("/all")
    public List<MarketSellProposalDto> getAllMarketProposals() {
        return marketService.findAllMarketSellProposals().stream()
                .map(marketSellProposalDtoMapper::mapMarketSellProposalToDto)
                .toList();
    }
}

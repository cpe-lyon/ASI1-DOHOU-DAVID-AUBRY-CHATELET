package cpe.atelier2.controller.market;

import cpe.atelier2.domain.auth.AuthenticationService;
import cpe.atelier2.domain.auth.exception.ExpiredTokenException;
import cpe.atelier2.domain.card.exception.CardNotFoundException;
import cpe.atelier2.domain.market.MarketService;
import cpe.atelier2.domain.market.exception.MarketSellProposalAlreadyExistsException;
import cpe.atelier2.domain.market.exception.MarketSellProposalCardNotOwnedException;
import cpe.atelier2.domain.user.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {

    private final AuthenticationService authenticationService;
    private final MarketService marketService;
    private final MarketSellProposalDtoMapper mapMarketSellProposalDtoMapper;
    private final MarketSellProposalDtoMapper marketSellProposalDtoMapper;

    public MarketController(AuthenticationService authenticationService, MarketService marketService, MarketSellProposalDtoMapper mapMarketSellProposalDtoMapper, MarketSellProposalDtoMapper marketSellProposalDtoMapper) {
        this.authenticationService = authenticationService;
        this.marketService = marketService;
        this.mapMarketSellProposalDtoMapper = mapMarketSellProposalDtoMapper;
        this.marketSellProposalDtoMapper = marketSellProposalDtoMapper;
    }

    @PostMapping("/sell")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public void sell(@CookieValue("token") String token, @RequestBody MarketSellProposalRequestDTO marketSellRequest) throws ExpiredTokenException,
            MarketSellProposalCardNotOwnedException,
            UserNotFoundException,
            CardNotFoundException, MarketSellProposalAlreadyExistsException {
        authenticationService.checkAuthentication(token);
        marketService.sell(mapMarketSellProposalDtoMapper.mapMarketSellProposalRequestDtoToMarketSellProposal(marketSellRequest));
    }

    @GetMapping("/all")
    public List<MarketSellProposalDto> getAllMarketProposals() {
        return marketService.findAllMarketSellProposals().stream()
                .map(marketSellProposalDtoMapper::mapMarketSellProposalToDto)
                .toList();
    }
}

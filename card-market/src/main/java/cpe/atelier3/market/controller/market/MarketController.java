package cpe.atelier3.market.controller.market;

import cpe.atelier3.commons.api.auth.AuthenticationApi;
import cpe.atelier3.commons.api.auth.utils.AuthJWTUtils;
import cpe.atelier3.commons.api.card.CardApi;
import cpe.atelier3.commons.api.exception.ApiURIException;
import cpe.atelier3.commons.api.user.UserApi;
import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.market.dto.*;
import cpe.atelier3.commons.market.exception.*;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.user.exception.InvalidTokenException;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import cpe.atelier3.market.domain.market.MarketService;
import jakarta.servlet.http.Cookie;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {

    private final AuthenticationApi authenticationService;
    private final MarketService marketService;
    private final MarketSellProposalDtoMapper marketSellProposalDtoMapper;
    private final MarketBuyRequestDtoMapper marketBuyRequestDtoMapper;
    private final UserApi userApi;
    private final CardApi cardApi;

    public MarketController(AuthenticationApi authenticationService, MarketService marketService, MarketSellProposalDtoMapper marketSellProposalDtoMapper, MarketBuyRequestDtoMapper marketBuyRequestDtoMapper, UserApi userApi, CardApi cardApi) {
        this.authenticationService = authenticationService;
        this.marketService = marketService;
        this.marketSellProposalDtoMapper = marketSellProposalDtoMapper;
        this.marketBuyRequestDtoMapper = marketBuyRequestDtoMapper;
        this.userApi = userApi;
        this.cardApi = cardApi;
    }

    @PostMapping("/sell")
    public void sell(@CookieValue("token") String token, @RequestBody MarketSellProposalRequestDTO marketSellRequest) throws InvalidTokenException,
            MarketSellProposalCardNotOwnedException,
            UserNotFoundException,
            CardNotFoundException, MarketSellProposalAlreadyExistsException, URISyntaxException, MarketSellProposalImpersonationException {
        authenticationService.checkAuthentication(token);

        User seller = userApi.findUserPrivate(token);
        Card card = cardApi.findCardById(marketSellRequest.cardToSellId());

        if (! seller.id().toString().equals(marketSellRequest.sellerId().toString())) {
            throw new MarketSellProposalImpersonationException();
        };

        marketService.sell(marketSellProposalDtoMapper.mapMarketSellProposalRequestDtoToMarketSellProposal(marketSellRequest, seller, card));
    }

    @GetMapping("/all")
    public List<MarketSellProposalDto> getAllMarketProposals() {
        return marketService.findAllMarketSellProposals().stream()
                .map(marketSellProposalDtoMapper::mapMarketSellProposalToDto)
                .toList();
    }

    @PostMapping("/buy")
    public void buy(@CookieValue("token") String token, @RequestBody MarketBuyRequestDTO marketBuyRequestDTO) throws InvalidTokenException,
            URISyntaxException, MarketSellProposalNotFoundException, ResponseStatusException, UserNotFoundException, MarketBuyRequestCardAlreadyOwnedException {
        String uid = authenticationService.checkAuthentication(token);
        marketService.buy(token, marketBuyRequestDtoMapper.marketBuyRequestDtoToMarketBuyRequest(marketBuyRequestDTO));
    }
}

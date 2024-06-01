package cpe.atelier3.market.domain.market;

import cpe.atelier3.commons.api.card.CardApi;
import cpe.atelier3.commons.api.user.UserApi;
import cpe.atelier3.commons.card.Card;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.market.MarketSellProposal;
import cpe.atelier3.commons.market.exception.MarketSellProposalAlreadyExistsException;
import cpe.atelier3.commons.market.exception.MarketSellProposalCardNotOwnedException;
import cpe.atelier3.commons.market.exception.MarketSellProposalNotFoundException;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class MarketService {
    private final CardApi cardService;
    UserApi userService;
    IMarketRepository iMarketRepository;

    public MarketService(UserApi userService, CardApi cardService, IMarketRepository iMarketRepository) {
        this.userService = userService;
        this.cardService = cardService;
        this.iMarketRepository = iMarketRepository;
    }

    public void sell(MarketSellProposal marketSellProposal) throws MarketSellProposalCardNotOwnedException, CardNotFoundException,
            MarketSellProposalAlreadyExistsException {
        User seller = marketSellProposal.seller();
        Card card = marketSellProposal.card();

        if (seller.cardList().isEmpty() || ! seller.cardList().contains(card)) {
            throw new MarketSellProposalCardNotOwnedException();
        }

        try {
            findMarketSellProposalForUserAndCard(marketSellProposal.seller().id(), marketSellProposal.card().getId());
        }  catch (MarketSellProposalNotFoundException e) {
            iMarketRepository.createNewMarketSellProposal(marketSellProposal);
            return;
        }
        throw new MarketSellProposalAlreadyExistsException();
    }

    public MarketSellProposal findMarketSellProposalForUserAndCard(Long userId, Long cardId) throws MarketSellProposalNotFoundException {

        Optional<MarketSellProposal> proposal =  iMarketRepository.findMarketSellProposalBySellerIdAndCardId(userId, cardId);

        if (proposal.isEmpty()) {
            throw new MarketSellProposalNotFoundException();
        }

        return proposal.get();
    }

    public List<MarketSellProposal> findAllMarketSellProposalsForUser(Long userId) {
        return iMarketRepository.findAllByUserId(userId);
    }

    public List<MarketSellProposal> findAllMarketSellProposals() {
        return iMarketRepository.findAllMarketSellProposals();
    }
}

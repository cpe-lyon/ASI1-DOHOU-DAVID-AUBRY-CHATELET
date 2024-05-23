package cpe.atelier2.domain.market;

import cpe.atelier2.domain.card.Card;
import cpe.atelier2.domain.card.CardService;
import cpe.atelier2.domain.market.exception.MarketSellProposalCardNotFoundException;
import cpe.atelier2.domain.market.exception.MarketSellProposalCardNotOwnedException;
import cpe.atelier2.domain.market.exception.MarketSellProposalUserNotFoundException;
import cpe.atelier2.domain.user.User;
import cpe.atelier2.domain.user.UserService;
import cpe.atelier2.repository.card.CardEntityMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarketService {
    private final CardService cardService;
    private final CardEntityMapper cardEntityMapper;
    UserService userService;
    IMarketRepository iMarketRepository;

    public MarketService(UserService userService, CardService cardService, CardEntityMapper cardEntityMapper, IMarketRepository iMarketRepository) {
        this.userService = userService;
        this.cardService = cardService;
        this.cardEntityMapper = cardEntityMapper;
    }

    public void sell(MarketSellProposal marketSellProposal) throws MarketSellProposalUserNotFoundException, MarketSellProposalCardNotFoundException, MarketSellProposalCardNotOwnedException {
        Optional<User> maybeSeller = userService.findUserById(marketSellProposal.seller());
        Optional<Card> maybeCard = cardService.findCardById(marketSellProposal.cardId());
        if (maybeSeller.isEmpty()) {
            throw new MarketSellProposalUserNotFoundException();
        }
        if (maybeCard.isEmpty()) {
            throw new MarketSellProposalCardNotFoundException();
        }

        User seller = maybeSeller.get();
        Card card = maybeCard.get();

        if (seller.cardList().isEmpty() || ! seller.cardList().contains(cardEntityMapper.cardToCardEntity(card))) {
            throw new MarketSellProposalCardNotOwnedException();
        }


    }
}

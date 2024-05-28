package cpe.atelier3.market.domain.market;

import cpe.atelier2.domain.card.Card;
import cpe.atelier2.domain.card.CardService;
import cpe.atelier2.domain.card.exception.CardNotFoundException;
import cpe.atelier2.domain.market.exception.MarketSellProposalAlreadyExistsException;
import cpe.atelier2.domain.market.exception.MarketSellProposalCardNotOwnedException;
import cpe.atelier2.domain.market.exception.MarketSellProposalNotFoundException;
import cpe.atelier2.domain.user.User;
import cpe.atelier2.domain.user.UserService;
import cpe.atelier2.domain.user.exception.UserNotFoundException;
import cpe.atelier2.repository.card.CardEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
        this.iMarketRepository = iMarketRepository;
    }

    public void sell(MarketSellProposal marketSellProposal) throws MarketSellProposalCardNotOwnedException, CardNotFoundException, UserNotFoundException, MarketSellProposalAlreadyExistsException {
        User seller = userService.findUserById(marketSellProposal.seller().id());
        Card card = cardService.findCardById(marketSellProposal.card().getId());

        if (seller.cardList().isEmpty() || ! seller.cardList().contains(cardEntityMapper.cardToCardEntity(card))) {
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

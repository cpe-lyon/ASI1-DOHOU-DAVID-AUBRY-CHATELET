package cpe.atelier2.repository.market;

import cpe.atelier2.domain.card.CardService;
import cpe.atelier2.domain.card.exceptions.CardNotFoundException;
import cpe.atelier2.domain.market.MarketSellProposal;
import cpe.atelier2.domain.user.User;
import cpe.atelier2.domain.user.UserService;
import cpe.atelier2.domain.user.exception.UserNotFoundException;
import cpe.atelier2.repository.user.UserEntity;
import cpe.atelier2.repository.user.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MarketSellProposalMapper {
    private final UserService userService;
    private final CardService cardService;
    private UserEntityMapper userEntityMapper;

    public MarketSellProposalMapper(@Autowired UserEntityMapper userEntityMapper, UserService userService, CardService cardService) {
        this.userEntityMapper = userEntityMapper;
        this.userService = userService;
        this.cardService = cardService;
    }

    public MarketSellProposalEntity mapMarketSellProposalToEntity(MarketSellProposal proposal) {
        MarketSellProposalEntity e = new MarketSellProposalEntity();
        e.setId(proposal.id());
        e.setPrice(proposal.price());
        e.setCardId(proposal.card().getId());
        User ue = null;
        try {
            ue = userService.findUserById(proposal.seller().id());
        } catch (UserNotFoundException ex) {
            return null;
        }

        e.setSeller(userEntityMapper.userToUserEntity(ue));

        return e;
    }

    public MarketSellProposal mapEntityToMarketSellProposal(MarketSellProposalEntity marketSellProposalEntity) throws CardNotFoundException {
        return new MarketSellProposal(marketSellProposalEntity.getId(),
                cardService.findCardById(marketSellProposalEntity.getCardId()),
                userEntityMapper.userEntityToUser(marketSellProposalEntity.getSeller()),
                marketSellProposalEntity.getPrice());
    }
}

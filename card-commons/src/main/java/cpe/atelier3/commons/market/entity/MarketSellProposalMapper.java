package cpe.atelier3.commons.market.entity;

import cpe.atelier3.commons.api.card.CardApi;
import cpe.atelier3.commons.api.exception.ApiURIException;
import cpe.atelier3.commons.api.user.UserApi;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.market.MarketSellProposal;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.user.entity.UserEntityMapper;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class MarketSellProposalMapper {
    private final UserApi userService;
    private final CardApi cardService;
    private UserEntityMapper userEntityMapper;

    public MarketSellProposalMapper(@Autowired UserEntityMapper userEntityMapper, UserApi userService, CardApi cardService) {
        this.userEntityMapper = userEntityMapper;
        this.userService = userService;
        this.cardService = cardService;
    }

    public MarketSellProposalEntity mapMarketSellProposalToEntity(MarketSellProposal proposal) throws UserNotFoundException, ApiURIException {
        MarketSellProposalEntity e = new MarketSellProposalEntity();
        e.setId(proposal.id());
        e.setPrice(proposal.price());
        e.setCardId(proposal.card().getId());
        User ue = userService.findUserById(proposal.seller().id());

        e.setSeller(userEntityMapper.userToUserEntity(ue));

        return e;
    }

    public MarketSellProposal mapEntityToMarketSellProposal(MarketSellProposalEntity marketSellProposalEntity) throws CardNotFoundException, URISyntaxException {
        return new MarketSellProposal(marketSellProposalEntity.getId(),
                cardService.findCardById(marketSellProposalEntity.getCardId()),
                userEntityMapper.userEntityToUser(marketSellProposalEntity.getSeller()),
                marketSellProposalEntity.getPrice());
    }
}

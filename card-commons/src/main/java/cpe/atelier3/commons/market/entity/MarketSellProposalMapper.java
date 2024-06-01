package cpe.atelier3.commons.market.entity;

import cpe.atelier3.commons.api.card.CardApi;
import cpe.atelier3.commons.api.exception.ApiURIException;
import cpe.atelier3.commons.api.user.UserApi;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.market.MarketSellProposal;
import cpe.atelier3.commons.user.User;
import cpe.atelier3.commons.user.entity.UserEntityMapper;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class MarketSellProposalMapper {
    private final CardApi cardService;
    private UserEntityMapper userEntityMapper;

    public MarketSellProposalMapper(@Autowired UserEntityMapper userEntityMapper, CardApi cardService) {
        this.userEntityMapper = userEntityMapper;
        this.cardService = cardService;
    }

    public MarketSellProposalEntity mapMarketSellProposalToEntity(MarketSellProposal proposal) throws UserNotFoundException {
        MarketSellProposalEntity e = new MarketSellProposalEntity();
        e.setId(proposal.id());
        e.setPrice(proposal.price());
        e.setCardId(proposal.card().getId());
        e.setSeller(userEntityMapper.userToUserEntity(proposal.seller()));

        return e;
    }

    public MarketSellProposal mapEntityToMarketSellProposal(MarketSellProposalEntity marketSellProposalEntity) throws CardNotFoundException, URISyntaxException {
        return new MarketSellProposal(marketSellProposalEntity.getId(),
                cardService.findCardById(marketSellProposalEntity.getCardId()),
                userEntityMapper.userEntityToUser(marketSellProposalEntity.getSeller()),
                marketSellProposalEntity.getPrice());
    }
}

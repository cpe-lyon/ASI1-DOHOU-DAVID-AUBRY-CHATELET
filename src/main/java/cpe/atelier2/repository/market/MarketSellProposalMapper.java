package cpe.atelier2.repository.market;

import cpe.atelier2.domain.market.MarketSellProposal;
import cpe.atelier2.domain.user.User;
import cpe.atelier2.domain.user.UserService;
import cpe.atelier2.repository.user.UserEntity;
import cpe.atelier2.repository.user.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MarketSellProposalMapper {
    private final UserService userService;
    private UserEntityMapper userEntityMapper;

    public MarketSellProposalMapper(@Autowired UserEntityMapper userEntityMapper, UserService userService) {
        this.userEntityMapper = userEntityMapper;
        this.userService = userService;
    }

    public MarketSellProposalEntity mapMarketSellProposalToEntity(MarketSellProposal proposal) {
        MarketSellProposalEntity e = new MarketSellProposalEntity();
        e.setId(proposal.id());
        e.setPrice(proposal.price());
        e.setCardId(proposal.cardId());
        Optional<User> ue = userService.findUserById(proposal.seller());
        if (ue.isEmpty()) {
            return null;
        }
        e.setSeller(userEntityMapper.userToUserEntity(ue.get()));

        return e;
    }

    public MarketSellProposal mapEntityToMarketSellProposal(MarketSellProposalEntity marketSellProposalEntity) {
        return new MarketSellProposal(marketSellProposalEntity.getId(),
                marketSellProposalEntity.getCardId(),
                marketSellProposalEntity.getSeller().getId(),
                marketSellProposalEntity.getPrice());
    }
}

package cpe.atelier2.controller.market;

import cpe.atelier2.domain.card.CardService;
import cpe.atelier2.domain.card.exceptions.CardNotFoundException;
import cpe.atelier2.domain.market.MarketSellProposal;
import cpe.atelier2.domain.user.UserService;
import cpe.atelier2.domain.user.exception.UserNotFoundException;
import cpe.atelier2.repository.market.MarketSellProposalEntity;
import org.springframework.stereotype.Component;

@Component
public class MarketSellProposalDtoMapper {

    UserService userService;
    CardService cardService;

    public MarketSellProposalDtoMapper(UserService userService) {
        this.userService = userService;
    }

    public MarketSellProposal mapMarketSellProposalRequestDtoToMarketSellProposal(
            MarketSellProposalRequestDTO proposalRequestDTO) throws UserNotFoundException, CardNotFoundException {
        return new MarketSellProposal(null, cardService.findCardById(proposalRequestDTO.cardToSellId()), userService.findUserById(proposalRequestDTO.sellerId()), proposalRequestDTO.price());
    }

    public MarketSellProposalDto mapMarketSellProposalToDto(MarketSellProposal e) {
        return new MarketSellProposalDto(e.id(), e.card(), e.seller(), e.price());
    }

}

package cpe.atelier2.controller.market;

import cpe.atelier2.controller.card.CardDtoMapper;
import cpe.atelier2.controller.user.UserDtoMapper;
import cpe.atelier2.domain.card.CardService;
import cpe.atelier2.domain.card.exception.CardNotFoundException;
import cpe.atelier2.domain.market.MarketSellProposal;
import cpe.atelier2.domain.user.UserService;
import cpe.atelier2.domain.user.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MarketSellProposalDtoMapper {

    UserService userService;
    CardService cardService;
    UserDtoMapper userDtoMapper;
    CardDtoMapper cardDtoMapper;

    public MarketSellProposalDtoMapper(UserService userService, CardService cardService, UserDtoMapper userDtoMapper, CardDtoMapper cardDtoMapper) {
        this.userService = userService;
        this.cardService = cardService;
        this.userDtoMapper = userDtoMapper;
        this.cardDtoMapper = cardDtoMapper;
    }

    public MarketSellProposal mapMarketSellProposalRequestDtoToMarketSellProposal(
            MarketSellProposalRequestDTO proposalRequestDTO) throws UserNotFoundException, CardNotFoundException {
        return new MarketSellProposal(null, cardService.findCardById(proposalRequestDTO.cardToSellId()), userService.findUserById(proposalRequestDTO.sellerId()), proposalRequestDTO.price());
    }

    public MarketSellProposalDto mapMarketSellProposalToDto(MarketSellProposal e) {
        return new MarketSellProposalDto(e.id(), cardDtoMapper.cardToCardDTO(e.card()), userDtoMapper.userToPublicUserDto(e.seller()), e.price());
    }

}

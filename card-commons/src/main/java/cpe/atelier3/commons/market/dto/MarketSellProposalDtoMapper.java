package cpe.atelier3.commons.market.dto;


import cpe.atelier3.commons.api.card.CardApi;
import cpe.atelier3.commons.api.exception.ApiURIException;
import cpe.atelier3.commons.api.user.UserApi;
import cpe.atelier3.commons.card.dto.CardDtoMapper;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.market.MarketSellProposal;
import cpe.atelier3.commons.user.dto.UserDtoMapper;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class MarketSellProposalDtoMapper {

    UserApi userService;
    CardApi cardService;
    UserDtoMapper userDtoMapper;
    CardDtoMapper cardDtoMapper;

    public MarketSellProposalDtoMapper(UserApi userService, CardApi cardService, UserDtoMapper userDtoMapper, CardDtoMapper cardDtoMapper) {
        this.userService = userService;
        this.cardService = cardService;
        this.userDtoMapper = userDtoMapper;
        this.cardDtoMapper = cardDtoMapper;
    }

    public MarketSellProposal mapMarketSellProposalRequestDtoToMarketSellProposal(
            MarketSellProposalRequestDTO proposalRequestDTO) throws UserNotFoundException, ApiURIException, URISyntaxException, CardNotFoundException {
        return new MarketSellProposal(null, cardService.findCardById(proposalRequestDTO.cardToSellId()), userService.findUserById(proposalRequestDTO.sellerId()), proposalRequestDTO.price());
    }

    public MarketSellProposalDto mapMarketSellProposalToDto(MarketSellProposal e) {
        return new MarketSellProposalDto(e.id(), cardDtoMapper.cardToCardDTO(e.card()), userDtoMapper.userToPublicUserDto(e.seller()), e.price());
    }

}

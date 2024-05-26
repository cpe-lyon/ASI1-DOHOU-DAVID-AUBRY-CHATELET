package cpe.atelier2.controller.market;

import cpe.atelier2.domain.market.MarketSellProposal;
import org.springframework.stereotype.Component;

@Component
public class MarketSellProposalDtoMapper {

    public MarketSellProposal mapMarketSellProposalRequestDtoToMarketSellProposal(
            MarketSellProposalRequestDTO proposalRequestDTO) {
        return new MarketSellProposal(null, proposalRequestDTO.cardToSellId(), proposalRequestDTO.sellerId(), proposalRequestDTO.price());
    }

}

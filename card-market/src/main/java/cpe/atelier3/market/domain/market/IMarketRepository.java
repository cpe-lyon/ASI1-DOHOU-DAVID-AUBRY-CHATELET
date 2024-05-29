package cpe.atelier3.market.domain.market;

import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.market.MarketSellProposal;

import java.util.List;
import java.util.Optional;

public interface IMarketRepository {
    List<MarketSellProposal> findAllMarketSellProposals();

    Optional<MarketSellProposal> findMarketSellProposalById(Long id);

    Optional<MarketSellProposal> findMarketSellProposalBySellerIdAndCardId(Long sellerId, Long cardId);

    MarketSellProposal createNewMarketSellProposal(MarketSellProposal marketSellProposal) throws CardNotFoundException;

    List<MarketSellProposal> findAllByUserId(Long userId);
}

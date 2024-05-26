package cpe.atelier2.domain.market;

import cpe.atelier2.domain.card.exception.CardNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IMarketRepository {
    List<MarketSellProposal> findAllMarketSellProposals();

    Optional<MarketSellProposal> findMarketSellProposalById(Long id);

    Optional<MarketSellProposal> findMarketSellProposalBySellerIdAndCardId(Long sellerId, Long cardId);

    MarketSellProposal createNewMarketSellProposal(MarketSellProposal marketSellProposal) throws CardNotFoundException;

    List<MarketSellProposal> findAllByUserId(Long userId);
}

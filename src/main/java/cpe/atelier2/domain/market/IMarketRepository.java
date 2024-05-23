package cpe.atelier2.domain.market;

import java.util.List;
import java.util.Optional;

public interface IMarketRepository {
    List<MarketSellProposal> findAllMarketSellProposals();

    Optional<MarketSellProposal> findMarketSellProposalById(String id);
}

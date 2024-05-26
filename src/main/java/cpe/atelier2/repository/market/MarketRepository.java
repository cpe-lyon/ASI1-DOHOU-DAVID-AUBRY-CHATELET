package cpe.atelier2.repository.market;

import cpe.atelier2.domain.market.IMarketRepository;
import cpe.atelier2.domain.market.MarketSellProposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MarketRepository implements IMarketRepository {

    MarketJpaRepository marketJpaRepository;
    MarketSellProposalMapper marketSellProposalMapper;

    public MarketRepository(@Autowired MarketJpaRepository marketJpaRepository,
                            @Autowired MarketSellProposalMapper marketSellProposalMapper) {
        this.marketJpaRepository = marketJpaRepository;
        this.marketSellProposalMapper = marketSellProposalMapper;
    }

    @Override
    public List<MarketSellProposal> findAllMarketSellProposals() {
        return marketJpaRepository.findAll().stream()
                .map(marketSellProposalMapper::mapEntityToMarketSellProposal)
                .toList();
    }

    @Override
    public Optional<MarketSellProposal> findMarketSellProposalById(String id) {
        return Optional.empty();
    }
}

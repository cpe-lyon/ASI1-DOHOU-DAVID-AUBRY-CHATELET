package cpe.atelier3.market.repository.market;

import cpe.atelier3.commons.api.exception.ApiURIException;
import cpe.atelier3.commons.card.exception.CardNotFoundException;
import cpe.atelier3.commons.market.MarketSellProposal;
import cpe.atelier3.commons.market.entity.MarketSellProposalEntity;
import cpe.atelier3.commons.market.entity.MarketSellProposalMapper;
import cpe.atelier3.commons.user.exception.UserNotFoundException;
import cpe.atelier3.market.domain.market.IMarketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class MarketRepository implements IMarketRepository {
    Logger logger = LoggerFactory.getLogger(MarketRepository.class);
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
                .map(this::mapEntityToMarketSellProposal)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public Optional<MarketSellProposal> findMarketSellProposalById(Long id) {
        return marketJpaRepository.findById(id).map(this::mapEntityToMarketSellProposal);
    }

    @Override
    public Optional<MarketSellProposal> findMarketSellProposalBySellerIdAndCardId(Long sellerId, Long cardId) {
        return  marketJpaRepository.findByCardIdAndSellerId(cardId, sellerId)
                .map(this::mapEntityToMarketSellProposal);
    }

    @Override
    public MarketSellProposal createNewMarketSellProposal(MarketSellProposal marketSellProposal) throws CardNotFoundException {
        MarketSellProposalEntity entity = null;
        try {
            entity = marketSellProposalMapper.mapMarketSellProposalToEntity(marketSellProposal);
        } catch (UserNotFoundException | ApiURIException e) {
            throw new RuntimeException(e);
        }
        try {
            return marketSellProposalMapper.mapEntityToMarketSellProposal(marketJpaRepository.save(entity));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MarketSellProposal> findAllByUserId(Long userId) {
        return marketJpaRepository.findAllBySellerId(userId).stream()
                .map(this::mapEntityToMarketSellProposal)
                .filter(Objects::nonNull)
                .toList();
    }

    private MarketSellProposal mapEntityToMarketSellProposal(MarketSellProposalEntity marketSellProposalEntity) {
        try {
            return marketSellProposalMapper.mapEntityToMarketSellProposal(marketSellProposalEntity);
        } catch (CardNotFoundException | URISyntaxException e) { // TODO : Better handling for the URISyntaxException
            logger.error("Could not find the card for market sell proposal {}", marketSellProposalEntity.getId());
        }
        return null;
    }
}

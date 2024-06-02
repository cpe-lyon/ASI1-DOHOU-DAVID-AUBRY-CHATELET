package cpe.atelier3.market.repository.market;

import cpe.atelier3.commons.market.entity.MarketSellProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarketJpaRepository extends JpaRepository<MarketSellProposalEntity, Long> {
    Optional<MarketSellProposalEntity> findById(Long proposalId);
    Optional<MarketSellProposalEntity> findByCardIdAndSellerId(Long cardId, Long sellerId);
    List<MarketSellProposalEntity> findAllBySellerId(Long sellerId);
}

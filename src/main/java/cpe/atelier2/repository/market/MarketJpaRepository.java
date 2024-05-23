package cpe.atelier2.repository.market;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketJpaRepository extends JpaRepository<MarketSellProposalEntity, Long> {
    Optional<MarketSellProposalEntity> findById(Long proposalId);
}

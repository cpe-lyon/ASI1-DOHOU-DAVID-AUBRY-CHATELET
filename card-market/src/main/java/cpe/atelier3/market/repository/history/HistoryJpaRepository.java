package cpe.atelier3.market.repository.history;

import cpe.atelier3.commons.market.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryJpaRepository extends JpaRepository<HistoryEntity, Long> {
}

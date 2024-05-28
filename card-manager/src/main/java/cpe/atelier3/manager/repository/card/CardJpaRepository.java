package cpe.atelier3.manager.repository.card;

import cpe.atelier3.commons.card.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findByName(String name);
}

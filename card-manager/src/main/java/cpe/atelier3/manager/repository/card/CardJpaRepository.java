package cpe.atelier3.manager.repository.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findByName(String name);
}

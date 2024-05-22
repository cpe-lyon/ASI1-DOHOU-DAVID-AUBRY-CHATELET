package cpe.atelier2.repository.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<CardEntity, Integer> {
    CardEntity findByName(String name);
}

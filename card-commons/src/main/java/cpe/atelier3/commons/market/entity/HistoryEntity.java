package cpe.atelier3.commons.market.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "history")
public class HistoryEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long cardId;
    private Long userId;
    private String type;
    private double price;
}

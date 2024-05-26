package cpe.atelier2.repository.market;

import cpe.atelier2.repository.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MarketSellProposalEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long cardId;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;

    private double price;
}

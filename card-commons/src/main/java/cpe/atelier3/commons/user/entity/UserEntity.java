package cpe.atelier3.commons.user.entity;

import cpe.atelier3.commons.card.entity.CardEntity;
import cpe.atelier3.commons.market.entity.MarketSellProposalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "app_user")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    private double money;

    @ManyToMany(cascade = jakarta.persistence.CascadeType.MERGE)
    @JoinTable(
            name = "user_card",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "Card_id")
    )
    List<CardEntity> cardEntityList;

    @OneToMany
    List<MarketSellProposalEntity> marketSellProposals;
}

package cpe.atelier2.repository.user;

import cpe.atelier2.repository.card.CardEntity;
import cpe.atelier2.repository.market.MarketSellProposalEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

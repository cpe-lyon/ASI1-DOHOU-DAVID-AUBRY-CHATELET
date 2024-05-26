package cpe.atelier2.repository.card;

import cpe.atelier2.repository.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Card")
public class CardEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String description;
        private String family;
        private String affinity;
        private String imgUrl;
        private String smallImgUrl;
        private float energy;
        private float hp;
        private float defence;
        private float attack;
        private float price;
        @ManyToMany(mappedBy = "cardEntityList")
        List<UserEntity> userEntityList;
}

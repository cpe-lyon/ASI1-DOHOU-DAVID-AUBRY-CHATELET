package cpe.atelier2.repository.card;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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
}

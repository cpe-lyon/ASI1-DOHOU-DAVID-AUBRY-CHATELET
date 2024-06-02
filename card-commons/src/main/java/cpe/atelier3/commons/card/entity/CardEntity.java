package cpe.atelier3.commons.card.entity;

import cpe.atelier3.commons.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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
        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinTable(
                name = "user_marketsellproposals",
                inverseJoinColumns = @JoinColumn(name = "app_user_id"),
                joinColumns = @JoinColumn(name = "marketsellproposal_id")
        )
        List<UserEntity> userEntityList;

        @Override
        public final boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof CardEntity that)) return false;

            return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
        }

        @Override
        public int hashCode() {
                int result = Objects.hashCode(getId());
                result = 31 * result + Objects.hashCode(getName());
                return result;
        }
}

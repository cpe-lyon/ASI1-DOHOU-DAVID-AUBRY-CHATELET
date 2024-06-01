package cpe.atelier3.commons.card;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Card {
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

    public Card(Long id, String name, String description, String family, String affinity, String imgUrl, String smallImgUrl, float energy, float hp, float defence, float attack, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.family = family;
        this.affinity = affinity;
        this.imgUrl = imgUrl;
        this.smallImgUrl = smallImgUrl;
        this.energy = energy;
        this.hp = hp;
        this.defence = defence;
        this.attack = attack;
        this.price = price;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;

        return Float.compare(getEnergy(), card.getEnergy()) == 0 && Float.compare(getHp(), card.getHp()) == 0 && Float.compare(getDefence(), card.getDefence()) == 0 && Float.compare(getAttack(), card.getAttack()) == 0 && getId().equals(card.getId()) && getName().equals(card.getName()) && Objects.equals(getFamily(), card.getFamily());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}

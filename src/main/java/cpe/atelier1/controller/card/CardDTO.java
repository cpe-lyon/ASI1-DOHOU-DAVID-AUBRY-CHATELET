package cpe.atelier1.controller.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {

    private int id;
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

    public CardDTO(int id, String name, String description, String family, String affinity, String imgUrl, String smallImgUrl, float energy, float hp, float defence, float attack, float price) {
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
}


package cpe.atelier1.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CardFormDTO {

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
    private int userId;

    public CardFormDTO(){
        this.id = 0;
        this.name = "";
        this.description = "";
        this.family = "";
        this.affinity = "";
        this.imgUrl = "";
        this.smallImgUrl = "";
        this.energy = 0;
        this.hp = 0;
        this.defence = 0;
        this.attack = 0;
        this.price = 0;
        this.userId = 0;
    }
    public CardFormDTO(int id, String name, String description, String family, String affinity, String imgUrl, String smallImgUrl, float energy, float hp, float defence, float attack, float price, int userId) {
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
        this.userId = userId;
    }
}


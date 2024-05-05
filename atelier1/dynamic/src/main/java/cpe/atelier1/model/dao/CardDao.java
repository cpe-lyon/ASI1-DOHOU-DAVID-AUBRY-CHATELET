package cpe.atelier1.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpe.atelier1.model.Card;
import org.springframework.stereotype.Service;

@Service
public class CardDao {
    private List<Card> myCardList;
    private Random randomGenerator;

    public CardDao() {
        myCardList =new ArrayList<>();
        randomGenerator = new Random();
        createCardList();
    }

    private void createCardList() {

        Card p1=new Card(1,"pinky", "pink", "pink poney", "water","http://ekladata.com/9-cPSlYvrenNHMVawFmf_gLx8Jw.gif",
                "http://ekladata.com/9-cPSlYvrenNHMVawFmf_gLx8Jw.gif",10,20,
                15,2 ,250,0);
        Card p2=new Card(2,"grenny", "green", "green poney", "water","http://ekladata.com/9-cPSlYvrenNHMVawFmf_gLx8Jw.gif",
                "http://ekladata.com/9-cPSlYvrenNHMVawFmf_gLx8Jw.gif",10,20,
                18,2 ,250,0);
        myCardList.add(p1);
        myCardList.add(p2);
    }
    public List<Card> getCardList() {
        return this.myCardList;
    }
    public Card getCardByName(String name){
        for (Card cardBean : myCardList) {
            if(cardBean.getName().equals(name)){
                return cardBean;
            }
        }
        return null;
    }
    public Card getRandomCard(){
        int index=randomGenerator.nextInt(this.myCardList.size());
        return this.myCardList.get(index);
    }

    public Card addCard(int id, String name, String description, String family, String affinity,
                        String imgUrl, String smallImgUrl, float energy, float hp, float defence,
                        float attack, float price, int userId) {
        Card p=new Card(id, name, description, family, affinity, imgUrl, smallImgUrl, energy,
                hp, defence, attack, price, userId);
        this.myCardList.add(p);
        return p;
    }
}


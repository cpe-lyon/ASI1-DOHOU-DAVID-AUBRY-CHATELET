package cpe.atelier3.commons.user;

import cpe.atelier3.commons.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
public class User {
    private Long id;
    private String email;
    private String username;
    private String password;
    private double money;
    private List<Card> cardList;

    public User(Long id, String email, String username, String password, double money, List<Card> cardList) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.money = money;
        this.cardList = cardList;
    }

    public Long id() {
        return id;
    }

    public String email() {
        return email;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public double money() {
        return money;
    }

    public List<Card> cardList() {
        return cardList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Double.doubleToLongBits(this.money) == Double.doubleToLongBits(that.money) &&
                Objects.equals(this.cardList, that.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, money, cardList);
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id + ", " +
                "email=" + email + ", " +
                "username=" + username + ", " +
                "password=" + password + ", " +
                "money=" + money + ", " +
                "cardList=" + cardList + ']';
    }

}

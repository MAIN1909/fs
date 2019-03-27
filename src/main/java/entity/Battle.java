package entity;

import java.io.Serializable;
import java.util.List;

public class Battle implements Serializable {
    private int id;

    private User player1;
    private User player2;

    private List<Card> deck1;
    private List<Card> deck2;

    private List<Card> inHand1;
    private List<Card> inHand2;

    private List<Card> onTable1;
    private List<Card> onTable2;

    private int numberOfMove;
    private boolean isMove1;

    private int mana1;
    private int mana2;

    private int hp1;
    private int hp2;

    private boolean isHeroPowered1;
    private boolean isHeroPowered2;

    private boolean IsHeroUsePower;

    private Integer fromHandChoosen;
    private Integer fromTableChoosen;

    private int battlePointsPlayer1;
    private int battlePointsPlayer2;

    public Battle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public List<Card> getDeck1() {
        return deck1;
    }

    public void setDeck1(List<Card> deck1) {
        this.deck1 = deck1;
    }

    public List<Card> getDeck2() {
        return deck2;
    }

    public void setDeck2(List<Card> deck2) {
        this.deck2 = deck2;
    }

    public List<Card> getInHand1() {
        return inHand1;
    }

    public void setInHand1(List<Card> inHand1) {
        this.inHand1 = inHand1;
    }

    public List<Card> getInHand2() {
        return inHand2;
    }

    public void setInHand2(List<Card> inHand2) {
        this.inHand2 = inHand2;
    }

    public List<Card> getOnTable1() {
        return onTable1;
    }

    public void setOnTable1(List<Card> onTable1) {
        this.onTable1 = onTable1;
    }

    public List<Card> getOnTable2() {
        return onTable2;
    }

    public void setOnTable2(List<Card> onTable2) {
        this.onTable2 = onTable2;
    }

    public int getNumberOfMove() {
        return numberOfMove;
    }

    public void setNumberOfMove(int numberOfMove) {
        this.numberOfMove = numberOfMove;
    }

    public boolean isMove1() {
        return isMove1;
    }

    public void setMove1(boolean move1) {
        isMove1 = move1;
    }

    public int getMana1() {
        return mana1;
    }

    public void setMana1(int mana1) {
        this.mana1 = mana1;
    }

    public int getMana2() {
        return mana2;
    }

    public void setMana2(int mana2) {
        this.mana2 = mana2;
    }

    public int getHp1() {
        return hp1;
    }

    public void setHp1(int hp1) {
        this.hp1 = hp1;
    }

    public int getHp2() {
        return hp2;
    }

    public void setHp2(int hp2) {
        this.hp2 = hp2;
    }

    public boolean isHeroPowered1() {
        return isHeroPowered1;
    }

    public void setHeroPowered1(boolean heroPowered1) {
        isHeroPowered1 = heroPowered1;
    }

    public boolean isHeroPowered2() {
        return isHeroPowered2;
    }

    public void setHeroPowered2(boolean heroPowered2) {
        isHeroPowered2 = heroPowered2;
    }

    public Integer getFromHandChoosen() {
        return fromHandChoosen;
    }

    public void setFromHandChoosen(Integer fromHandChoosen) {
        this.fromHandChoosen = fromHandChoosen;
    }

    public Integer getFromTableChoosen() {
        return fromTableChoosen;
    }

    public void setFromTableChoosen(Integer fromTableChoosen) {
        this.fromTableChoosen = fromTableChoosen;
    }

    public boolean isHeroUsePower() {
        return IsHeroUsePower;
    }

    public void setHeroUsePower(boolean heroUsePower) {
        IsHeroUsePower = heroUsePower;
    }

    public int getBattlePointsPlayer1() {
        return battlePointsPlayer1;
    }

    public void setBattlePointsPlayer1(int battlePointsPlayer1) {
        this.battlePointsPlayer1 = battlePointsPlayer1;
    }

    public int getBattlePointsPlayer2() {
        return battlePointsPlayer2;
    }

    public void setBattlePointsPlayer2(int battlePointsPlayer2) {
        this.battlePointsPlayer2 = battlePointsPlayer2;
    }
}

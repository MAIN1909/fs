package service;

import collections.Battles;
import entity.Battle;
import entity.Card;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class BattleService {

    private final CardService cardService;
    private final Battles battles;

    @Autowired
    public BattleService(CardService cardService, Battles battles) {
        this.cardService = cardService;
        this.battles = battles;
    }

    public int createBattle(User player1, User player2) {
        Battle b = new Battle();
        b.setId(new Random().nextInt());
        b.setPlayer1(player1);
        b.setPlayer2(player2);
        List<Card> p1cards = cardService.getUserCards(player1.getDeck());
        List<Card> p2cards = cardService.getUserCards(player2.getDeck());
        b.setDeck1(p1cards);
        b.setDeck2(p2cards);
        b.setInHand1(cardService.getAndRemoveTwoCardsFromTen(p1cards));
        b.setInHand2(cardService.getAndRemoveTwoCardsFromTen(p2cards));
        b.setOnTable1(new LinkedList<>());
        b.setOnTable2(new LinkedList<>());

        b.setNumberOfMove(1);
        b.setMove1(true);

        b.setMana1(1);
        b.setMana2(1);

        b.setHp1(20);
        b.setHp2(20);

        b.setHeroPowered1(false);
        b.setHeroPowered2(false);

        b.setFromHandChoosen1(0);

        b.setFromTableChoosen1(0);

        battles.getBattleList().put(b.getId(), b);

        return b.getId();
    }

    public Battle isUserInBattle(String login) {
        for (Battle b : battles.getBattleList().values()) {
            if (b.getPlayer1().getLogin().equals(login) ||
                    b.getPlayer2().getLogin().equals(login)) {
                return b;
            }
        }
        return null;
    }

    public Battle getBattleById(Integer id) {
        return battles.getBattleList().get(id);
    }

    public Battle inverse(Battle battle) {
        User promU = battle.getPlayer1();
        battle.setPlayer1(battle.getPlayer2());
        battle.setPlayer2(promU);

        List<Card> promDeck = battle.getDeck1();
        battle.setDeck1(battle.getDeck2());
        battle.setDeck2(promDeck);

        List<Card> promHand = battle.getInHand1();
        battle.setInHand1(battle.getInHand2());
        battle.setInHand2(promHand);

        List<Card> promTable = battle.getOnTable1();
        battle.setOnTable1(battle.getOnTable2());
        battle.setOnTable2(promTable);

        battle.setMove1(false);

        int promMana = battle.getMana1();
        battle.setMana1(battle.getMana2());
        battle.setMana2(promMana);

        int promHp = battle.getHp1();
        battle.setHp1(battle.getHp2());
        battle.setHp2(promHp);

        return battle;


    }
}
package service;

import collections.ActivePlayers;
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
    private final ActivePlayers activePlayers;

    @Autowired
    public BattleService(CardService cardService, Battles battles, ActivePlayers activePlayers) {
        this.cardService = cardService;
        this.battles = battles;
        this.activePlayers = activePlayers;
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
        b.setHeroUsePower(false);
        battles.getBattleList().put(b.getId(), b);

        return b.getId();
    }

//    @Deprecated
//    public Battle isUserInBattle(String login) {
//        for (Battle b : battles.getBattleList().values()) {
//            if (b.getPlayer1().getLogin().equals(login) ||
//                    b.getPlayer2().getLogin().equals(login)) {
//                return b;
//            }
//        }
//        return null;
//    }

    public Integer isUserInBattle2(String login) {
        for (String l : activePlayers.getActivePlayersList().keySet()) {
            if (l.equals(login)) {
                return activePlayers.getActivePlayersList().get(login);
            }
        }
        return null;
    }


    public Battle getBattleById(Integer id) {
        if (id != null) {
            return battles.getBattleList().get(id);
        }
        return null;
    }

    public Battle inverse(Battle battle) {
        Battle bat = new Battle();
        bat.setPlayer1(battle.getPlayer2());
        bat.setPlayer2(battle.getPlayer1());

        bat.setDeck1(battle.getDeck2());
        bat.setDeck2(battle.getDeck1());

        bat.setInHand1(battle.getInHand2());
        bat.setInHand2(battle.getInHand1());

        bat.setOnTable1(battle.getOnTable2());
        bat.setOnTable2(battle.getOnTable1());

        bat.setMove1(!battle.isMove1());

        bat.setMana1(battle.getMana2());
        bat.setMana2(battle.getMana1());

        bat.setHp1(battle.getHp2());
        bat.setHp2(battle.getHp1());

        bat.setId(battle.getId());
        bat.setNumberOfMove(battle.getNumberOfMove());

        bat.setBattlePointsPlayer1(battle.getBattlePointsPlayer2());
        bat.setBattlePointsPlayer2(battle.getBattlePointsPlayer1());

        bat.setFromTableChoosen(battle.getFromTableChoosen());
        bat.setFromHandChoosen(battle.getFromHandChoosen());
        bat.setHeroPowered1(battle.isHeroPowered2());
        bat.setHeroPowered2(battle.isHeroPowered1());
        bat.setHeroUsePower(battle.isHeroUsePower());

        return bat;


    }

    public void processEndTurn(String userLogin, Battle b) {
        if (b.getPlayer2().getLogin().equals(userLogin)) {
            b.setNumberOfMove(b.getNumberOfMove() + 1);
            b.setMana1(b.getNumberOfMove());
        }
        b.setMove1(!b.isMove1());
        if (!b.isMove1()) {
            b.setMana2(b.getNumberOfMove());
        }
        b.setHeroPowered1(false);
        b.setHeroPowered2(false);
        b.setHeroUsePower(false);
        b.setFromHandChoosen(null);
        b.setFromHandChoosen(null);

        if (b.getPlayer2().getLogin().equals(userLogin)) {
            cardService.moveRandomCard(b.getDeck1(), b.getInHand1());
            cardService.moveRandomCard(b.getDeck2(), b.getInHand2());
        }
        for (Card c : b.getOnTable1()) {
            c.setCardCanMoove(true);
        }
        for (Card c : b.getOnTable2()) {
            c.setCardCanMoove(true);
        }

    }

    public void moveCardById(List<Card> from, List<Card> to, Integer id) {
        for (Card c : from) {
            if (id.equals(c.getId())) {
                to.add(c);
                from.remove(c);
                break;
            }
        }
    }

    public void doPlaceCard(Battle b, String login, Integer id) {
        if (b.getPlayer1().getLogin().equals(login)) {
            for (Card c : b.getInHand1()) {
                if (id.equals(c.getId())) {
                    b.getOnTable1().add(c);
                    b.getInHand1().remove(c);
                    b.setMana1(b.getMana1() - c.getCost());
                    break;
                }
            }
        } else {
            for (Card c : b.getInHand2()) {
                if (id.equals(c.getId())) {
                    b.getOnTable2().add(c);
                    b.getInHand2().remove(c);
                    b.setMana2(b.getMana2() - c.getCost());
                    break;
                }
            }
        }
    }

    public void doChooseOnTableLogic(Battle b, String login, Integer id) {
        if (b.getPlayer1().getLogin().equals(login)) {
            for (Card c : b.getOnTable1()) {
                if (id.equals(c.getId())) {
                    b.setFromTableChoosen(id);
                    break;
                }
            }
        } else {
            for (Card c : b.getOnTable2()) {
                if (id.equals(c.getId())) {
                    b.setFromTableChoosen(id);
                    break;
                }
            }
        }
    }

    public void doUnchooseOnTableLogic(Battle b, String login, Integer id) {
        if (b.getPlayer1().getLogin().equals(login)) {
            for (Card c : b.getOnTable1()) {
                if (id.equals(-c.getId())) {
                    b.setFromTableChoosen(null);
                    break;
                }
            }
        } else {
            for (Card c : b.getOnTable2()) {
                if (id.equals(-c.getId())) {
                    b.setFromTableChoosen(null);
                    break;
                }
            }
        }
    }

    public void doAttackCard(Battle b, String login, Integer id) {
        if (b.getPlayer1().getLogin().equals(login)) {
            Card defender = cardService.chooseCardFromListById(b.getOnTable2(), id);
            //Hero attack
            if (b.isHeroUsePower()) {
                defender.setArmor(defender.getArmor() - 1);
                //Points
                b.setBattlePointsPlayer1(b.getBattlePointsPlayer1() + 1);
                b.setHeroUsePower(false);
                //Card attack
            } else {
                Card attacker = cardService.chooseCardFromListById(b.getOnTable1(), b.getFromTableChoosen());
                //Points
                if (defender.getArmor() >= attacker.getDamage()) {
                    b.setBattlePointsPlayer1(b.getBattlePointsPlayer1() + attacker.getDamage());
                } else {
                    b.setBattlePointsPlayer1(b.getBattlePointsPlayer1() + defender.getArmor());
                }
                if (attacker.getArmor() >= defender.getDamage()) {
                    b.setBattlePointsPlayer2(b.getBattlePointsPlayer2() + defender.getDamage());
                } else {
                    b.setBattlePointsPlayer2(b.getBattlePointsPlayer2() + attacker.getArmor());
                }
                defender.setArmor(defender.getArmor() - attacker.getDamage());
                attacker.setArmor(attacker.getArmor() - defender.getDamage());

                if (attacker.getArmor() <= 0) {
                    b.getOnTable1().remove(attacker);
                }
                b.setFromTableChoosen(null);
                attacker.setCardCanMoove(false);
            }
            if (defender.getArmor() <= 0) {
                b.getOnTable2().remove(defender);
            }
        } else {
            Card defender = cardService.chooseCardFromListById(b.getOnTable1(), id);
            //Hero attack
            if (b.isHeroUsePower()) {
                defender.setArmor(defender.getArmor() - 1);
                //Points
                b.setBattlePointsPlayer2(b.getBattlePointsPlayer2() + 1);
                b.setHeroUsePower(false);
                //Card attack
            } else {
                Card attacker = cardService.chooseCardFromListById(b.getOnTable2(), b.getFromTableChoosen());
                //Points
                if (defender.getArmor() >= attacker.getDamage()) {
                    b.setBattlePointsPlayer2(b.getBattlePointsPlayer2() + attacker.getDamage());
                } else {
                    b.setBattlePointsPlayer2(b.getBattlePointsPlayer2() + defender.getArmor());
                }
                if (attacker.getArmor() >= defender.getDamage()) {
                    b.setBattlePointsPlayer1(b.getBattlePointsPlayer1() + defender.getDamage());
                } else {
                    b.setBattlePointsPlayer1(b.getBattlePointsPlayer1() + attacker.getArmor());
                }
                defender.setArmor(defender.getArmor() - attacker.getDamage());
                attacker.setArmor(attacker.getArmor() - defender.getDamage());
                if (attacker.getArmor() <= 0) {
                    b.getOnTable2().remove(attacker);
                }
                b.setFromTableChoosen(null);
                attacker.setCardCanMoove(false);
            }
            if (defender.getArmor() <= 0) {
                b.getOnTable1().remove(defender);
            }
        }
    }

    public void addActivePlayer(User u1, User u2, int battleId) {
        activePlayers.getActivePlayersList().put(u1.getLogin(), battleId);
        activePlayers.getActivePlayersList().put(u2.getLogin(), battleId);
    }


    public void doHeroPower(Battle b, String login) {
        b.setHeroUsePower(true);
        if (b.getPlayer1().getLogin().equals(login)) {
            b.setMana1(b.getMana1() - 2);
            b.setHeroPowered1(true);
        } else {
            b.setMana2(b.getMana2() - 2);
            b.setHeroPowered2(true);
        }
//        b.setHeroUsePower(false);
    }

    public void doAttackHero(Battle b, String login) {
        if (b.getPlayer1().getLogin().equals(login)) {
            //Hero attack
            if (b.isHeroUsePower()) {
                b.setHp2(b.getHp2() - 1);
                //Points
                b.setBattlePointsPlayer1(b.getBattlePointsPlayer1() + 1);
                b.setHeroUsePower(false);
            } else {
                //Card attack
                Card attacker = cardService.chooseCardFromListById(b.getOnTable1(), b.getFromTableChoosen());
                //Points
                if (b.getHp2() >= attacker.getDamage()) {
                    b.setBattlePointsPlayer1(b.getBattlePointsPlayer1() + attacker.getDamage());
                } else {
                    b.setBattlePointsPlayer1(b.getBattlePointsPlayer1() + b.getHp2());
                }
                b.setHp2(b.getHp2() - attacker.getDamage());
                b.setFromTableChoosen(null);
                attacker.setCardCanMoove(false);
//            b.setHeroPowered1(false);
            }
        } else {
            //Hero attack
            if (b.isHeroUsePower()) {
                b.setHp1(b.getHp1() - 1);
                //Points
                b.setBattlePointsPlayer2(b.getBattlePointsPlayer2() + 1);
                b.setHeroUsePower(false);
            } else {
                //Card attack
                Card attacker = cardService.chooseCardFromListById(b.getOnTable2(), b.getFromTableChoosen());
                //Points
                if (b.getHp1() >= attacker.getDamage()) {
                    b.setBattlePointsPlayer2(b.getBattlePointsPlayer2() + attacker.getDamage());
                } else {
                    b.setBattlePointsPlayer2(b.getBattlePointsPlayer2() + b.getHp1());
                }
                b.setHp1(b.getHp1() - attacker.getDamage());
                b.setFromTableChoosen(null);
                attacker.setCardCanMoove(false);
//            b.setHeroPowered2(false);
            }
        }
    }

    public boolean isSomeHeroDead(Battle b) {
        return (b.getHp1() <= 0 || b.getHp2() <= 0);
    }
}

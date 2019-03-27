package service;

import collections.ActivePlayers;
import collections.Battles;
import dao.UserDao;
import entity.Battle;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinishService {
    private final Battles battles;
    private final UserDao userDao;
    private final ActivePlayers activePlayers;
    private static Battle battle;
    private static boolean firstExit = false;
    @Autowired
    private FinishService(UserDao userDao, Battles battles, ActivePlayers activePlayers) {
        this.userDao = userDao;
        this.battles = battles;
        this.activePlayers = activePlayers;
    }


    public Battle calculatePoints(Battle b) {
        User u1 = b.getPlayer1();
        User u2 = b.getPlayer2();
        if (b.getHp1() <= 0) {
            u1.setPoints(u1.getPoints() + (b.getBattlePointsPlayer1() / 2));
            if (u1.getStars() == 0 && u1.getLvl() > 0) {
                u1.setLvl(u1.getLvl() - 1);
                u1.setStars(4);
            } else if (u1.getLvl() > 0 && u1.getStars() >= 0) {
                u1.setStars(u1.getStars() - 1);
            }
            u2.setPoints(u2.getPoints() + b.getBattlePointsPlayer2());
            u2.setStars(u2.getStars() + 1);
            if (u2.getStars() == 5) {
                u2.setLvl(u2.getLvl() + 1);
                u2.setStars(0);
            }
        } else if (b.getHp2() <= 0) {
            u2.setPoints(u2.getPoints() + b.getBattlePointsPlayer2() / 2);
            if (u2.getStars() == 0 && u2.getLvl() > 0) {
                u2.setLvl(u2.getLvl() - 1);
                u2.setStars(4);
            } else if (u2.getStars() >= 0 && u2.getLvl() > 0) {
                u2.setStars(u2.getStars() - 1);
            }
            u1.setPoints(u1.getPoints() + b.getBattlePointsPlayer1());
            u1.setStars(u1.getStars() + 1);
            if (u1.getStars() == 5) {
                u1.setLvl(u1.getLvl() + 1);
                u1.setStars(0);
            }
            userDao.update(u1);
            userDao.update(u2);
            b.setPlayer1(u1);
            b.setPlayer2(u2);
        }
        return b;
    }

    public void deleteBattle(Integer id) {
        for (Integer ids : battles.getBattleList().keySet()) {
            if (ids.equals(id)) {
                battles.getBattleList().remove(id);
            }
        }
    }

    public void deleteActivePlayers(Battle b) {
        if (activePlayers.getActivePlayersList().containsKey(b.getPlayer1().getLogin())) {
            activePlayers.getActivePlayersList().remove(b.getPlayer1().getLogin());
        }
        if (activePlayers.getActivePlayersList().containsKey(b.getPlayer2().getLogin())) {
            activePlayers.getActivePlayersList().remove(b.getPlayer2().getLogin());
        }
    }

    public Battle result(Battle bat) {
        if (battle == null) {
            battle = calculatePoints(bat);
        }
        return battle;
    }

    public boolean isFirstExit() {
        return firstExit;
    }

    public void setFirstExit() {
        firstExit = true;
    }

}

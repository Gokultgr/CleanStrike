package com.tgr.cleanstrike.utils;

import com.tgr.cleanstrike.actions.DefunctCoin;
import com.tgr.cleanstrike.actions.Move;
import com.tgr.cleanstrike.actions.MultiStrike;
import com.tgr.cleanstrike.actions.NoStrikeCoin;
import com.tgr.cleanstrike.actions.RedStrike;
import com.tgr.cleanstrike.actions.Strike;
import com.tgr.cleanstrike.actions.StrikerStrike;
import com.tgr.cleanstrike.model.CarromBoard;
import com.tgr.cleanstrike.model.Game;
import com.tgr.cleanstrike.model.Player;

public class PlayerUtils {
    private Player player;

    public PlayerUtils(Player player) {
        this.player = player;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getPlayerPoints() {
        return player.getPoints();
    }

    public void makeAMove(Game game, int playerInput) {
        Move move;
        switch (playerInput) {
            case 1:
                move = new Strike(player, game);
                if (move.isMovePossible()) {
                    move.action();
                    player.incrementStrikes();
                    player.addMovetoList(move);
                } else {
                    move = new NoStrikeCoin(player, game);
                    move.action();
                    player.incrementStrikes();
                    player.addMovetoList(move);
                }
                break;
            case 2:
                move = new MultiStrike(player, game);
                if (move.isMovePossible()) {
                    move.action();
                    player.incrementStrikes();
                    player.addMovetoList(move);
                } else {
                    move = new NoStrikeCoin(player, game);
                    move.action();
                    player.incrementStrikes();
                    player.addMovetoList(move);
                }
                break;
            case 3:
                move = new RedStrike(player, game);
                if (move.isMovePossible()) {
                    move.action();
                    player.incrementStrikes();
                    player.addMovetoList(move);
                } else {
                    move = new NoStrikeCoin(player, game);
                    move.action();
                    player.incrementStrikes();
                    player.addMovetoList(move);
                }
                break;
            case 4:
                move = new StrikerStrike(player, game);
                move.action();
                player.incrementStrikes();
                player.addMovetoList(move);
                break;
            case 5:
                move = new DefunctCoin(player, game);
                if (move.isMovePossible()) {
                    move.action();
                    player.incrementStrikes();
                    player.addMovetoList(move);
                } else {
                    move = new NoStrikeCoin(player, game);
                    move.action();
                    player.incrementStrikes();
                    player.addMovetoList(move);
                }
                break;
            case 6:
                move = new NoStrikeCoin(player, game);
                move.action();
                player.incrementStrikes();
                player.addMovetoList(move);
                break;
            default:
                printPlayerStats();
        }
    }

    void printPlayerStats() {
        player.toString();
    }

    public int updatePlayerPoints(boolean isPointsGained, int pointsGained, boolean isRed) {
        player.incrementPoints(pointsGained);
        if (isPointsGained) {
            player.initializeMoveWithNoPoints();
            player.setIsLastRed(isRed);
        } else { // if it is a foul and foul limit exceeds decrement one more point
            if (pointsGained < 0) { // if it is a foul and foul limit exceeds decrement one more point
                player.incrementStrikesWithFouls();
                if (player.getStrikeswithFouls() >= CarromBoard.CONSECUTIVE_FOULS_LIMIT) {
                    player.decrementPoints();
                }
            }
            player.incrementMoveWithNoPoints();
            if (player.getMoveWithNoPoints() == CarromBoard.CONSECUTIVE_NO_POINTS_LIMIT) {
                player.decrementPoints();
            }
        }
        return player.getPoints();
    }
}

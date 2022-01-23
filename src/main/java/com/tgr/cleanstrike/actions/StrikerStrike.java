package com.tgr.cleanstrike.actions;

import com.tgr.cleanstrike.model.Game;
import com.tgr.cleanstrike.model.Player;
import com.tgr.cleanstrike.utils.CarromBoardUtils;
import com.tgr.cleanstrike.utils.GameUtils;
import com.tgr.cleanstrike.utils.PlayerUtils;

public class StrikerStrike implements Move {
    PlayerUtils playerUtils;
    CarromBoardUtils carromBoardUtils;
    GameUtils gameUtils;
    int playerPoints;
    int coinsRemaining;
    public static final int POINTS_GAINED = -1;
    public static final int BLACK_COINS_DETECTED = 0;
    public static final int RED_COINS_DETECTED = 0;

    public StrikerStrike(Player player, Game game) {
        playerUtils = new PlayerUtils(player);
        carromBoardUtils = new CarromBoardUtils(game.getCarromBoard());
        gameUtils = new GameUtils(game);
    }

    @Override
    public void action() {
        playerPoints = playerUtils.updatePlayerPoints(false, POINTS_GAINED, false);
        coinsRemaining = carromBoardUtils
                .updateCarromBoardCoins(new int[] { BLACK_COINS_DETECTED, RED_COINS_DETECTED });
        if (coinsRemaining == 0) {
            gameUtils.calculateWinnerwithEmptyBoard();
        } else {
            gameUtils.calculateWinnerWithScore();
        }
        gameUtils.updateCurrentPlayer();
        makeMove();

    }

    @Override
    public void makeMove() {
        System.out.println(
                "Striker Strike!!!\nPlayer name at instance :: " + playerUtils.getPlayerName()
                        + "\tplayer's points after Move :: "
                        + playerUtils.getPlayerPoints() + "\nCoins remaining in board :: ");
        carromBoardUtils.printCoinsInBoard();
    }

    @Override
    public boolean isMovePossible() {
        return true;
    }

}

package com.tgr.cleanstrike.actions;

import com.tgr.cleanstrike.model.Game;
import com.tgr.cleanstrike.model.Player;
import com.tgr.cleanstrike.utils.CarromBoardUtils;
import com.tgr.cleanstrike.utils.GameUtils;
import com.tgr.cleanstrike.utils.PlayerUtils;

public class DefunctCoin implements Move {
    PlayerUtils playerUtils;
    CarromBoardUtils carromBoardUtils;
    GameUtils gameUtils;
    int playerPoints;
    int coinsRemaining;
    public static final int POINTS_GAINED = -2;
    public static final int BLACK_COINS_DETECTED = 1;
    public static final int RED_COINS_DETECTED = 1;

    public DefunctCoin(Player player, Game game) {
        playerUtils = new PlayerUtils(player);
        carromBoardUtils = new CarromBoardUtils(game.getCarromBoard());
        gameUtils = new GameUtils(game);
    }

    @Override
    public void action() {

        playerPoints = playerUtils.updatePlayerPoints(false, POINTS_GAINED, false);
        if (carromBoardUtils.getBlackAndRedCoinsCount()[0] >= BLACK_COINS_DETECTED) {
            coinsRemaining = carromBoardUtils
                    .updateCarromBoardCoins(new int[] { BLACK_COINS_DETECTED, 0 });
        } else if (carromBoardUtils.getBlackAndRedCoinsCount()[1] >= RED_COINS_DETECTED) {
            coinsRemaining = carromBoardUtils
                    .updateCarromBoardCoins(new int[] { 0, RED_COINS_DETECTED });
        }
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
                "Defunct Coin!!!\nPlayer name at instance :: " + playerUtils.getPlayerName()
                        + "\n\tplayer's points after Move :: "
                        + playerUtils.getPlayerPoints() + "\nCoins remaining in board :: ");
        carromBoardUtils.printCoinsInBoard();
    }

    @Override
    public boolean isMovePossible() {
        return carromBoardUtils.getCoins() >= BLACK_COINS_DETECTED;
    }

}

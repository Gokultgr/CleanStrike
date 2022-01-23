package com.tgr.cleanstrike.utils;

import java.util.List;

import com.tgr.cleanstrike.model.Game;
import com.tgr.cleanstrike.model.Player;

public class GameUtils {
    private Game game;

    public GameUtils(Game game) {
        this.game = game;
    }

    // updateCurrentPlayer() -- update currentplayer to next player
    public void updateCurrentPlayer() {
        game.setCurrentStriker(game.getPlayerList().moveToNextPlayer());
    }

    public Player getWinner() {
        return game.getWinner();
    }

    // calculateWinnerwithEmptyBoard() -- when the board is empty player with
    // highest point is winner and if points are equal its draw
    public void calculateWinnerwithEmptyBoard() {
        Player winner = game.getPlayerList().searchHighScoreInBoard();
        game.setWinner(winner);
        game.setIsGameOver(true);
    }

    // updateLeadingPlayer() -- Player leading in scores
    void updateLeadingPlayer() {
        Player winner = game.getPlayerList().searchHighScoreInBoard();
        game.setLeadingPlayer(winner);
    }

    // calculateWinnerWithScore() -- When the player has more than 5 points if he
    // has 3 points more than subsequent player he is the winner
    public void calculateWinnerWithScore() {
        updateLeadingPlayer();
        Player leadingPlayer = game.getLeadingPlayer();
        if (game.getLeadingPlayer() == null) {
            return;
        }
        if (game.getLeadingPlayer().getPoints() >= 5) {
            Player temp = game.getPlayerList().searchSecondHighScoreInBoard(leadingPlayer);
            if (leadingPlayer.getPoints() - temp.getPoints() >= 3) {
                game.setWinner(leadingPlayer);
                game.setIsGameOver(true);
            }
        }
    }

    public void printCurrentStandngs() {
        if (game.getLeadingPlayer() != null) {
            System.out.println("Leading Player :: " + game.getLeadingPlayer().getName());
        }
        if (game.isGameOver()) {
            game.getPlayerList().displayAllPlayers();
        }
    }

    public static GameUtils createAGame(List<Player> playerList) {
        Game game = new Game(playerList);
        return new GameUtils(game);
    }

    public Player getCurrentStriker() {
        return game.getCurrentStriker();
    }

    public boolean makeAMove(Player currentPlayer, int playerInput) {
        PlayerUtils playerUtils = new PlayerUtils(currentPlayer);
        playerUtils.makeAMove(game, playerInput);
        return game.isGameOver();
    }

}

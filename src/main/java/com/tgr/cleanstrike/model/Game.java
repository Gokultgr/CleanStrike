package com.tgr.cleanstrike.model;

import java.util.List;

import com.tgr.cleanstrike.utils.CircularLinkedList;

public class Game {
    private CarromBoard carromBoard;
    private final int noOfPlayers;
    private final CircularLinkedList players;
    private Player winner, leadingPlayer, currentStriker;
    private boolean gameOver;

    public Game(List<Player> players) {
        this.carromBoard = new CarromBoard();
        this.noOfPlayers = players.size();
        this.players = new CircularLinkedList(players);
        carromBoard = new CarromBoard();
        this.currentStriker = players.get(0);
        gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public CarromBoard getCarromBoard() {
        return carromBoard;
    }

    public void setIsGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLeadingPlayer() {
        return leadingPlayer;
    }

    public Player getCurrentStriker() {
        return currentStriker;
    }

    public int getNoOfPlayer() {
        return noOfPlayers;
    }

    public CircularLinkedList getPlayerList() {
        return players;
    }

    public void setLeadingPlayer(Player leadingPlayer) {
        this.leadingPlayer = leadingPlayer;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setCurrentStriker(Player currentStriker) {
        this.currentStriker = currentStriker;
    }

}

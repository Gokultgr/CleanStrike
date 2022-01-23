package com.tgr.cleanstrike.model;

public class CarromBoard {
    public final static int TOTAL_BLACK_COINS = 9;
    public final static int TOTAL_RED_COINS = 1;
    public final static int CONSECUTIVE_FOULS_LIMIT = 3;
    public final static int CONSECUTIVE_NO_POINTS_LIMIT = 3;
    private int curr_black_coins;
    private int curr_red_coins;

    CarromBoard() {
        curr_black_coins = TOTAL_BLACK_COINS;
        curr_red_coins = TOTAL_RED_COINS;
    }

    public int getBlackCoinCount() {
        return curr_black_coins;
    }

    public int getRedCoinCount() {
        return curr_red_coins;
    }

    public void deductBlackCoins(int blackCoins) {
        curr_black_coins -= blackCoins;
    }

    public void deductRedCoins(int redCoins) {
        curr_red_coins -= redCoins;
    }

    public int totalCoins() {
        return curr_black_coins + curr_red_coins;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\tNo. of Back Coins ::: " + curr_black_coins + "\n");
        sb.append("\tNo. of Red Coins ::: " + curr_red_coins);
        return sb.toString();
    }
}

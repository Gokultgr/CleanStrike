package com.tgr.cleanstrike.utils;

import com.tgr.cleanstrike.model.CarromBoard;

public class CarromBoardUtils {
    CarromBoard carromBoard;

    public CarromBoardUtils(CarromBoard carromBoard) {
        this.carromBoard = carromBoard;
    }

    public void printCoinsInBoard() {
        System.out.println(carromBoard.toString());
    }

    public int updateCarromBoardCoins(int coins_detected[]) {
        carromBoard.deductBlackCoins(coins_detected[0]);
        carromBoard.deductRedCoins(coins_detected[1]);
        return carromBoard.totalCoins();
    }

    public int[] getBlackAndRedCoinsCount() {
        int arr[] = new int[2];
        arr[0] = carromBoard.getBlackCoinCount();
        arr[1] = carromBoard.getRedCoinCount();
        return arr;
    }

    public int getCoins() {
        return carromBoard.totalCoins();
    }
}

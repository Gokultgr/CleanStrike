package com.tgr.cleanstrike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.tgr.cleanstrike.model.Player;
import com.tgr.cleanstrike.utils.GameUtils;

public class GameImplementation {

    void initiateCleanStrike() {

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("---Welcome to Clean Strike---");
        System.out.println("-----------------------------");
        int playerCount = 0;
        boolean flag = false;
        do {
            System.out.println("\nEnter no. of Players::");
            playerCount = sc.nextInt();
            if (playerCount < 2 || playerCount > 4) {
                System.out.println("No of players should be 2,3 or 4");
                flag = true;
            } else {
                flag = false;
            }
        } while (flag);
        Set<String> players = new HashSet<>();
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            boolean isInvalidName = false;
            System.out.println("\nEnter Player " + (i + 1) + " name ::");
            String playerName = "";
            do {
                playerName += sc.next();
                isInvalidName = false;
                if (!isValidPlayerName(playerName)) {
                    System.out.println("Invalid username");
                    isInvalidName = true;
                } else if (players.contains(playerName)) {
                    System.out.println("Name Already Taken");
                    isInvalidName = true;
                } else {
                    players.add(playerName);
                    Player player = new Player(playerName);
                    playerList.add(player);
                    break;
                }
                System.out.println("\nEnter Player " + (i + 1) + " name ::");
            } while (isInvalidName);
        }
        GameUtils gameUtils = GameUtils.createAGame(playerList);
        boolean isGameOver = false;
        do {
            Player currentPlayer = gameUtils.getCurrentStriker();
            System.out.print("\n" + currentPlayer.getName()
                    + " :: Choose a move from the following\n1. Strike \n2. MultiStrike \n3. RedStrike\n4. Striker strike\n5. Defunct Coin \n6. No Strike\n7. Exit\nOthers to print player stats\n>");
            int playerInput = sc.nextInt();
            if (playerInput == 7) {
                gameUtils.printCurrentStandngs();
                break;
            }
            isGameOver = gameUtils.makeAMove(currentPlayer, playerInput);
            gameUtils.printCurrentStandngs();
        } while (!isGameOver);
        if (gameUtils.getWinner() == null) {
            System.out.println("Match is Draw");
        } else {
            System.out.println("Match was won by " + gameUtils.getWinner().getName() + "!!!");
        }
        System.out.println("Bye Bye");
        sc.close();
    }

    private boolean isValidPlayerName(String name) {
        if (name == null || name.trim().equals("") || name.trim().equals("null") || name.length() == 0)
            return false;
        return true;
    }

    String gameSimulator(HashMap<String, List<Integer>> playerInputs) {
        String winner = null;
        List<Player> playersList = new ArrayList<>();
        Set<String> keys = playerInputs.keySet();
        Iterator<String> itr = keys.iterator();
        while (itr.hasNext()) {
            String playerName = (String) itr.next();
            Player player = new Player(playerName);
            playersList.add(player);
        }
        GameUtils gameUtils = GameUtils.createAGame(playersList);
        boolean isGameOver = false;
        int i = 0;
        while (!isGameOver) {
            for (String playerName : keys) {
                Player currentPlayer = gameUtils.getCurrentStriker();
                if (playerInputs.get(playerName).size() <= i) {
                    isGameOver = true;
                    break;
                }
                int playerInput = playerInputs.get(playerName).get(i);
                System.out.println("\n" + currentPlayer.getName()
                        + " :: Choose a move from the following\n1. Strike \n2. MultiStrike \n3. RedStrike\n4. Striker strike\n5. Defunct Coin \n6. No Strike\nOthers to print player stats\n> "
                        + playerInput);
                isGameOver = gameUtils.makeAMove(currentPlayer, playerInput);
                if (isGameOver) {
                    if (gameUtils.getWinner() == null) {
                        System.out.println("Match is Draw");
                        break;
                    } else {
                        System.out.println("Match was won by " + gameUtils.getWinner().getName() + "!!!");
                        winner = gameUtils.getWinner().getName();
                        break;
                    }
                }
            }
            i++;
        }
        gameUtils.printCurrentStandngs();
        System.out.println("Bye Bye");
        return winner;
    }
}

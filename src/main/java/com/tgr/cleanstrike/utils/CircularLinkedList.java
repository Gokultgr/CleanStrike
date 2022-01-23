package com.tgr.cleanstrike.utils;

import java.util.List;

import com.tgr.cleanstrike.model.Player;

public final class CircularLinkedList {
    static class Node {
        Player player;
        Node next;

        Node(Player player) {
            this.player = player;
            this.next = null;
        }
    }

    Node head = null;
    Node curNode = null;

    public CircularLinkedList(List<Player> players) {
        for (Player player : players) {
            push(player);
        }
        curNode = head;
    }

    private void push(Player player) {
        Node new_node = new Node(player);
        if (head == null) {
            new_node.next = new_node;
            head = new_node;
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = new_node;
            new_node.next = head;
        }

    }

    Player moveToNextPlayer() {
        curNode = curNode.next;
        return curNode.player;
    }

    Player searchHighScoreInBoard() {
        Node temp = head;
        int max_point = Integer.MIN_VALUE;
        Player highScorer = null;
        do {
            Player p = temp.player;
            if (p.getPoints() > max_point) {
                max_point = p.getPoints();
                highScorer = p;
            } else if (p.getPoints() == max_point) {
                highScorer = null;
            }
            temp = temp.next;
        } while (temp != head);
        return highScorer;
    }

    Player searchSecondHighScoreInBoard(Player leadingPlayer) {
        Node temp = head;
        int max_point = Integer.MIN_VALUE;
        Player highScorer = null;
        do {
            Player p = temp.player;
            if (leadingPlayer != p) {
                if (p.getPoints() > max_point) {
                    max_point = p.getPoints();
                    highScorer = p;
                }
            }
            temp = temp.next;
        } while (temp != head);
        return highScorer;
    }

    void displayAllPlayers() {
        Node temp = head;
        do {
            Player p = temp.player;
            System.out.println(p.toString());
            temp = temp.next;
        } while (temp != head);
    }
}

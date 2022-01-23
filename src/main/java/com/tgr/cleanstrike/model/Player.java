package com.tgr.cleanstrike.model;

import java.util.List;
import java.util.ArrayList;
import com.tgr.cleanstrike.actions.Move;

public class Player {
    private final String name;
    private int points;
    private int totalStrikes;
    private int strikes_with_no_points;
    private int strikes_with_fouls;
    private boolean islastRead = false;
    private List<Move> moveList;

    public Player(String name) {
        this.name = name;
        points = totalStrikes = strikes_with_fouls = strikes_with_no_points = 0;
        this.moveList = new ArrayList<Move>();
    }

    public boolean getIsLastRed() {
        return islastRead;
    }

    public void setIsLastRed(boolean isLastRed) {
        this.islastRead = isLastRed;
    }

    public int getTotalStrikes() {
        return totalStrikes;
    }

    public void incrementStrikes() {
        totalStrikes++;
    }

    public void addMovetoList(Move move) {
        moveList.add(move);
    }

    public void incrementPoints() {
        points++;
    }

    public void decrementPoints() {
        points--;
    }

    public int getStrikeswithFouls() {
        return strikes_with_fouls;
    }

    public int getMoveWithNoPoints() {
        return strikes_with_no_points;
    }

    public void incrementStrikesWithFouls() {
        strikes_with_fouls++;
    }

    public void incrementMoveWithNoPoints() {
        strikes_with_no_points++;
    }

    public void initializeMoveWithNoPoints() {
        strikes_with_no_points = 0;
    }

    public void incrementPoints(int pointsGained) {
        this.points += pointsGained;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Player info :: " + name);
        sb.append("\n\tTotal Strikes Played :: " + totalStrikes);
        sb.append("\n\tTotal Points Earned :: " + points);
        sb.append("\n\tStrikes with no points :: " + strikes_with_no_points);
        sb.append("\n\tStrikes with fouls :: " + strikes_with_fouls);
        return sb.toString();
    }

    public String getName() {
        return name;
    }
}

package com.example.andresarango.memory_game_hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by andresarango on 9/28/16.
 */

public class SimonGame implements SequenceGameLogic {
    public List<SimonColors> userMoveSequence = new ArrayList<>();
    public List<SimonColors> gameMoveSequence = new ArrayList<>();
    public SimonColors currentMove;
    public int round;
    public int score;


    @Override
    public SimonColors getRandomColor() {
        int randNumber = getRandomNumber(4,1);
        switch(randNumber){
            case 1:
                return SimonColors.RED;
            case 2:
                return SimonColors.BLUE;
            case 3:
                return SimonColors.GREEN;
            case 4:
                return SimonColors.YELLOW;
            default:
                return null;
        }
    }

    @Override
    public int getRandomNumber(int max, int min) {
        Random rand = new Random();
        if(max > min){
        }else if(max == min){
            return 0;
        }else{
            max = min;
            min = max;
        }
        int randomNumber = rand.nextInt((max - min) + 1) + min;
        return randomNumber;
    }


    @Override
    public void addRandomColorToGameSequence() {
        gameMoveSequence.add(getRandomColor());
    }

    @Override
    public void setUserMove(SimonColors move) {
        currentMove = move;
    }

    @Override
    public boolean isUserMoveCorrect(SimonColors move, int moveNumber) {
        return gameMoveSequence.get(moveNumber).equals(move);
    }


    @Override
    public List<SimonColors> getGameSequence() {
        return gameMoveSequence;
    }

    @Override
    public void addToUserMoveHistory() {
        userMoveSequence.add(currentMove);
    }

    @Override
    public SimonColors getCorrectMove(int moveNumber) {
        return gameMoveSequence.get(moveNumber);
    }

    @Override
    public void addARound() {
        round +=1;
    }

    @Override
    public int getRound() {
        return round;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void increaseScore() {
        score += 1;
    }

    @Override
    public void resetGame() {
        userMoveSequence.clear();
        gameMoveSequence.clear();
        score = 0;
        round = 0;
        currentMove = null;
    }

    @Override
    public String convertEnumColorToString(SimonColors color) {
        switch(color){
            case BLUE:
                return "Blue";
            case RED:
                return "Red";
            case GREEN:
                return "Green";
            case YELLOW:
                return "Yellow";
            default:
                    return null;
        }
    }

    @Override
    public SimonColors convertStringToEnumColor(String move) {
        if(move.equalsIgnoreCase("yellow")){
            return SimonColors.YELLOW;
        }else if(move.equalsIgnoreCase("red")){
            return SimonColors.RED;
        }else if(move.equalsIgnoreCase("blue")){
            return SimonColors.BLUE;
        }else if(move.equalsIgnoreCase("green")){
            return SimonColors.GREEN;
        }
        return null;
    }

}
package com.example.andresarango.memory_game_hw5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by andresarango on 9/28/16.
 */

public class SimonGame implements SequenceGameLogic, Serializable {
    private List<SimonColors> gameMoveSequence = new ArrayList<>();
    private SimonColors currentMove;
    private int round = 1;
    private int score;
    private int mMoveNumber;


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



    public int getmMoveNumber() {return mMoveNumber;}


    @Override
    public List<SimonColors> getGameSequence() {
        return gameMoveSequence;
    }

    
    public void setUserMove(SimonColors move) {
        currentMove = move;
    }

    public void setmMoveNumber(int mMoveNumber) {this.mMoveNumber = mMoveNumber;}

    @Override
    public void addRandomColorToGameSequence() {
        gameMoveSequence.add(getRandomColor());
    }

    @Override
    public boolean isUserMoveCorrect() {
        return gameMoveSequence.get(mMoveNumber).equals(currentMove);
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
        gameMoveSequence.clear();
        score = 0;
        round = 0;
        currentMove = null;
    }

    @Override
    public int remainingNumberOfMoves() {
        return gameMoveSequence.size() - mMoveNumber - 1;
    }

    @Override
    public void startNextRound() {
        addARound();
        addRandomColorToGameSequence();
        increaseScore();
    }
}

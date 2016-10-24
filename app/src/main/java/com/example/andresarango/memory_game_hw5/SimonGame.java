package com.example.andresarango.memory_game_hw5;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static android.content.ContentValues.TAG;
import static com.example.andresarango.memory_game_hw5.SimonColors.BLUE;
import static com.example.andresarango.memory_game_hw5.SimonColors.GREEN;
import static com.example.andresarango.memory_game_hw5.SimonColors.RED;
import static com.example.andresarango.memory_game_hw5.SimonColors.YELLOW;


/**
 * Created by andresarango on 9/28/16.
 */

public class SimonGame implements SequenceGameLogic, Serializable {
    private List<SimonColors> gameMoveSequence = new ArrayList<>();
    private SimonColors currentMove;
    private int round = 1;
    private int score;
    private int mMoveNumber;

//    public SimonGame(List<SimonColors> gameMoveSequence){
//
//        this.gameMoveSequence.addAll(gameMoveSequence);
//
//    }

    @Override
    public SimonColors getRandomColor() {
        int randNumber = getRandomNumber(4,1);
        switch(randNumber){
            case 1:
                return RED;
            case 2:
                return BLUE;
            case 3:
                return GREEN;
            case 4:
                return YELLOW;
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

    public void setGameSequence(List<SimonColors> gameMoveSequence) {
         this.gameMoveSequence.addAll(gameMoveSequence);
    }
    
    public void setUserMove(SimonColors move) {
        currentMove = move;
    }
    public SimonColors getUserMove() {
        return currentMove;
    }

    public Set<String> convertToString(List<SimonColors> list){
        Set<String> output = new HashSet<>();
        for(SimonColors color:list){

            switch (color){

                case YELLOW:
                    output.add("yellow");
                    break;
                case RED:
                    output.add("red");
                    break;
                case BLUE:
                    output.add("blue");
                    break;
                case GREEN:
                    output.add("green");
                    break;

            }

        }
        for(String color:output){

            Log.d("MthodToCOnvert",color);

        }
     return output;

    }

    public void convertToSColors(Set<String> set){

        //List<SimonColors> output = new ArrayList<>();

        //gameMoveSequence.clear();



        for(String color:set){

            switch (color){

                case "yellow":
                    gameMoveSequence.add(YELLOW);
                    break;
                case "red":
                    gameMoveSequence.add(RED);
                    break;
                case "blue":
                    gameMoveSequence.add(BLUE);
                    break;
                case "green":
                    gameMoveSequence.add(GREEN);
                    break;

            }

        }
        Collections.reverse(gameMoveSequence);
        for(SimonColors color:gameMoveSequence){

            Log.d("FinalVersion",color.toString());

        }

    }

    public void setmMoveNumber(int mMoveNumber) {this.mMoveNumber = mMoveNumber;}

    @Override
    public void addRandomColorToGameSequence() {
        gameMoveSequence.add(getRandomColor());
        for(SimonColors color:gameMoveSequence){

            Log.d(TAG,color.toString());

        }
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


    public void setRound(int round){
       this.round=round;
    }
    public void setScore(int score){
        this.score=score;
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

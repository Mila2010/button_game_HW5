package com.example.andresarango.memory_game_hw5;

import java.util.List;

/**
 * Created by andresarango on 9/28/16.
 */

public interface SequenceGameLogic {
    public SimonColors getRandomColor();
    public int getRandomNumber(int max, int min);
    public void addRandomColorToGameSequence();
    public void setUserMove(SimonColors color);
    public boolean isUserMoveCorrect(SimonColors color, int moveNumber);
    public List<SimonColors> getGameSequence();
    public void addToUserMoveHistory();
    public SimonColors getCorrectMove(int moveNumber);
    public void addARound();
    public int getRound();
    public int getScore();
    public void increaseScore();
    public void resetGame();
    public String convertEnumColorToString(SimonColors color);
    public SimonColors convertStringToEnumColor(String move);
}
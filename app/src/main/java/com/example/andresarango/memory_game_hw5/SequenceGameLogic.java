package com.example.andresarango.memory_game_hw5;

import java.util.List;

/**
 * Created by andresarango on 9/28/16.
 */

/**/

public interface SequenceGameLogic {
    public SimonColors getRandomColor();
    public int getRandomNumber(int max, int min);
    public void addRandomColorToGameSequence();
    public boolean isUserMoveCorrect();
    public List<SimonColors> getGameSequence();
    public SimonColors getCorrectMove(int moveNumber);
    public void addARound();
    public int getRound();
    public int getScore();
    public int getmMoveNumber();
    public void setmMoveNumber(int mMoveNumber);
    public void increaseScore();
    public void resetGame();
    public int remainingNumberOfMoves();
    public void startNextRound();
}

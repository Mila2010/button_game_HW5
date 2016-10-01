package com.example.andresarango.memory_game_hw5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import static com.example.andresarango.memory_game_hw5.MainActivity.msNewGame;

/**
 * Created by andresarango on 9/30/16.
 */

public class LesButtons {
    private final TextView mSmallCircleText;
    private String LAG = "BUTTONS";
    final private Button mUpperLeftGreenBut;
    final private Button mUpperRightRedBut;
    final private Button mLowerLeftYellowBut;
    final private Button mLowerRightBlueBut;

    private int mSimulationIteration;
    private int mDifficulty;
    private int mMoveNumber;

    private Activity mOurActivity;
    private Intent mintentRound;
    private Intent mGameOverAct;

    private Handler mhandler;
    private SimonGame msNewGame;

    /*
    * I set all the buttons to my fields in the constructor
    * */

    public LesButtons(Activity currentActivity, SimonGame thisGame){
        mOurActivity = currentActivity;

        mintentRound = new Intent(mOurActivity,BetweenRoundsActivity.class);
        msNewGame = thisGame;
        mintentRound.putExtra("nextRound", msNewGame.getRound());

        mGameOverAct = new Intent(mOurActivity,GameOver.class);

        mDifficulty = mOurActivity.getIntent().getIntExtra("difficulty",1000);

        mSmallCircleText = (TextView) mOurActivity.findViewById(R.id.small_circle);
        mSmallCircleText.setText(mOurActivity.getString(R.string.round,msNewGame.getRound()));

        mUpperLeftGreenBut = (Button) mOurActivity.findViewById(R.id.upper_left);
        mUpperRightRedBut = (Button) mOurActivity.findViewById(R.id.upper_right);
        mLowerLeftYellowBut = (Button) mOurActivity.findViewById(R.id.lower_left);
        mLowerRightBlueBut = (Button) mOurActivity.findViewById(R.id.lower_right);

        mLowerLeftYellowBut.setOnClickListener(clickedButton);
        mLowerRightBlueBut.setOnClickListener(clickedButton);
        mUpperLeftGreenBut.setOnClickListener(clickedButton);
        mUpperRightRedBut.setOnClickListener(clickedButton);

        mhandler= new Handler();
    }


    /*
    *
    * When button is clicked, userMove is set in simongame, mMoveNumber is set in simongame,
    * buttonwasclicked is set in simongame to true and button is flashed.
    * if usermove is incorrect, game is reset, user goes to gameover activity and main activity is
    * destroyed
    *
    * If user moves have maxed out, next round is
    
    *
    * */

    private View.OnClickListener clickedButton = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            int unflashDelayTime = mDifficulty/5;
            switch (view.getId()){
                case R.id.upper_left:
                    msNewGame.setUserMove(SimonColors.GREEN);
                    msNewGame.setmMoveNumber(mMoveNumber);
                    flashButton(mUpperLeftGreenBut);
                    mhandler.postDelayed(delayUnflashButton(mUpperLeftGreenBut),unflashDelayTime);
                    break;
                case R.id.upper_right:
                    msNewGame.setUserMove(SimonColors.RED);
                    msNewGame.setmMoveNumber(mMoveNumber);
                    flashButton(mUpperRightRedBut);
                    mhandler.postDelayed(delayUnflashButton(mUpperRightRedBut),unflashDelayTime);
                    break;
                case R.id.lower_left:
                    msNewGame.setUserMove(SimonColors.YELLOW);
                    msNewGame.setmMoveNumber(mMoveNumber);
                    flashButton(mLowerLeftYellowBut);
                    mhandler.postDelayed(delayUnflashButton(mLowerLeftYellowBut),unflashDelayTime);
                    break;
                case R.id.lower_right:
                    msNewGame.setUserMove(SimonColors.BLUE);
                    msNewGame.setmMoveNumber(mMoveNumber);
                    flashButton(mLowerRightBlueBut);
                    mhandler.postDelayed(delayUnflashButton(mLowerRightBlueBut),unflashDelayTime);
                    break;
            }
            mMoveNumber += 1;

            if(!msNewGame.isUserMoveCorrect()){
                msNewGame.resetGame();
                mGameOverAct.putExtra("difficulty",mDifficulty);
                mOurActivity.startActivity(mGameOverAct);
                mOurActivity.finish();
            }
            if (msNewGame.remainingNumberOfMoves() == 0) {
                msNewGame.startNextRound();
                mMoveNumber = 0;
                mintentRound.putExtra("nextRound",msNewGame.getRound());
                mOurActivity.startActivity(mintentRound);
                mSmallCircleText.setText(mOurActivity.getString(R.string.round,msNewGame.getRound()));
            }
        }
    };

    /*This shows the button sequence user must match*/

    public void showButtonSequence(){
        setAllButtonsUnclickable();
        int time = 0;
        for (int i = 0; i < msNewGame.getGameSequence().size(); i++) {
            if(msNewGame.getRound() == 1){
                time += 2000;
                mhandler.postDelayed(flashButtonSequence(i),time);
                time += 60;
                mSimulationIteration = i;
                mhandler.postDelayed(unFlashButtonSequence(i),time);
            }else {
                time += mDifficulty;
                mhandler.postDelayed(flashButtonSequence(i), time);
                time += mDifficulty / 5;
                mSimulationIteration = i;
                mhandler.postDelayed(unFlashButtonSequence(i), time);
            }
        }
    }

    /*Allows button to temporarily stay "flashed" when put in handler.postdelayed*/

    private Runnable delayUnflashButton(final Button ourbut){
        return new Runnable() {
            @Override
            public void run() {
                switch(ourbut.getId()){
                    case R.id.upper_right:
                        unFlashButton(mUpperRightRedBut);
                        break;
                    case R.id.lower_right:
                        unFlashButton(mLowerRightBlueBut);
                        break;
                    case R.id.lower_left:
                        unFlashButton(mLowerLeftYellowBut);
                        break;
                    case R.id.upper_left:
                        unFlashButton(mUpperLeftGreenBut);
                }
            }
        };
    }


    /*method used specifically in showbutton sequence to unflash a button  according to sequence
    * iteration*/

    private Runnable unFlashButtonSequence(final int iteration){
        return new Runnable() {
            @Override
            public void run() {
                SimonColors color = msNewGame.getCorrectMove(iteration);
                int colothis;
                GradientDrawable unflash;
                switch(color){
                    case RED:
                        unFlashButton(mUpperRightRedBut);
                        break;
                    case BLUE:
                        unFlashButton(mLowerRightBlueBut);
                        break;
                    case YELLOW:
                        unFlashButton(mLowerLeftYellowBut);
                        break;
                    case GREEN:
                        unFlashButton(mUpperLeftGreenBut);
                }
                if(mSimulationIteration == msNewGame.getGameSequence().size() - 1){
                    setAllButtonsClickable();
                }
            }
        };
    }

    /*method used specifically in showbutton sequence to flash a button according to sequence
    * iteration*/

    private Runnable flashButtonSequence(final int iteration){
        return new Runnable() {
            @Override
            public void run() {
                SimonColors color = msNewGame.getCorrectMove(iteration);
                switch(color){
                    case RED:
                        flashButton(mUpperRightRedBut);
                        break;
                    case BLUE:
                        flashButton(mLowerRightBlueBut);
                        break;
                    case YELLOW:
                        flashButton(mLowerLeftYellowBut);
                        break;
                    case GREEN:
                        flashButton(mUpperLeftGreenBut);
                }
            }
        };
    }

    /*flash a button*/

    private void flashButton(final Button ourBut){
                int colothis;
                GradientDrawable flash;
                switch(ourBut.getId()){
                    case R.id.upper_right:
                        flash = (GradientDrawable) mUpperRightRedBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashred);
                        flash.setColor(colothis);
                        break;
                    case R.id.lower_right:
                        flash = (GradientDrawable) mLowerRightBlueBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashblue);
                        flash.setColor(colothis);
                        break;
                    case R.id.lower_left:
                        flash = (GradientDrawable) mLowerLeftYellowBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashyellow);
                        flash.setColor(colothis);
                        break;
                    case R.id.upper_left:
                        flash = (GradientDrawable) mUpperLeftGreenBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashgreen);
                        flash.setColor(colothis);
                }
    }


    /*unflash a button*/

    private void unFlashButton(final Button ourBut){
                int colothis;
                GradientDrawable unflash;
                switch(ourBut.getId()){
                        case R.id.upper_right:
                            unflash = (GradientDrawable) mUpperRightRedBut.getBackground();
                            colothis = ContextCompat.getColor(mOurActivity, R.color.red);
                            unflash.setColor(colothis);
                            break;
                        case R.id.lower_right:
                            unflash = (GradientDrawable) mLowerRightBlueBut.getBackground();
                            colothis = ContextCompat.getColor(mOurActivity, R.color.blue);
                            unflash.setColor(colothis);
                            break;
                        case R.id.lower_left:
                            unflash = (GradientDrawable) mLowerLeftYellowBut.getBackground();
                            colothis = ContextCompat.getColor(mOurActivity, R.color.yellow);
                            unflash.setColor(colothis);
                            break;
                        case R.id.upper_left:
                            unflash = (GradientDrawable) mUpperLeftGreenBut.getBackground();
                            colothis = ContextCompat.getColor(mOurActivity, R.color.green);
                            unflash.setColor(colothis);
                }
    }

    /*Sets all buttons clickable. Used in showSequence allows users to press all buttons while
    sequence is being played*/
    public void setAllButtonsClickable(){
        mUpperLeftGreenBut.setClickable(true);
        mUpperRightRedBut.setClickable(true);
        mLowerLeftYellowBut.setClickable(true);
        mLowerRightBlueBut.setClickable(true);
    }

    /*Sets all buttons unclickable. Used in showSequence to make sure users are not pressing buttons
    * while game is being shown*/
    public void setAllButtonsUnclickable(){
        mUpperLeftGreenBut.setClickable(false);
        mUpperRightRedBut.setClickable(false);
        mLowerLeftYellowBut.setClickable(false);
        mLowerRightBlueBut.setClickable(false);
    }
}

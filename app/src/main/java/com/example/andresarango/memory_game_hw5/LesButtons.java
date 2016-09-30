package com.example.andresarango.memory_game_hw5;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import static com.example.andresarango.memory_game_hw5.MainActivity.msNewGame;

/**
 * Created by andresarango on 9/30/16.
 */

public class LesButtons {
    private String LAG = "BUTTONS";
    final private Button mUpperLeftGreenBut;
    final private Button mUpperRightRedBut;
    final private Button mLowerLeftYellowBut;
    final private Button mLowerRightBlueBut;
    private int mSimulationIteration;
    private Activity mOurActivity;
    int mDifficulty = 1000;


    private Handler mhandler;
    public LesButtons(Activity currentActivity){
        mOurActivity = currentActivity;
        mDifficulty = mOurActivity.getIntent().getIntExtra("difficulty",1000);
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

    private View.OnClickListener clickedButton = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            int unflashDelayTime = mDifficulty;
            switch (view.getId()){
                case R.id.upper_left:
                    msNewGame.setUserMove(SimonColors.GREEN);
                    flashButton(mUpperLeftGreenBut);
                    mhandler.postDelayed(delayUnflashButton(mUpperLeftGreenBut),unflashDelayTime);
                    break;
                case R.id.upper_right:
                    msNewGame.setUserMove(SimonColors.RED);
                    flashButton(mUpperRightRedBut);
                    mhandler.postDelayed(delayUnflashButton(mUpperRightRedBut),unflashDelayTime);
                    break;
                case R.id.lower_left:
                    msNewGame.setUserMove(SimonColors.YELLOW);
                    flashButton(mLowerLeftYellowBut);
                    mhandler.postDelayed(delayUnflashButton(mLowerLeftYellowBut),unflashDelayTime);
                    break;
                case R.id.lower_right:
                    msNewGame.setUserMove(SimonColors.BLUE);
                    flashButton(mLowerRightBlueBut);
                    mhandler.postDelayed(delayUnflashButton(mLowerRightBlueBut),unflashDelayTime);
                    break;
            }
        }
    };


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
                if(mSimulationIteration == msNewGame.gameMoveSequence.size() - 1){
                    setAllButtonsClickable();
                }
            }
        };
    }

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

    private void flashButton(final Button ourBut){
                int colothis;
                GradientDrawable unflash;
                switch(ourBut.getId()){
                    case R.id.upper_right:
                        unflash = (GradientDrawable) mUpperRightRedBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashred);
                        unflash.setColor(colothis);
                        break;
                    case R.id.lower_right:
                        unflash = (GradientDrawable) mLowerRightBlueBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashblue);
                        unflash.setColor(colothis);
                        break;
                    case R.id.lower_left:
                        unflash = (GradientDrawable) mLowerLeftYellowBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashyellow);
                        unflash.setColor(colothis);
                        break;
                    case R.id.upper_left:
                        unflash = (GradientDrawable) mUpperLeftGreenBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashgreen);
                        unflash.setColor(colothis);
                }
    }


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

    public void showButtonSequence(){
        setAllButtonsUnclickable();
        int time = 0;
        for (int i = 0; i < msNewGame.gameMoveSequence.size(); i++) {
            time = mDifficulty + time;
            mhandler.postDelayed(flashButtonSequence(i),time);
            time = mDifficulty/5 + time;
            mSimulationIteration = i;
            mhandler.postDelayed(unFlashButtonSequence(i),time);
        }
    }

    public void setAllButtonsClickable(){
        mUpperLeftGreenBut.setClickable(false);
        mUpperRightRedBut.setClickable(false);
        mLowerLeftYellowBut.setClickable(false);
        mLowerRightBlueBut.setClickable(false);
    }

    public void setAllButtonsUnclickable(){
        mUpperLeftGreenBut.setClickable(true);
        mUpperRightRedBut.setClickable(true);
        mLowerLeftYellowBut.setClickable(true);
        mLowerRightBlueBut.setClickable(true);
    }
}

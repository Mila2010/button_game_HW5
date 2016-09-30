package com.example.andresarango.memory_game_hw5;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.andresarango.memory_game_hw5.MainActivity.msNewGame;

/**
 * Created by andresarango on 9/30/16.
 */

public class LesButtons {
    private String LAG = "BUTTONS";
    private Button mUpperLeftGreenBut;
    private Button mUpperRightRedBut;
    private Button mLowerLeftYellowBut;
    private Button mLowerRightBlueBut;
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
            switch (view.getId()){
                case R.id.upper_left:
                    msNewGame.setUserMove(SimonColors.GREEN);
                    break;
                case R.id.upper_right:
                    msNewGame.setUserMove(SimonColors.RED);
                    break;
                case R.id.lower_left:
                    msNewGame.setUserMove(SimonColors.YELLOW);
                    break;
                case R.id.lower_right:
                    msNewGame.setUserMove(SimonColors.BLUE);
                    break;
            }
        }
    };


    private Runnable unFlashButton(final int iteration){
        return new Runnable() {
            @Override
            public void run() {
                SimonColors color = msNewGame.getCorrectMove(iteration);
                int colothis;
                GradientDrawable unflash;
                switch(color){
                    case RED:
                        unflash = (GradientDrawable) mUpperRightRedBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.red);
                        unflash.setColor(colothis);
                        break;
                    case BLUE:
                        unflash = (GradientDrawable) mLowerRightBlueBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.blue);
                        unflash.setColor(colothis);
                        break;
                    case YELLOW:
                        unflash = (GradientDrawable) mLowerLeftYellowBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.yellow);
                        unflash.setColor(colothis);
                        break;
                    case GREEN:
                        unflash = (GradientDrawable) mUpperLeftGreenBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.green);
                        unflash.setColor(colothis);
                }
                if(mSimulationIteration == msNewGame.gameMoveSequence.size() - 1){
                    setAllButtonsClickable();
                }
            }
        };
    }

    private Runnable flashButton(final int iteration){
        return new Runnable() {
            @Override
            public void run() {
                SimonColors color = msNewGame.getCorrectMove(iteration);
                int colothis;
                GradientDrawable unflash;
                switch(color){
                    case RED:
                        unflash = (GradientDrawable) mUpperRightRedBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashred);
                        unflash.setColor(colothis);
                        break;
                    case BLUE:
                        unflash = (GradientDrawable) mLowerRightBlueBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashblue);
                        unflash.setColor(colothis);
                        break;
                    case YELLOW:
                        unflash = (GradientDrawable) mLowerLeftYellowBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashyellow);
                        unflash.setColor(colothis);
                        break;
                    case GREEN:
                        unflash = (GradientDrawable) mUpperLeftGreenBut.getBackground();
                        colothis = ContextCompat.getColor(mOurActivity, R.color.flashgreen);
                        unflash.setColor(colothis);
                }
            }
        };
    }

    public void showButtonSequence(){
        setAllButtonsUnclickable();
        int time = 0;
        for (int i = 0; i < msNewGame.gameMoveSequence.size(); i++) {
            Log.d(LAG, Integer.toString(i));
            time = mDifficulty + time;
            mhandler.postDelayed(flashButton(i),time);
            time = mDifficulty/5 + time;
            mSimulationIteration = i;
            mhandler.postDelayed(unFlashButton(i),time);
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

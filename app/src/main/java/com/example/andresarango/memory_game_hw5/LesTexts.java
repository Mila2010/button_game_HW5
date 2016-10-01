package com.example.andresarango.memory_game_hw5;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by andresarango on 9/30/16.
 */

/*
* class to hold all things done to textviews
* */

public class LesTexts {
    private final Activity mOurActivity;
    private final TextView mSmallCircleText;

    public LesTexts(Activity currentActivity){
        mOurActivity = currentActivity;
        mSmallCircleText = (TextView) mOurActivity.findViewById(R.id.small_circle);
    }
}

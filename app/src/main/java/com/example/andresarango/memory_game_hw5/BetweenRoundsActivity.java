package com.example.andresarango.memory_game_hw5;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BetweenRoundsActivity extends AppCompatActivity {

    private Intent mintent;
    private Intent mintentMain;
    private TextView mRoundCount;
    private TextView mRoundCountdown;
    private Handler mHandler;
    private int countdownTime = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_between_rounds);
        mintentMain = new Intent(BetweenRoundsActivity.this,MainActivity.class);
        mintent = getIntent();
        mRoundCount = (TextView) findViewById(R.id.round_count);
        mRoundCountdown = (TextView) findViewById(R.id.round_countdown);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRoundCount.setText(Integer.toString(mintent.getIntExtra("nextRound",0)));
        mHandler = new Handler();
        int time = 0;
        for (int i = 3; i > 0 ; i--) {
            time += countdownTime;
            mHandler.postDelayed(countdownText(i), time);
            if(i==1){
                time += countdownTime;
                mHandler.postDelayed(delayGoToMain(),time);
            }
        }
    }

    private Runnable countdownText(final int i){
        return new Runnable() {
            @Override
            public void run() {
                mRoundCountdown.setText(Integer.toString(i));
            }
        };
    }

    private Runnable delayGoToMain(){
        return new Runnable() {
            @Override
            public void run() {
                mintentMain.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(mintentMain);
                finish();
            }
        };
    };
}

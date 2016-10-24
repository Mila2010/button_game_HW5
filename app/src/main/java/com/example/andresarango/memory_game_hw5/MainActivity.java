package com.example.andresarango.memory_game_hw5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SWAG";
    private static final String ROUND= "ROUND";
    private static final String SCORE = "SCORE";
    private static final String GAMESEQUENCE = "GAMESEQUENCE";
    private List<String> generateSequence;
    private List<String> userSequence;
    public SimonGame msNewGame;
    private LesButtons magicButtons;
    private boolean flag=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        msNewGame = new SimonGame();

        if(savedInstanceState!=null){
            SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
            msNewGame.setRound(settings.getInt(ROUND,0));
            msNewGame.setScore(settings.getInt(SCORE,0));
            Set<String> set = new HashSet<>();
            set.addAll(settings.getStringSet(GAMESEQUENCE,null));
            for(String string:set){

                Log.d(TAG,string);

            }
            msNewGame.convertToSColors(settings.getStringSet(GAMESEQUENCE,null));

            flag=true;

        }
            msNewGame.addRandomColorToGameSequence();
            magicButtons = new LesButtons(MainActivity.this,msNewGame);






    }

    @Override
    protected void onResume() {
        super.onResume();
       if(!flag) {
           magicButtons.showButtonSequence();
       }


    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(ROUND,  msNewGame.getRound());
        editor.putInt(SCORE, msNewGame.getScore());
        Set<String> set = new HashSet<String>();
        set.addAll(msNewGame.convertToString(msNewGame.getGameSequence()));

        for(String string:set){

            Log.d(TAG,string);

        }

        editor.putStringSet(GAMESEQUENCE,set);
        editor.apply();
        editor.commit();

    }

}

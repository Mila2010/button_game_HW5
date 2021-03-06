package com.example.andresarango.memory_game_hw5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SWAG";
    private List<String> generateSequence;
    private List<String> userSequence;
    public SimonGame msNewGame;
    private LesButtons magicButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        msNewGame = new SimonGame();
        msNewGame.addRandomColorToGameSequence();
        magicButtons = new LesButtons(MainActivity.this,msNewGame);
    }

    @Override
    protected void onResume() {
        super.onResume();
        magicButtons.showButtonSequence();

    }

}

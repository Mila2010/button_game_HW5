package com.example.andresarango.memory_game_hw5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SWAG";
    private List<String> generateSequence;
    private List<String> userSequence;
    public static SimonGame msNewGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LesButtons magicButtons = new LesButtons(MainActivity.this);

        msNewGame = new SimonGame();
        msNewGame.addRandomColorToGameSequence();
        magicButtons.showButtonSequence();

    }
}

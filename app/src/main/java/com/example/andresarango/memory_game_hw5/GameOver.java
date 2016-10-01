package com.example.andresarango.memory_game_hw5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GameOver extends AppCompatActivity {
    /*
    * This is my suggested GameOver activity. Once again, if you can do something cool like put a
    * screen on top of the main activity, feel free.
    *
    * */

    private Button endGame;
    private Button newGame;
    private Intent mainAct;
    private Intent home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mainAct = new Intent(GameOver.this, MainActivity.class);
        home = new Intent(GameOver.this,HomeScreen.class);
        endGame = (Button) findViewById(R.id.new_game);
        newGame = (Button) findViewById(R.id.quit);
        endGame.setOnClickListener(clickedButton);
        newGame.setOnClickListener(clickedButton);
    }

    private View.OnClickListener clickedButton = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.new_game:
                   startActivity(mainAct);
                    finish();
                    break;
                case R.id.quit:
                    startActivity(home);
                    mainAct.putExtra("difficulty",getIntent().getIntExtra("difficulty",1000));
                    finish();
            }
        }
    };
}


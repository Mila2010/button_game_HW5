package com.example.andresarango.memory_game_hw5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    private Button mStart;
    private Button mEasy;
    private Button mMedium;
    private Button mHard;
    private Intent mintent;

    /*
    * sets Homescreen, has buttons to change "difficulty" or speed that sequence is flashed, not
    * very exciting stuff
    *
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mStart = (Button) findViewById(R.id.start);
        mEasy = (Button) findViewById(R.id.easy);
        mMedium = (Button) findViewById(R.id.medium);
        mHard = (Button) findViewById(R.id.hard);
        mintent = new Intent(HomeScreen.this,MainActivity.class);

        mStart.setOnClickListener(clickedButton);
        mEasy.setOnClickListener(clickedButton);
        mMedium.setOnClickListener(clickedButton);
        mHard.setOnClickListener(clickedButton);
    }

    /*
    * set game difficulty and start game.
    *
    * Feel free to revise the homescreen, just be sure to change the Button ID's in this class and
    * have the corresponding buttons in your new xml.
    * */

    private View.OnClickListener clickedButton = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.easy:
                    mintent.putExtra("difficulty",1000);
                    break;
                case R.id.medium:
                    mintent.putExtra("difficulty",850);
                    break;
                case R.id.hard:
                    mintent.putExtra("difficulty",700);
                    break;
                case R.id.start:
                    startActivity(mintent);
            }
        }
    };
}

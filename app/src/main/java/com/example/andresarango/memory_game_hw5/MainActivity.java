package com.example.andresarango.memory_game_hw5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mUpperLeftGreenBut;
    private Button mUpperRightRedBut;
    private Button mLowerLeftYellowBut;
    private Button mLowerRightBlueBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUpperLeftGreenBut = (Button) findViewById(R.id.upper_left);
        mUpperRightRedBut = (Button) findViewById(R.id.upper_right);
        mLowerLeftYellowBut = (Button) findViewById(R.id.lower_left);
        mLowerRightBlueBut = (Button) findViewById(R.id.lower_right);
        mLowerLeftYellowBut.setOnClickListener(clickedButton);
    }
    private View.OnClickListener clickedButton = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.upper_left:
                    break;
                case R.id.upper_right:
                    break;
                case R.id.lower_left:
                    break;
                case R.id.lower_right:
                    break;
            }
        }
    };

}

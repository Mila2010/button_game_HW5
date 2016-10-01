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
        setContentView(R.layout.main_activity);


        LesButtons magicButtons = new LesButtons(MainActivity.this);

        msNewGame = new SimonGame();


            msNewGame.addRandomColorToGameSequence();
            magicButtons.showButtonSequence();



    }

//        }
        /*
        *
        * The following code may not work for I have not implemented it however, it might work. If
        * not, think of something better, no hard feelings : D
        *
        * If you work with textviews programmitcally, please (eventually) place the code in
        * Lesbuttons.
        *
        * If you're looking for methods to use, I would suggest looking at th
        *
        *  The following code may require two seperate activities and, if switching between
         *  activities, may need to go in onResume() maybe? THIS SUGGESTION IS POSSIBLY WRONG.
        *
        *   Activity one displays a game over screen with a button that goes back to main activity
        *   Activity two displays the intermediary screen between rounds
        *
        * It would be cool if they were screens that popped up on top of the activity, if not, meh.
        *
        * P.S. the method names I referred to below are all pretty much created I think.
        *
        * while(true){
        * if(isRoundStarting){
        *   go to Activity two that
        *   ---Code to say new round is starting
        *   ---(MAYBE) countdown on screen 3 seconds
        *   ------set small circle text to new "Round\n"+roundint
        *   ---showMoveSequence
        * }else if(wasButtonClicked){
        *   if(!isUserMoveCorrect){
        *       stopGame, reset Simon, offer to start MainActivity in a new activity with big "game over" text.
        *       Destroy Main activity.
        *           Extra: Make game over text do something cool like flash multicolors
        *       }else if(remainingNumberOfMoves == 0){
        *           addRandomColorTo gamesequence
        *           startNextRound
        *       }else{
        *       display remaining number of moves
        *       display current score
        *       addMoveToUserMoveHistory (ArrayList in SimonGame)
        * }
        * */
}

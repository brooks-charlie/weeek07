package com.example.weeek07;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**QuizActivity.java - Activity that runs through the quiz
*@author Jayton
*@version [1.0]
*/
public class QuizActivity extends AppCompatActivity {


    private static final String TAG_NAME = "Quiz Deck Log";


    boolean firstLoad;

    //Widgets
    TextView cardText;
    TextView numberWrongTextView;
    TextView numberRightTextView;
    TextView numberMasteredTextView;
    TextView deckNameTextView;
    Button flipButton;
    Button rightButton;
    Button wrongButton;

    Deck deck;
    ArrayList<Card> quizCards;
    Card cardToQuiz;
    float wrongCount;
    float rightCount;
    int masteredCount;
    Toast masteredToast;
    Toast rightToast;
    Toast wrongToast;

    /**
    *Initaizle the quiz
    *Creates a quiz and initalizes user interface and binds our widgets to the inouts
    *@param savedInstanceState A variable of type Bundle
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        firstLoad = true;
        cardToQuiz = null;
        //hook up them widgets
        cardText = (TextView)findViewById(R.id.textView);
        flipButton = (Button)findViewById(R.id.flipButton);
        rightButton = (Button)findViewById(R.id.rightButton);
        wrongButton = (Button)findViewById(R.id.wrongButton);
        numberWrongTextView = (TextView)findViewById(R.id.numberWrongTextView);
        numberRightTextView = (TextView)findViewById(R.id.numberRightTextView);
        numberMasteredTextView = (TextView)findViewById(R.id.numberMasteredTextView);
        deckNameTextView = (TextView)findViewById(R.id.deckNameTextView);

        masteredToast = Toast.makeText(getApplicationContext(), "Mastered!", Toast.LENGTH_SHORT);
        rightToast = Toast.makeText(getApplicationContext(), "Nice!", Toast.LENGTH_SHORT);
        wrongToast = Toast.makeText(getApplicationContext(), "Bummer...", Toast.LENGTH_SHORT);

        Bundle extras = getIntent().getExtras();
        deck = (Deck) extras.get("deckToQuiz");
        deckNameTextView.setText(deck.getDeckName());
        quizCards = new ArrayList<>(deck.cards);

        wrongCount = 0;
        rightCount = 0;
        masteredCount = 0;


        makePrompt();

    }

    public void makePrompt() {
        if(quizCards.isEmpty()){
            finishQuiz();
            return;
        }
        //Get the card
        if (firstLoad) {
            cardToQuiz = quizCards.get(new Random().nextInt(quizCards.size()));
        }
        firstLoad = true;
        //Set text to prompt and counters.
        cardText.setText(cardToQuiz.getPrompt());
        numberRightTextView.setText("Right: " + String.format("%.0f", rightCount));
        numberWrongTextView.setText("Wrong: " + String.format("%.0f", wrongCount));
        numberMasteredTextView.setText("Mastered: " + masteredCount);

        //Set up flip button/hide other buttons
        flipButton.setText("Flip it!");
        flipButton.setVisibility(View.VISIBLE);
        wrongButton.setVisibility(View.GONE);
        rightButton.setVisibility(View.GONE);

        //log quizCards for debugging
        logQuizCards();

    }

    /**
    *shows answer
    *Reveals answer side of card
    *@param v A variable of type View
    */
    public void showAnswer(View v) {
        //Set text to the answer
        cardText.setText(cardToQuiz.getAnswer());

        //Hide flip button/show right button and wrong button
        flipButton.setVisibility(View.GONE);
        wrongButton.setVisibility(View.VISIBLE);
        rightButton.setVisibility(View.VISIBLE);
    }
    /**
    *WrongClicked
    *Allows use to record if then gave the wrong answer.
    **@param v A variable of type View
    */
    public void wrongClicked(View v) {
        wrongToast.show();
        cardToQuiz.gotWrong();
        wrongCount++;
        makePrompt();
    }
    /**
    *RightClicked
    *Allows use to record if then gave the right answer.
    *@param v A variable of type View
    */
    public void rightClicked(View v) {

        cardToQuiz.gotRight();
        if (cardToQuiz.isMastered()){
            quizCards.remove(cardToQuiz);
            masteredToast.show();
            masteredCount++;
        } else {
            rightToast.show();
        }
        rightCount++;
        makePrompt();
    }

    public void finishQuiz() {
        flipButton.setVisibility(View.GONE);
        wrongButton.setVisibility(View.GONE);
        rightButton.setVisibility(View.GONE);
        float userScore = (rightCount / (rightCount + wrongCount)) * 100;

        cardText.setText("Nice Job! Your score was " + String.format("%.0f", userScore) +"%");

    }
    //This function logs each card in quizCards. Debugging purposes only. May be removed later.
    public void logQuizCards(){
        for (int i = 0; i < quizCards.size(); i++) {
            Log.d(TAG_NAME, "Prompt:" + quizCards.get(i).getPrompt() + " Answer:" + quizCards.get(i).getAnswer() +
            " score:" + quizCards.get(i).getScore());
        }

    }
    @Override
    public void onStop(){
        super.onStop();

        Log.d("onStop occured", "stopped");



    }
}


































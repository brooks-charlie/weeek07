package com.example.weeek07;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

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
    int wrongCount;
    int rightCount;
    int masteredCount;
    Toast masteredToast;
    Toast rightToast;
    Toast wrongToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

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
        cardToQuiz = quizCards.get(new Random().nextInt(quizCards.size()));

        //Set text to prompt and counters.
        cardText.setText(cardToQuiz.getPrompt());
        numberRightTextView.setText("Right: " + rightCount);
        numberWrongTextView.setText("Wrong: " + wrongCount);
        numberMasteredTextView.setText("Mastered: " + masteredCount);

        //Set up flip button/hide other buttons
        flipButton.setText("Flip it!");
        flipButton.setVisibility(View.VISIBLE);
        wrongButton.setVisibility(View.GONE);
        rightButton.setVisibility(View.GONE);

        //log quizCards for debugging
        logQuizCards();

    }

    public void showAnswer(View v) {
        //Set text to the answer
        cardText.setText(cardToQuiz.getAnswer());

        //Hide flip button/show right button and wrong button
        flipButton.setVisibility(View.GONE);
        wrongButton.setVisibility(View.VISIBLE);
        rightButton.setVisibility(View.VISIBLE);
    }

    public void wrongClicked(View v) {
        wrongToast.show();
        cardToQuiz.gotWrong();
        wrongCount++;
        makePrompt();
    }

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
        float userScore = rightCount / (rightCount + wrongCount);
        cardText.setText("Nice Job! Your score was " + userScore);

    }
    //This function logs each card in quizCards. Debugging purposes only. May be removed later.
    public void logQuizCards(){
        for (int i = 0; i < quizCards.size(); i++) {
            Log.d("Quiz card Log", "Prompt:" + quizCards.get(i).getPrompt() + " Answer:" + quizCards.get(i).getAnswer() +
            " score:" + quizCards.get(i).getScore());
        }

    }
}

































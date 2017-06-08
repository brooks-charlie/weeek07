package com.example.weeek07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void testDrive(View view) {
        Deck testDeck = new Deck("Basic math");
        testDeck.addCard("2+2", "4");
        testDeck.addCard("3+3", "6");
        testDeck.addCard("3+1", "4");
        testDeck.logDeck();
        Intent i = new Intent(getApplicationContext(), QuizActivity.class);
        i.putExtra("deckToQuiz", testDeck);
        startActivity(i);
    }


}

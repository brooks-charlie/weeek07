package com.example.weeek07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_NAME = "DECK LOAD";


    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create user. We will use a hardcoded user for now
        //  Later this will be pulled from preferences/database
        user = new User("Debuggy mcDebugface", "debugger534545352@gmail.com");

    }

    public void createDeck(View view){
        Intent i = new Intent(getApplicationContext(), CreateDeckActivity.class);
        i.putExtra("user", user);
        startActivity(i);
    }

    // This is for debugging only. Will be removed.
    public void testDrive(View view) {
        Deck testDeck = new Deck("Basic math");
        testDeck.addCard("2+2", "4");
        testDeck.addCard("3+3", "6");
        testDeck.addCard("3+1", "4");
        if (testDeck == null) {
            Log.wtf( TAG_NAME, "Deck Load Failed");
        } else {
            Log.d(TAG_NAME, "Deck loaded");
        }
        testDeck.logDeck();
        Intent i = new Intent(getApplicationContext(), QuizActivity.class);
        i.putExtra("deckToQuiz", testDeck);
        startActivity(i);
    }

    public void loginClicked(View view) {
        Intent i = new Intent(getApplicationContext(), GoogleSignInActivity.class);
        startActivity(i);
    }

    public void loadDecksClicked(View view) {
        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(i);
    }
}

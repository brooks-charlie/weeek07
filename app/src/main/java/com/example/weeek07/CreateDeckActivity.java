package com.example.weeek07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class CreateDeckActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    TextView userNameTextView;
    EditText deckNameText;
    EditText promptText;
    EditText answerText;
    Deck deck = null;
    Toast addCardErrorToast;
    Toast addCardSuccessToast;
    FirebaseUser user;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userNameTextView = (TextView)findViewById(R.id.userNameTextView);
        deckNameText = (EditText)findViewById(R.id.deckNameEditText);
        promptText = (EditText)findViewById(R.id.promptEditText);
        answerText = (EditText)findViewById(R.id.answerEditText);

        deck = new Deck();
        addCardErrorToast = Toast.makeText(getApplicationContext(), "ERROR: Add text to both Prompt and Answer Fields", Toast.LENGTH_LONG);
        addCardSuccessToast = Toast.makeText(getApplicationContext(), "Card Added!", Toast.LENGTH_LONG);
        userNameTextView.setText(user.getDisplayName());

    }

    public void addCardClicked(View v) {
        if(promptText.getText().toString().isEmpty() || answerText.getText().toString().isEmpty()) {
            addCardErrorToast.show();
            return;
        }
        //add fields to a card. add it to deck.
        deck.addCard(promptText.getText().toString(), answerText.getText().toString());
        addCardSuccessToast.show();

        //Reset textEdit widgets
        promptText.setText(null);
        answerText.setText(null);


        deck.logDeck();
    }

    public void doneClicked(View v) {
        deck.setDeckName(deckNameText.getText().toString());
        saveDeck();
        Log.d("doneClicked-CLICKED", "deck finalized");
        deck.logDeck();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);


    }

    //Load it to shared prefs (DATABASE IN FUTURE)
    private void saveDeck(){
        DatabaseReference newDeckLocation =
                mDatabase.child("users").child(user.getUid()).child("deckList").push();
        newDeckLocation.setValue(deck);
    }
}

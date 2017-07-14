package com.example.weeek07;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManageDeck extends AppCompatActivity {

    TextView deckNameView;
    TextView deckIdView;
    TextView promptTv;
    TextView answerTv;
    TextView emptyDeckMessage;

    //copy of deck from firebase
    Deck deck;

    //location of deck in json tree in firebase
    String deckId;

    //editText widgets representing card's answer and prompt
    EditText promptEditText;
    EditText answerEditText;

    //used to iterate through deck
    int deckIndex;


    //navigation buttons to move through deck
    ImageButton backButton;
    ImageButton forwardButton;

    //More buttons
    Button saveCardButton;
    Button deleteCardButton;


    //Toasts to notify
    Toast cardSavedToast;
    Toast cardDeletedToast;
    Toast deckDeletedToast;

    //firebase references
    FirebaseAuth mAuth;
    FirebaseUser fbUser;
    DatabaseReference mDatabaseRefToDeck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_deck);

        Bundle extras = getIntent().getExtras();
        deck = (Deck) extras.get("deckToQuiz");
        deckId = (String) extras.get("deckId");

        //Get firebase refrences
        mAuth = FirebaseAuth.getInstance();
        fbUser = mAuth.getCurrentUser();
        mDatabaseRefToDeck =  FirebaseDatabase.getInstance().getReference().child("users").child(fbUser.getUid())
                .child("deckList").child(deckId);


        //connect widgets
        deckNameView = (TextView)findViewById(R.id.textView3);
        deckIdView = (TextView)findViewById(R.id.textView4);
        promptEditText = (EditText)findViewById(R.id.editText);
        answerEditText = (EditText)findViewById(R.id.editText2);
        backButton = (ImageButton)findViewById(R.id.imageButton);
        forwardButton = (ImageButton)findViewById(R.id.imageButton2);
        saveCardButton = (Button)findViewById(R.id.button6);
        deleteCardButton = (Button)findViewById(R.id.button7);
        promptTv = (TextView)findViewById(R.id.textView5);
        answerTv = (TextView)findViewById(R.id.textView6);
        emptyDeckMessage = (TextView)findViewById(R.id.textView7);

        //Create toasts
        cardSavedToast = Toast.makeText(getApplicationContext(), "Card Saved!", Toast.LENGTH_SHORT);
        cardDeletedToast = Toast.makeText(getApplicationContext(), "Card Deleted!", Toast.LENGTH_SHORT);
        deckDeletedToast = Toast.makeText(getApplicationContext(), "Deck Deleted!", Toast.LENGTH_SHORT);

        emptyDeckMessage.setVisibility(View.INVISIBLE);
        deckNameView.setText(deck.getDeckName());
        deckIdView.setText(deckId);
        deckIndex = 0;
        updateUI();
    }

    public void saveCardClicked(View v){
        Card card = new Card(promptEditText.getText().toString(), answerEditText.getText().toString());
        deck.cards.set(deckIndex, card);
        cardSavedToast.show();
        deck.logDeck();
    }

    public void deleteCardClicked(View v) {
        deck.cards.remove(deckIndex);

        //if the index is at the end at removal push it left
        if (deckIndex == deck.cards.size()) {
            deckIndex = deckIndex - 1;
        }

        deck.logDeck();
        cardDeletedToast.show();
        updateUI();
    }

    //This saves a new deck to the location of the previous deck on Firebase.
    public void saveDeck(View v){
        mDatabaseRefToDeck.setValue(deck);

        //Go back to MainActivity
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void updateUI(){
        //get deck size.
        int deckMaxCount = deck.cards.size();

        //If there are no more cards change UI accordingly
        if(deck.cards.isEmpty()) {
            //hide buttons except for addCard and deleteDeck and add a message.
            promptTv.setVisibility(View.INVISIBLE);
            answerTv.setVisibility(View.INVISIBLE);
            promptEditText.setVisibility(View.INVISIBLE);
            answerEditText.setVisibility(View.INVISIBLE);
            saveCardButton.setVisibility(View.INVISIBLE);
            deleteCardButton.setVisibility(View.INVISIBLE);

            //Make empty deck message visible
            emptyDeckMessage.setVisibility(View.VISIBLE);

            //Show an empty count
            deckIdView.setText("Card " + (deckIndex + 1) + " out of " + deckMaxCount);
            return;
        }

        //set editText widgets to the card's value.
        deckIdView.setText("Card " + (deckIndex + 1) + " out of " + deckMaxCount);
        promptEditText.setText(deck.cards.get(deckIndex).getPrompt());
        answerEditText.setText(deck.cards.get(deckIndex).getAnswer());

        // if first card, hide left arrow.
        if(deckIndex == 0){
            backButton.setVisibility(View.INVISIBLE);
        } else {
            backButton.setVisibility(View.VISIBLE);
        }

        // if last card, hide right arrow.
        if(deckIndex == deckMaxCount - 1) {
            forwardButton.setVisibility(View.INVISIBLE);
        } else {
            forwardButton.setVisibility(View.VISIBLE);
        }
    }

    //Move to next card
    public void backButtonClicked(View v) {
        deckIndex = deckIndex - 1;
        updateUI();
    }

    //Move to previous card
    public void forwardButtonSet(View v) {
        deckIndex = deckIndex + 1;
        updateUI();
    }

    public void addCard(View v) {
        //make everything visible if it isnt
        promptTv.setVisibility(View.VISIBLE);
        answerTv.setVisibility(View.VISIBLE);
        promptEditText.setVisibility(View.VISIBLE);
        answerEditText.setVisibility(View.VISIBLE);
        saveCardButton.setVisibility(View.VISIBLE);
        deleteCardButton.setVisibility(View.VISIBLE);

        //Make empty deck message invisible if it isnt
        emptyDeckMessage.setVisibility(View.INVISIBLE);

        Card card = new Card("Write a prompt", "Write an answer");
        deck.cards.add(card);
        deckIndex = deck.cards.indexOf(card);
        updateUI();
    }

    public void deleteDeckClicked(View v) {
        mDatabaseRefToDeck.removeValue();
        deckDeletedToast.show();

        //Go back to MainActivity
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

}











































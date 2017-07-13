package com.example.weeek07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ManageDeck extends AppCompatActivity {
    TextView deckNameView;
    TextView deckIdView;
    Deck deck;
    String deckId;
    EditText promptEditText;
    EditText answerEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_deck);

        deckNameView = (TextView)findViewById(R.id.textView3);
        deckIdView = (TextView)findViewById(R.id.textView4);
        promptEditText = (EditText)findViewById(R.id.editText);
        answerEditText = (EditText)findViewById(R.id.editText2);

        Bundle extras = getIntent().getExtras();
        deck = (Deck) extras.get("deckToQuiz");
        deckId = (String) extras.get("deckId");

        deckNameView.setText(deck.getDeckName());
        deckIdView.setText(deckId);
        promptEditText.setText(deck.cards.get(0).getPrompt());
        answerEditText.setText(deck.cards.get(0).getAnswer());

    }

    public void saveCardClicked(View v){
        Card card = new Card(promptEditText.getText().toString(), answerEditText.getText().toString());
        

    }
}


package com.example.weeek07;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    FirebaseUser fbUser;
    User user;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    DatabaseReference userDBReference;
    Class classDestination;
    HashMap deckList;
    LinearLayout ll;

    //widgets for when user has no decks
    Button createDeckButton;
    TextView emptyDeckMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        classDestination = (Class) extras.get("classDestination");

        //Connect widgets
        createDeckButton = (Button)findViewById(R.id.button13);
        emptyDeckMessage = (TextView)findViewById(R.id.textView8);
        ll = (LinearLayout) findViewById(R.id.linearLayout);

        //Hide widgets only to be show when user has no decks
        emptyDeckMessage.setVisibility(View.INVISIBLE);
        createDeckButton.setVisibility(View.INVISIBLE);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        fbUser = mAuth.getCurrentUser();
        Log.d("Create deck on create", fbUser.getUid());
        userDBReference = FirebaseDatabase.getInstance().getReference().child("users").child(fbUser.getUid());

        ValueEventListener userLister = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                // LOG THE DECKS WITH MAP ITERATOR
                deckList = (HashMap) user.deckList;
                if (deckList == null){
                    //Hide scrollView and Show TV and Button about not having decks
                    createDeckButton.setVisibility(View.VISIBLE);
                    emptyDeckMessage.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.INVISIBLE);
                }
                else {
                    Iterator it = deckList.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        Deck deck = (Deck) pair.getValue();
                        deck.logDeck();
                        Button btn = new Button(getApplicationContext());
                        btn.setOnClickListener(onClickListener);
                        btn.setText(deck.getDeckName());
                        ll.addView(btn);
                        btn.setTag(pair.getKey());

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        userDBReference.addValueEventListener(userLister);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String deckTag = (String)v.getTag();
            Deck deck = (Deck) deckList.get(deckTag);
            Log.d("DECK CLICKED", "CLICKED");
            Log.d("DECK CLICKED", deck.getDeckName());
            Intent i = new Intent(getApplicationContext(), classDestination);
            i.putExtra("deckToQuiz", deck);
            i.putExtra("deckId", deckTag);
            startActivity(i);
        }
    };

    public void createDeckButtonClick(View v){
        Intent i = new Intent(getApplicationContext(), CreateDeckActivity.class);
        startActivity(i);
    }
}

package com.example.weeek07;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jayton on 6/6/2017.
 */

public class Deck implements Serializable{

    private String deckName;

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    Deck(String name) {
        this.deckName = name;
    }
    ArrayList<Card> cards = new ArrayList<>();

    public void addCard(String prompt, String answer){
        Card card = new Card(prompt, answer);
        cards.add(card);
    }

    public void logDeck() {
        for(int i = 0; i < cards.size(); i++) {
            Log.d("Deck Log", "Prompt:" + cards.get(i).getPrompt() + " Answer:" + cards.get(i).getAnswer());
        }
    }


}

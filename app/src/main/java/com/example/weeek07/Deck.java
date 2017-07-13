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
/**Deck.java - deck data structure to hold our cards.
*@author Jayton
*@version 1.0
*/



public class Deck implements Serializable{

    private static String TAG_NAME = "Deck Log";

    private String deckName;

    /**
    *Get deck name
    *Method is used when the Decks naem is needed to be retrieved.
    *@return An [Data Type] data type
    */
    public String getDeckName() {
        return deckName;
    }

    /**
    *Sets deck name
    *Method is called when deck name needs to be changed.
    *@param deckName A variable of type String
    */
    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    Deck(String name) {
        this.deckName = name;
    }
    Deck(){
        deckName = null;
    }
    ArrayList<Card> cards = new ArrayList<>();

    /**
    *creates a card
    *Creates a new card for the currrent deck.
    *@param prompt A variable of type String
    *@param answer A variable of type String
    */
    public void addCard(String prompt, String answer){
        Card card = new Card(prompt, answer);
        cards.add(card);
    }

    public void logDeck() {
        for(int i = 0; i < cards.size(); i++) {
            Log.d(TAG_NAME, "Prompt:" + cards.get(i).getPrompt() + " Answer:" + cards.get(i).getAnswer());
        }
    }


}

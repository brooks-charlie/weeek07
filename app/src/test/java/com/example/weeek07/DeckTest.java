package com.example.weeek07;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jayton on 6/7/2017.
 */

public class DeckTest {
    Deck testDeck = new Deck("Basic math");
    @Test
    public void deck_addCard() throws Exception {
        testDeck.addCard("2+2", "4");
        assertEquals(testDeck.cards.get(0).getPrompt(), "2+2");
        assertEquals(testDeck.cards.get(0).getAnswer(), "4");
    }
    public void deck_getDeckName() throws Exception {
        assertEquals(testDeck.getDeckName(), "Basic math");

    }

}

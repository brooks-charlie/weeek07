package com.example.weeek07;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jayton on 6/7/2017.
 */

public class DeckTest {

    @Test
    public void deckCreates() throws Exception {
        Deck testDeck = new Deck("Basic math");
        testDeck.addCard("2+2", "4");
        assertEquals(testDeck.cards.get(0).getPrompt(), "2+2");
    }
}

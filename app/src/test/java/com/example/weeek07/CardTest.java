package com.example.weeek07;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by charliebrooks on 7/1/17.
 */

public class CardTest {
    private static String TAG = "CardTest";
    Card c = new Card("First Pres", "GW");
    @Test
    public void gotRight_returns5() throws Exception {
        c.gotRight();
        c.gotRight();
        c.gotRight();
        c.gotRight();
        c.gotRight();
        //score should equal 5 now.
        assertEquals(c.getScore(), 5);
    }

    @Test
    public void gotWrong_returnsNegative1() throws Exception {

        c.gotWrong();
        //score should equal -1 now.
        assertEquals(c.getScore(), -1);
    }
    @Test
    public void isMastered_ReturnsTrue() throws Exception {

        gotRight_returns5();
        //isMastered should equal true
        assertEquals(c.isMastered(), true);

    }
    @Test
    public void isMastered_ReturnsFalse() throws Exception {
        c.gotWrong();
        assertEquals(c.isMastered(), false);
    }


}

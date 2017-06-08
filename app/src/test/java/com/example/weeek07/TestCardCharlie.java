package com.example.weeek07;

import org.junit.Test;

//import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by charliebrooks on 6/7/17.
 */

public class TestCardCharlie {
    @Test
    public void testcalcScore() throws Exception {
        //assertEquals(4, 2 + 2);
        CardCharlie c = new CardCharlie("First Pres", "GW");
        c.calcScore(1);
        assertThat(c.calcScore(1), is(1 * 2));
        assertThat(c.getAnswer(),is("GW"));
        assertThat(c.getPrompt(),is("First Pres"));
    }
}

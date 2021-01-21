package com.galvanize;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CallingCardTest {
    @Test
    void itGetsRemainingMinutes() {
        CallingCard card = new CallingCard(20); // this card costs 20 cents / minute to use
        int minutes = card.getRemainingMinutes();
        assertEquals(0, minutes);
    }

    @Test
    void itAddsDollars(){
        CallingCard card = new CallingCard(20); // this card costs 20 cents / minute to use
        card.addDollars(1);           // add $1 (100 cents).  100 cents / 20 cents/minute = 5 minutes added
        int minutes = card.getRemainingMinutes();   // returns 5  (5 minutes left)
        assertEquals(5, minutes);
    }

    @Test
    void itUsesMinutes(){
        CallingCard card = new CallingCard(20); // this card costs 20 cents / minute to use
        card.addDollars(1);           // add $1 (100 cents).  100 cents / 20 cents/minute = 5 minutes added
        card.useMinutes(3);
        int minutes = card.getRemainingMinutes();   // return 2
        assertEquals(2, minutes);
    }

    @Test
    void itHasMinimumOfZeroMinutes(){
        CallingCard card = new CallingCard(5); // this card costs 5 cents / minute to use
        card.addDollars(2);           // 200 cents / 5 cents/minute = 40 minutes added
        card.useMinutes(43);
        int minutes = card.getRemainingMinutes();   // return 0
        assertEquals(0, minutes);
    }

    @Test
    void itIgnoresDecimalPlacesForUnevenNumberOfMinutes(){
        CallingCard card = new CallingCard(17);
        card.addDollars(3);         // 300 cents / 17 = 17.647... = 17 minutes added
        int minutes = card.getRemainingMinutes(); // => returns 17
        assertEquals(17, minutes);
    }

}

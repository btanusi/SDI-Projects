package com.galvanize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;


public class CellPhoneTest {
    @Test
    void itChecksIfTalking() {
        CallingCard card = new CallingCard(10);
        card.addDollars(1); // add 100 cents @ 10 cents/minute = 10 minutes added
        CellPhone phone = new CellPhone(card);
        boolean isTalking = phone.isTalking();  // => returns false
        assertEquals(false, isTalking);
    }

    @Test
    void itStartsCalling(){
        CallingCard card = new CallingCard(10);
        card.addDollars(1); // add 100 cents @ 10 cents/minute = 10 minutes added
        CellPhone phone = new CellPhone(card);
        phone.call("555-1212");
        boolean isTalking = phone.isTalking();  // => returns true
        assertEquals(true, isTalking);
    }

    @Test
    void itEndsCall(){
        CallingCard card = new CallingCard(10);
        card.addDollars(1); // add 100 cents @ 10 cents/minute = 10 minutes added
        CellPhone phone = new CellPhone(card);
        phone.call("555-1212");
        phone.endCall();
        boolean isTalking = phone.isTalking();  // => returns true
        assertEquals(false, isTalking);
    }

    @Test
    void itDecreasesCardMinutesOnTick(){
        CallingCard card = new CallingCard(10);
        card.addDollars(1); // add 100 cents @ 10 cents/minute = 10 minutes added
        CellPhone phone = new CellPhone(card);
        phone.call("555-1111");
        phone.tick();       // simulate a minute going by
        int remainingMinutes = card.getRemainingMinutes();
        assertEquals(9,remainingMinutes);
    }

    @Test
    void itOnlyTicksDuringCall(){
        CallingCard card = new CallingCard(10);
        card.addDollars(1); // add 100 cents @ 10 cents/minute = 10 minutes added
        CellPhone phone = new CellPhone(card);
        phone.tick();
        int remainingMinutes = card.getRemainingMinutes();
        assertEquals(10,remainingMinutes);
    }

    @Test
    void itShowsCallHistoryForOneCall(){
        CallingCard card = new CallingCard(10);
        card.addDollars(1); // add 100 cents @ 10 cents/minute = 10 minutes added
        CellPhone phone = new CellPhone(card);
        phone.call("555-1212");
        phone.tick();       // simulate a minute going by
        phone.endCall();
        String history = phone.getHistory(); // => returns "555-1212 (1 minute)"
        assertEquals("555-1212 (1 minute)", history);
    }

    @Test
    void itPluralizesMultipleMinutes(){
        CallingCard card = new CallingCard(10);
        card.addDollars(1); // add 100 cents @ 10 cents/minute = 10 minutes added
        CellPhone phone = new CellPhone(card);
        phone.call("555-1212");
        phone.tick();       // simulate a minute going by
        phone.tick();
        phone.endCall();
        String history = phone.getHistory(); // => returns "555-1212 (1 minute)"
        assertEquals("555-1212 (2 minutes)", history);
    }

    @Test
    void itShowsCallHistoryForMultipleCalls(){
        CallingCard card = new CallingCard(10);
        card.addDollars(1); // add 100 cents @ 10 cents/minute = 10 minutes added
        CellPhone phone = new CellPhone(card);
        phone.call("555-1111");
        phone.tick();       // simulate a minute going by
        phone.endCall();
        phone.call("555-2222");
        phone.tick();       // simulate a minute going by
        phone.tick();       // simulate a minute going by
        phone.endCall();
        String history = phone.getHistory(); // => returns "555-1111 (1 minute), 555-2222 (2 minutes)"
        assertEquals("555-1111 (1 minute), 555-2222 (2 minutes)", history);
    }

    @Test
    void itEndsCallIfNoMinutesLeft(){
        CallingCard card = new CallingCard(20);
        card.addDollars(1); // add 100 cents @ 20 cents/minute = 5 minutes added
        CellPhone phone = new CellPhone(card);
        phone.call("555-1111");
        phone.tick();       // 1 minute elapsed
        phone.tick();       // 2 minutes elapsed
        phone.endCall();
        phone.call("555-3333");
        phone.tick();       // 3 minutes elapsed
        phone.tick();       // 4 minutes elapsed
        phone.tick();       // this is the end of the 5th minute, so the call is ended
        String history = phone.getHistory(); // => returns "555-1111 (2 minutes), 555-3333 (cut of at 3 minutes)"
        assertEquals("555-1111 (2 minutes), 555-3333 (cut off at 3 minutes)", history);
    }
}
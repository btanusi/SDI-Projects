package com.galvanize;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LightSaberTest {

    @Test
    void itGetsCharge() {
        LightSaber ls = new LightSaber(1);
        float charge = ls.getCharge();
        assertEquals(100.0f, charge);
    }

    @Test
    void itSetsCharge(){
        LightSaber ls = new LightSaber(1);
        ls.setCharge(50.0f);
        float charge = ls.getCharge();
        assertEquals(50.0f, charge);
    }

    @Test
    void itGetsColor(){
        LightSaber ls = new LightSaber(1);
        String color = ls.getColor();
        assertEquals("green", color);
    }

    @Test
    void itSetsColor(){
        LightSaber ls = new LightSaber(1);
        ls.setColor("red");
        String color = ls.getColor();
        assertEquals("red", color);
    }

    @Test
    void itGetsJediSerialNumber(){
        long expectedSerialNumber = 54321;
        LightSaber ls = new LightSaber(expectedSerialNumber);
        long serialNumber = ls.getJediSerialNumber();
        assertEquals(expectedSerialNumber, serialNumber);
    }

    @Test
    void itGetsMinutesRemaining(){
        LightSaber ls = new LightSaber(1);
        float minutes = ls.getRemainingMinutes();
        assertEquals( 300, minutes);
    }

    @Test
    void itUsesPower(){
        float efficiency = 10.0f;
        float minutesUsed = 60.0f;
        float expectedRemainingCharge = 100.0f - (efficiency / 60.0f) * minutesUsed;
        float expectedMinutesRemaining = (expectedRemainingCharge/efficiency) * 30;
        LightSaber ls = new LightSaber(1);
        ls.use(minutesUsed);
        float minutesRemaining = ls.getRemainingMinutes();
        assertEquals(expectedMinutesRemaining, minutesRemaining);
    }

    @Test
    void itRecharges(){
        LightSaber ls = new LightSaber(1);
        ls.setCharge(50.0f);
        float charge = ls.getCharge();
        assertEquals(50.0f, charge);
        ls.recharge();
        charge = ls.getCharge();
        assertEquals(100.0f, charge);
    }
}
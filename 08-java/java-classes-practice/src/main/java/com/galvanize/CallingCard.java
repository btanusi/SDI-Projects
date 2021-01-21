package com.galvanize;

public class CallingCard {
    private int centsPerMinute;
    private int balance = 0;
    private int minutes = 0;

    public CallingCard(int centsPerMinute){
        this.centsPerMinute = centsPerMinute;
    }

    public void addDollars(int dollars){
        int cents = dollars * 100;
        balance += cents;
        minutes += cents / centsPerMinute;
    }

    public int getRemainingMinutes(){
        return minutes;
    }

    public void useMinutes(int minutesUsed){
        minutes -= minutesUsed;
        if(minutes < 0){
            minutes = 0;
        }
    }
}

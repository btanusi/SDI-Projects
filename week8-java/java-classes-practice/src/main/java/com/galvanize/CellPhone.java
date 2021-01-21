package com.galvanize;

public class CellPhone {
    CallingCard card;
    String currentNumber;
    String history;
    boolean isTalking;
    boolean cutOff;
    int minutesGoneBy;

    public CellPhone(CallingCard card){
        this.card = card;
        this.history = "";
        this.refreshPhone();
    }

    private void refreshPhone(){
        isTalking = false;
        currentNumber = "";
        minutesGoneBy = 0;
        cutOff = false;
    }

    public boolean isTalking(){
        return isTalking;
    }

    public void call(String number){
        isTalking = true;
        currentNumber = number;
    }

    public void tick(){
        if(isTalking){
            minutesGoneBy++;
            card.useMinutes(1);
            if(card.getRemainingMinutes() == 0){
                cutOff = true;
                this.endCall();
            }
        }
    }

    public void endCall(){
        String minutes = "";
        if(cutOff){
            minutes = "cut off at ";
        }
        minutes += Integer.toString(minutesGoneBy) + " minute";
        if(minutesGoneBy != 1){
            minutes += "s";
        }

        if(history != ""){
            history += ", ";
        }
        history += currentNumber + " (" + minutes + ")";
        refreshPhone();
    }

    public String getHistory(){
        return history;
    }

}

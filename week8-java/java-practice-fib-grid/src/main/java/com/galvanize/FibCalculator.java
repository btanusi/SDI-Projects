package com.galvanize;
import java.util.ArrayList;

public class FibCalculator {
    private ArrayList<String> fib;
    private int fn_1 = 1;
    private int fn_2 = 0;

    public FibCalculator(int totalFibNums){
        fib = new ArrayList<String>(){};
        this.buildFib(totalFibNums);
    }

    private void buildFib(int totalFibNums){
        fib.add("0");
        if(totalFibNums > 1){
            fib.add("1");
        }
        while(totalFibNums > 2){
            int nextNum = fn_1+fn_2;
            fib.add(Integer.toString(nextNum));
            fn_2 = fn_1;
            fn_1 = nextNum;
            totalFibNums--;
        }
    }

    public ArrayList<String> getFib(){
        return fib;
    }
    public String getByIndex(int index){
        return fib.get(index);
    }
}


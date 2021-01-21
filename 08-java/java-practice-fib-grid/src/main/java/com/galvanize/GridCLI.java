package com.galvanize;
import java.util.ArrayList;

public class GridCLI {

    static FibCalculator fibCalc;
    static int r;
    static int c;
    static int lengthOfFinalNum;

    public static String buildRow(int fibIndex){
        //build number row
        String numRow = "";
        for (int i = 0; i < c; i++){
            String num = fibCalc.getByIndex(i+fibIndex);
            int lengthOfNum = num.length();
            numRow += "| ";
            for (int j = 0; j != lengthOfFinalNum - lengthOfNum; j++){
                numRow += " ";
            }
            numRow += num + " ";
        }
        numRow += "|";
        return numRow;
    }

    private static void checkArgs(String[] args){
        if(args.length < 2){
            throw new IllegalArgumentException("Too few arguments");
        } else if(args.length > 2){
            throw new IllegalArgumentException("Too many arguments");
        }

        try{
            r = Integer.parseInt(args[0]);
            c = Integer.parseInt(args[1]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Non-numeric arguments are not allowed");
        }

        if (r == 0 || c == 0 || r < 0 || c < 0){
            throw new IllegalArgumentException("Arguments must be positive integers");
        }
    }

    public static void main(String[] args) {
        checkArgs(args);
        int i;
        fibCalc = new FibCalculator(r*c);
        lengthOfFinalNum = fibCalc.getByIndex(r*c-1).length();

        //build row border
        String rowBorder = "";
        for (i = 0; i < c; i++){
            rowBorder += "+--";
            for(int j = 0; j < lengthOfFinalNum; j++){
                rowBorder += "-";
            }
        }
        rowBorder += "+";

        //System.out.printf("\n");
        for(i = 0; i < r; i++){
            int fibIndex = i*c;
            System.out.printf("%s\n", rowBorder);
            System.out.printf("%s\n", buildRow(fibIndex));
        }
        System.out.printf("%s", rowBorder);
    }
}

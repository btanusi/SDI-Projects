package com.galvanize;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class FibCalculatorTest {
    FibCalculator fib;
    int totalFibNums;

    @Test
    public void itBuildsFibSeqOfOne(){
        totalFibNums = 1;
        fib = new FibCalculator(totalFibNums);
        ArrayList<String> expected = new ArrayList<String>(
                Arrays.asList("0"));
        assertTrue(expected.equals(fib.getFib()));
    }

    @Test
    public void itBuildsFibSeqOfTwo(){
        totalFibNums = 2;
        fib = new FibCalculator(totalFibNums);
        ArrayList<String> expected = new ArrayList<String>(
                Arrays.asList("0", "1"));
        assertTrue(expected.equals(fib.getFib()));
    }

    @Test
    public void itBuildsFibSeqOfThree(){
        totalFibNums = 3;
        fib = new FibCalculator(totalFibNums);
        ArrayList<String> expected = new ArrayList<String>(
                Arrays.asList("0", "1", "1"));
        assertTrue(expected.equals(fib.getFib()));
    }

    @Test
    public void itBuildsFibSeqOfTen(){
        totalFibNums = 10;
        fib = new FibCalculator(totalFibNums);
        ArrayList<String> expected = new ArrayList<String>(
                Arrays.asList("0", "1", "1", "2", "3", "5", "8", "13", "21", "34"));
        assertTrue(expected.equals(fib.getFib()));
    }

    @Test
    public void itGetsFibNumByIndex(){
        totalFibNums = 10;
        fib = new FibCalculator(totalFibNums);
        assertEquals("34", fib.getByIndex(9));
    }

}

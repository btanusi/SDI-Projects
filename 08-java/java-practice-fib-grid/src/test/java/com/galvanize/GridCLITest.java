package com.galvanize;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GridCLITest {

    PrintStream original;
    ByteArrayOutputStream outContent;
    GridCLI grid;

    // This block captures everything written to System.out
    @BeforeEach
    public void setOut() {
        grid = new GridCLI();
        original = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    // This block resets System.out to whatever it was before
    @AfterEach
    public void restoreOut() {
        System.setOut(original);
    }

    // String output = outContent.toString(); // will give you what your code printed to System.out

    @Test
    public void itThrowsExceptionIfTooFewArgs() {
        String[] args = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { grid.main(args); });
        String expectedMessage = "Too few arguments";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void itThrowsExceptionIfTooManyArgs() {
        String[] args = {"1", "2", "3"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { grid.main(args); });
        String expectedMessage = "Too many arguments";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void itThrowsExceptionForNonNumericArgs() {
        String[] args = {"1", "five"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { grid.main(args); });
        String expectedMessage = "Non-numeric arguments are not allowed";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void itThrowsExceptionForArgumentOfZero() {
        String[] args = {"0", "1"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { grid.main(args); });
        String expectedMessage = "Arguments must be positive integers";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void itThrowsExceptionForNegativeArgument() {
        String[] args = {"1", "-1"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { grid.main(args); });
        String expectedMessage = "Arguments must be positive integers";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void itPrintsOut1FibNum() {
        String[] args = {"1", "1"};
        grid.main(args);
        String output = outContent.toString(); // will give you what your code printed to System.out
        assertEquals("+---+\n| 0 |\n+---+", output);
    }
}

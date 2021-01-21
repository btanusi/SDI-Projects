package com.galvanize;

import com.galvanize.util.ClassProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.opentest4j.AssertionFailedError;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class SolutionTest {

    private static final PrintStream ORIGINAL_OUT = System.out;

    private static final String GRIDCLI_CLASSNAME = "com.galvanize.GridCLI";
    private static final String TEST_FILE_PREFIX = "grid";

    private static ClassProxy Application;
    private ClassLoader classLoader;

    @BeforeAll
    public static void validateStructure() {
        ClassProxy.classNamed(GRIDCLI_CLASSNAME).ensureMainMethod();
    }

    @BeforeEach
    public void setup() {
        Application = ClassProxy.classNamed(GRIDCLI_CLASSNAME).ensureMainMethod();
        classLoader = getClass().getClassLoader();
    }

    @DisplayName("Odd Rows x Even Columns")
    @ParameterizedTest(name = "Row: {0}, Column: {1}")
    @CsvSource({"1, 2", "3, 6"})
    public void testGridOddEven(String r, String c) {
        gridTestRunner(r, c);
    }

    @DisplayName("Even Rows x Odd Columns")
    @ParameterizedTest(name = "Row: {0}, Column: {1}")
    @CsvSource({"2, 1", "4, 7"})
    public void testGridEvenOdd(String r, String c) {
        gridTestRunner(r, c);
    }

    @DisplayName("Square Grid")
    @ParameterizedTest(name = "Side: {0}")
    @CsvSource({"1, 1", "2, 2", "3, 3", "4, 4"})
    public void testGridSquare(String r, String c) {
        gridTestRunner(r, c);
    }

    @DisplayName("Invalid input parameters")
    @ParameterizedTest(name = "Row: {0}, Column: {1}")
    @CsvSource({"0, 1", "-3, 1", "1, -1"})
    public void testGridWithInvalidParams(String r, String c) {
        Throwable e = Application.assertInvokeThrows(
                IllegalArgumentException.class,
                "main",
                new Object[]{new String[]{r, c}});

        assertThat(e.getMessage(), allOf(containsString("positive"), containsString(("integers"))));
    }

    private static List<String> executeMain(String[] args) {
        final ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputByteArray));

        Object[] params = {args};
        Application.invoke("main", params);

        String output = outputByteArray.toString();

        System.out.flush();
        System.setOut(ORIGINAL_OUT);

        List<String> result = new ArrayList<>(Arrays.asList(output.split("\n")));

        return result;
    }

    private static List<String> getExpected(File file) {
        List<String> resultStringList = new ArrayList<>();
        try {
            resultStringList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            fail(String.format("Failed to load test fixture: %s", file.getName()));
        }

        return resultStringList;
    }

    private void gridTestRunner(String r, String c) {
        String[] args = {r, c};

        String fileString = String.format("%s%s_%s", TEST_FILE_PREFIX, r, c);

        try {
            File expectedFile = new File(classLoader.getResource(fileString).getFile());

            List<String> actualArray = executeMain(args);
            List<String> expectedArray = getExpected(expectedFile);

            String actualOutput = actualArray.stream().collect(joining("\n"));
            String expectedOutput = expectedArray.stream().collect(joining("\n"));

            if (!actualOutput.trim().equals(expectedOutput.trim())) {
                StringBuilder builder = new StringBuilder()
                        .append(String.format("The grid of %s by %s does not produce the correct result\n\n", r, c));

                builder.append(String.format("Expected grid of %s by %s to produce the following grid:\n\n", r, c))
                        .append(expectedOutput)
                        .append("\n\n");

                if (actualArray.isEmpty() || actualArray.stream().collect(joining("")).isEmpty()) {
                    builder.append("But it produced nothing\n\n");
                } else {
                    builder.append("But instead it produced:\n")
                            .append(actualOutput)
                            .append("\n\n");
                }

                fail(builder.toString());
            }
        } catch (NullPointerException np) {
            fail(String.format("Failed to load test fixture: %s", fileString));
        }
    }

}
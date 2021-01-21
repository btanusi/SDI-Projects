package com.galvanize;

import com.galvanize.util.ClassProxy;
import com.galvanize.util.InstanceProxy;
import com.google.common.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("letterCount Method")
public class LetterCountTest {

    private static final String ALGORITHM_CLASSNAME = "com.galvanize.Algorithm";

    private InstanceProxy algorithm;

    @BeforeEach
    public void setup() {
        ClassProxy Algorithm = ClassProxy.classNamed(ALGORITHM_CLASSNAME)
                .ensureMethod(m -> m
                        .named("letterCount")
                        .isPublic()
                        .withParameters(String.class)
                        .returns(new TypeToken<Map<String, Long>>() {
                        }));

        algorithm = Algorithm.newInstance();
    }

    private Map<String, Long> callLetterCount(String input) {
        return (Map<String, Long>) algorithm.invoke("letterCount", input);
    }

    @Test
    public void letterCountForEmptyString() {
        String testString = "";
        Map<String, Long> result = callLetterCount(testString);
        ensureResult(result);
        assertEquals(0, result.keySet().size(), "Letter count of " + testString);
    }

    @Test
    public void letterCountForSingleLetter() {
        String testString = "m";
        Map<String, Long> result = callLetterCount(testString);
        ensureKey(result, testString);
        assertEquals(1L, result.get(testString).longValue(), "Letter count of " + testString);
    }

    @Test
    public void letterCountForDifferentDoubleLetters() {
        String testString = "op";
        Map<String, Long> result = callLetterCount(testString);

        ensureKey(result, "o");
        ensureKey(result, "p");

        assertEquals(1L, result.get("o").longValue(), "Letter count of " + testString);
        assertEquals(1L, result.get("p").longValue(), "Letter count of " + testString);
    }

    @Test
    public void letterCountForMixedCaseString() {
        String testString = "tT";

        Map<String, Long> result = callLetterCount(testString);
        ensureKey(result, "t");
        assertEquals(2L, result.get("t").longValue(), "Letter count of " + testString);
    }

    @Test
    public void letterCountForRepeatedLetter() {
        String testString = "pp";

        Map<String, Long> result = callLetterCount(testString);
        String key = "p";
        ensureKey(result, key);
        assertEquals(2L, result.get(key).longValue(), "Letter count of " + testString);
    }

    private void ensureResult(Object result) {
        if (result == null) {
            fail("Expected Algorithm.letterCount to return a Map<String, Long> but it returned null");
        }
    }

    private void ensureKey(Map<String, Long> result, String key) {
        ensureResult(result);
        if (!result.containsKey(key)) {
            fail(String.format("Expected %s to contain the key '%s'", result, key));
        }
    }

    @Test
    public void letterCountForArbitraryString() {
        String testString = "Hello!";

        Map<String, Long> expected = new HashMap<>();
        expected.put("h", 1L);
        expected.put("e", 1L);
        expected.put("l", 2L);
        expected.put("o", 1L);
        expected.put("!", 1L);

        Map<String, Long> result = callLetterCount(testString);

        assertEquals(expected, result, "Letter count of " + testString);
    }

}

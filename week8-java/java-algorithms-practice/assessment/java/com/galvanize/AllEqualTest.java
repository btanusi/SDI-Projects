package com.galvanize;

import com.galvanize.util.ClassProxy;
import com.galvanize.util.InstanceProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("allEqual Method")
public class AllEqualTest {

    private static final String ALGORITHM_CLASSNAME = "com.galvanize.Algorithm";
    private ClassProxy Algorithm;
    private InstanceProxy algorithm;

    @BeforeEach
    public void setup() {
        Algorithm = ClassProxy.classNamed(ALGORITHM_CLASSNAME)
                .ensureMethod(m -> m
                        .named("allEqual")
                        .withParameters(String.class)
                        .returns(Boolean.TYPE));

        algorithm = Algorithm.newInstance();
    }

    private boolean callAllEqual(String input) {
        return (boolean) algorithm.invoke("allEqual", input);
    }

    @Test
    public void allEqualWithEmptyString() {
        String testString = "";
        assertEquals(false, callAllEqual(testString), "allEqual returns false for empty string");
    }

    @Test
    public void allEqualWithSingleLetter() {
        String testString = "a";
        assertEquals(true, callAllEqual(testString), "allEqual returns true for single letter");
    }

    @Test
    public void allEqualDoubleLetters() {
        String testString = "gg";
        assertEquals(true, callAllEqual(testString), "allEqual returns true for double repeated letters");
    }

    @Test
    public void allEqualDifferentDouble() {
        String testString = "gh";
        assertEquals(false, callAllEqual(testString), "allEqual returns false for different double letters");
    }

    @Test
    public void allEqualMixedCase() {
        String testString = "gG";
        assertEquals(true, callAllEqual(testString), "allEqual returns true for the same letters with with different cases");
    }

    @Test
    public void allEqualArbitraryString() {
        String testString1 = "aaAaaaa";
        assertEquals(true, callAllEqual(testString1), "Are all equal " + testString1);

        String testString2 = "zzz";
        assertEquals(true, callAllEqual(testString2), "Are all equal " + testString2);

        String testString3 = "ccccDccdd";
        assertEquals(false, callAllEqual(testString3), "Are all equal " + testString3);

        String testString4 = "abaaacaa";
        assertEquals(false, callAllEqual(testString4), "Are all equal " + testString4);
    }

}

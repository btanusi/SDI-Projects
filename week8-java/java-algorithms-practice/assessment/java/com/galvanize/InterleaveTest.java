package com.galvanize;

import com.galvanize.util.ClassProxy;
import com.galvanize.util.InstanceProxy;
import com.google.common.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("interleave Method")
public class InterleaveTest {

    private static final String ALGORITHM_CLASSNAME = "com.galvanize.Algorithm";
    private InstanceProxy algorithm;

    @BeforeEach
    public void setup() {
        ClassProxy Algorithm = ClassProxy.classNamed(ALGORITHM_CLASSNAME)
                .ensureMethod(m -> m
                        .named("interleave")
                        .isPublic()
                        .withParameters(
                                new TypeToken<List<String>>() {
                                },
                                new TypeToken<List<String>>() {
                                }
                        )
                        .returns(String.class));

        algorithm = Algorithm.newInstance();
    }

    private String callInterleave(List<String> list1, List<String> list2) {
        return (String) algorithm.invoke("interleave", list1, list2);
    }

    @Test
    public void interleaveForSingletonLists() {
        String[] input1 = {"a"};
        String[] input2 = {"b"};
        ArrayList<String> a1 = new ArrayList<>(Arrays.asList(input1));
        ArrayList<String> a2 = new ArrayList<>(Arrays.asList(input2));

        String result = callInterleave(a1, a2);

        assertEquals("ab", result, "Interleave [\"a\"] and [\"b\"]");
    }

    @Test
    public void interleaveForArbitraryLists() {
        String[] input1 = {"a", "b"};
        String[] input2 = {"c", "d"};
        ArrayList<String> a1 = new ArrayList<>(Arrays.asList(input1));
        ArrayList<String> a2 = new ArrayList<>(Arrays.asList(input2));

        String result = callInterleave(a1, a2);

        assertEquals("acbd", result, "Interleave [\"a\", \"b\"] and [\"c\", \"d\"]");
    }

    @Test
    public void interleaveForEmptyLists() {
        ArrayList<String> a1 = new ArrayList<>(Collections.emptyList());
        ArrayList<String> a2 = new ArrayList<>(Collections.emptyList());

        String result = callInterleave(a1, a2);

        assertEquals("", result, "Interleave [] and [] should return an empty string");
    }

    @Test
    public void interleaveForListsWithEmptyStrings() {
        String[] input1 = {""};
        String[] input2 = {""};

        ArrayList<String> a1 = new ArrayList<>(Arrays.asList(input1));
        ArrayList<String> a2 = new ArrayList<>(Arrays.asList(input2));

        String result = callInterleave(a1, a2);

        assertEquals("", result, "Interleave [\"\"] and [\"\"] should return an empty string");
    }

}

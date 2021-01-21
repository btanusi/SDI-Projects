package com.galvanize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Collections;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {
    Algorithm algorithm;
    HashMap<String, Long> expectedMap;
    @BeforeEach
    void init(){
        algorithm = new Algorithm();
        expectedMap = new HashMap<String, Long> ();
    }

    @Test
    void allEqualReturnsFalseForEmptyString(){
        assertEquals(false, algorithm.allEqual(""));
    }
    @Test
    void allEqualReturnsFalseForStringWithDifferentChars(){
        assertEquals(false, algorithm.allEqual("bbBbabbb"));
    }
    @Test
    void allEqualReturnsTrueForStringWithSameChars(){
        assertEquals(true, algorithm.allEqual("aAa"));
    }
    @Test
    void letterCountReturnsEmptyMapForEmptyString(){
        assertEquals(expectedMap, algorithm.letterCount(""));
    }
    @Test
    void letterCountReturnsMapWithOneKeyForStringWithSameChars(){
        expectedMap.put("a", 2L);
        assertEquals(expectedMap, algorithm.letterCount("aa"));
    }
    @Test
    void letterCountReturnsMapWithMultipleKeysForStringWithDifferentChars(){
        expectedMap.put("a", 1L);
        expectedMap.put("b", 2L);
        expectedMap.put("c", 1L);
        expectedMap.put("d", 1L);
        assertEquals(expectedMap, algorithm.letterCount("abBcd"));
    }

    @Test
    void interleaveReturnsEmptyStringForEmptyLists(){
        assertEquals("", algorithm.interleave(Collections.emptyList(), Collections.emptyList()));
    }

    @Test
    void interleaveReturnsStringForLists(){
        assertEquals("abcdef", algorithm.interleave(Arrays.asList("a", "c", "e"), Arrays.asList("b", "d", "f")));
    }

}

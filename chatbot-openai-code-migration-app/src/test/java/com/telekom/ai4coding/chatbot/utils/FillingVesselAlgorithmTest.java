package com.telekom.ai4coding.chatbot.utils;

import com.telekom.ai4coding.chatbot.configuration.properties.AcaProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FillingVesselAlgorithmTest {


    private static final int MAX_LENGTH = 30_000;

    private final FillingVesselAlgorithm fillingVesselAlgorithm =
            new FillingVesselAlgorithm(new AcaProperties(null, null
                    , null, MAX_LENGTH, 0));

    @Test
    void testBothInputsWithinLimit() {
        String llm = "A".repeat(5000);
        String keywords = "B".repeat(5000);
        String result = fillingVesselAlgorithm.fillVessel(llm, keywords);
        assertEquals(10000, result.length());
        assertTrue(result.startsWith(llm));
        assertTrue(result.endsWith(keywords));
    }

    @Test
    void testExactlyMaxLength() {
        String llm = "A".repeat(18000);
        String keywords = "B".repeat(12000);
        String result = fillingVesselAlgorithm.fillVessel(llm, keywords);
        assertEquals(MAX_LENGTH, result.length());
        assertEquals(llm + keywords, result);
    }

    @Test
    void testKeywordsLessThanIdealLength() {
        String llm = "A".repeat(24000);
        String keywords = "B".repeat(10000);
        String result = fillingVesselAlgorithm.fillVessel(llm, keywords);
        assertEquals(MAX_LENGTH, result.length());
        assertTrue(result.startsWith(keywords));
        assertTrue(result.endsWith("A".repeat(20000)));
    }

    @Test
    void testLLMLessThanIdealLength() {
        String llm = "A".repeat(16000);
        String keywords = "B".repeat(20000);
        String result = fillingVesselAlgorithm.fillVessel(llm, keywords);
        assertEquals(MAX_LENGTH, result.length());
        assertTrue(result.startsWith(llm));
        assertTrue(result.endsWith("B".repeat(14000)));
    }

    @Test
    void testBothExceedIdealLength() {
        String llm = "A".repeat(20000);
        String keywords = "B".repeat(16000);
        String result = fillingVesselAlgorithm.fillVessel(llm, keywords);
        assertEquals(MAX_LENGTH, result.length());
        assertTrue(result.startsWith("B".repeat(12000)));
        assertTrue(result.endsWith("A".repeat(18000)));
    }

    @Test
    void testEmptyLLM() {
        String llm = "";
        String keywords = "B".repeat(40000);
        String result = fillingVesselAlgorithm.fillVessel(llm, keywords);
        assertEquals(MAX_LENGTH, result.length());
        assertEquals("B".repeat(MAX_LENGTH), result);
    }

    @Test
    void testEmptyKeywords() {
        String llm = "A".repeat(40000);
        String keywords = "";
        String result = fillingVesselAlgorithm.fillVessel(llm, keywords);
        assertEquals(MAX_LENGTH, result.length());
        assertEquals("A".repeat(MAX_LENGTH), result);
    }

    @Test
    void testBothEmpty() {
        String result = fillingVesselAlgorithm.fillVessel("", "");
        assertEquals(0, result.length());
    }

}
package com.telekom.ai4coding.chatbot.utils;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenCalculationTest {


    @Test
    public void calculateTokens() {
        String message = "Hi, Create python function to add 2 nums.";
        int tokens = TokenCalculation.calculateTokens(message);
        assertEquals(10, tokens);
    }

    @Test
    public void calculateTokensReturnsZero() {
        String message = "";
        int tokens = TokenCalculation.calculateTokens(message);
        assertEquals(0, tokens);
    }

    @Test
    public void calculateTokensWithLargeMessage() {
        String message = "Create a video that tells the story of a lost puppy finding its way back home.";
        int tokens = TokenCalculation.calculateTokens(message);
        assertEquals(17, tokens);
    }
}
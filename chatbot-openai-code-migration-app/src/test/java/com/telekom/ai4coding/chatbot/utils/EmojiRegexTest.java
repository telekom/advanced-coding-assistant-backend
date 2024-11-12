package com.telekom.ai4coding.chatbot.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class EmojiRegexTest {


    @Test
    public void replaceEmojis_withEmojis() {
        String input = "Hello, this is a test 😊";
        byte[] fileContent = input.getBytes(StandardCharsets.UTF_8);
        byte[] result = EmojiRegex.replaceEmojis(fileContent);
        String resultString = new String(result, StandardCharsets.UTF_8);
        Assertions.assertEquals("Hello, this is a test [Emoji]", resultString);
    }

    @Test
    public void replaceEmojis_withoutEmojis() {
        String input = "Hello, this is a test";
        byte[] fileContent = input.getBytes(StandardCharsets.UTF_8);
        byte[] result = EmojiRegex.replaceEmojis(fileContent);
        String resultString = new String(result, StandardCharsets.UTF_8);
        Assertions.assertEquals(input, resultString);
    }

    @Test
    public void replaceEmojis_mixedContent() {
        String input = "Test 😊 and 😢 mixed";
        byte[] fileContent = input.getBytes(StandardCharsets.UTF_8);
        byte[] result = EmojiRegex.replaceEmojis(fileContent);
        String resultString = new String(result, StandardCharsets.UTF_8);
        Assertions.assertEquals("Test [Emoji] and [Emoji] mixed", resultString);
    }

    @Test
    public void replaceEmojis_emptyString() {
        String input = "";
        byte[] fileContent = input.getBytes(StandardCharsets.UTF_8);
        byte[] result = EmojiRegex.replaceEmojis(fileContent);
        String resultString = new String(result, StandardCharsets.UTF_8);
        Assertions.assertEquals(input, resultString);
    }

    @Test
    public void replaceEmojis_onlyEmojis() {
        String input = "😊😢😂";
        byte[] fileContent = input.getBytes(StandardCharsets.UTF_8);
        byte[] result = EmojiRegex.replaceEmojis(fileContent);
        String resultString = new String(result, StandardCharsets.UTF_8);
        Assertions.assertEquals("[Emoji]", resultString);
    }

}
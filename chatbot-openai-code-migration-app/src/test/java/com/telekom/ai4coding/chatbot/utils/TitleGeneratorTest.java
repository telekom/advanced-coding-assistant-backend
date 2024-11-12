package com.telekom.ai4coding.chatbot.utils;

import org.junit.Test;

import static com.telekom.ai4coding.chatbot.utils.TitleGenerator.generateTitle;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitleGeneratorTest {


    @Test
    public void generateTitleFromPrompt() {
        String prompt = "Hi, Create python function to add 2 nums.";
        String title = generateTitle(prompt);
        assertEquals("Create Python Function Add", title);
    }

    @Test
    public void generateTitleReturnsNewConversation() {
        String prompt = "Hi there!";
        String title = generateTitle(prompt);
        assertEquals("New Conversation", title);
    }

    @Test
    public void generateTitleReturnsEmptyString() {
        String prompt = "";
        String title = generateTitle(prompt);
        assertEquals("New Conversation", title);
    }
}
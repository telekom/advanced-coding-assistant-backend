package com.telekom.ai4coding.chatbot.utils;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * We need to replace the standard emojis with different values, as the encoding can vary depending on the system
 * and can extra byte to it.
 */
public class EmojiRegex {
    private static final Pattern EMOJI_PATTERN = Pattern.compile("[\\x{1F600}-\\x{1F64F}\\x{1F300}-\\x{1F5FF}\\x{1F680}-\\x{1F6FF}\\x{1F700}-\\x{1F77F}\\x{1F780}-\\x{1F7FF}\\x{1F800}-\\x{1F8FF}\\x{1F900}-\\x{1F9FF}\\x{1FA00}-\\x{1FA6F}\\x{1FA70}-\\x{1FAFF}\\x{2600}-\\x{26FF}\\x{2700}-\\x{27BF}]+");

    public static byte[] replaceEmojis(byte[] fileContent) {
        String fileContentString = new String(fileContent, StandardCharsets.UTF_8);
        Matcher matcher = EMOJI_PATTERN.matcher(fileContentString);
        return matcher.replaceAll("[Emoji]").getBytes();
    }
}

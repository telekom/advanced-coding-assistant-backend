package com.telekom.ai4coding.chatbot.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TitleGenerator {

    private static final List<String> IGNORE_WORDS =
            Arrays.asList("the", "i", "you", "how", "can", "is", "hi","hello","hey", "in", "at", "of", "and", "a",
                    "to", "it", "for", "on", "with", "as", "by", "an", "be", "this", "that", "which", "or", "from",
                    "are", "was", "were", "will", "would", "should", "could", "has", "have", "had", "do", "does",
                    "did", "but", "if", "then", "than", "so", "such", "not", "no", "yes", "he", "she", "they",
                    "we", "us", "them", "his", "her", "their", "our", "my", "your", "its", "me", "him", "who",
                    "whom", "whose", "what", "when", "where", "why", "how", "all", "any", "both", "each", "few",
                    "many", "most", "some", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                    "ten", "first", "second", "third", "about", "after", "again", "against", "between", "into",
                    "through", "during", "before", "under", "over", "above", "below", "up", "down", "out", "off",
                    "over", "under","there");


    public static String generateTitle(String prompt) {
        String cleanedMessage = prompt.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();

        List<String> tokens = Arrays.asList(cleanedMessage.split("\\s+"));

        List<String> keyPhrases = tokens.stream().filter(token -> !IGNORE_WORDS.contains(token)).toList();

        String title = String.join(" ", keyPhrases);

        title = !title.isEmpty() ?
                Arrays.stream(title.split("\\s+"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                        .collect(Collectors.joining(" "))
                : "New Conversation";

        return title.substring(0, Math.min(title.length(), 27)).trim();
    }
}



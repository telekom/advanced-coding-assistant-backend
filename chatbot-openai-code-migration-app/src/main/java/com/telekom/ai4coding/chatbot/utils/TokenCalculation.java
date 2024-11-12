package com.telekom.ai4coding.chatbot.utils;

import opennlp.tools.tokenize.SimpleTokenizer;

public class TokenCalculation {


    public static int calculateTokens(String message){
       SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
         String[] tokens = tokenizer.tokenize(message);
            return tokens.length;
    }
}

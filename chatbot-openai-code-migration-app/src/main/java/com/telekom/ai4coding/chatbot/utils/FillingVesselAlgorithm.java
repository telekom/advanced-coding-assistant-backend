package com.telekom.ai4coding.chatbot.utils;

import com.telekom.ai4coding.chatbot.configuration.properties.AcaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * FillingVesselAlgorithm
 *
 * This class implements the Filling Vessel Algorithm, which combines characters
 * from two sources (LLM-generated text and keyword search results) into a single
 * result string, adhering to specific rules and proportions.
 *
 * Algorithm Description:
 * 1. The result string has a configurable maximum capacity (default 30,000 characters).
 * 2. Ideally, the algorithm aims to fill:
 *    - 3/5 (60%) of the result with LLM-generated characters
 *    - 2/5 (40%) of the result with keyword search characters
 * 3. If either source doesn't have enough characters to fill its ideal proportion,
 *    the algorithm allows the other source to fill the remaining space.
 * 4. If both sources combined have fewer characters than the maximum length,
 *    all characters from both sources are used without truncation.
 *
 * Algorithm Flow:
 * 1. Check if the total length of both inputs is <= maximum length
 *    If yes, combine both inputs as is.
 * 2. If total length > maximum length, check if keyword length < 2/5 of maximum length
 *    If yes, use all keyword characters and fill the rest with LLM characters.
 * 3. If keyword length >= 2/5 of maximum length, check if LLM length < 3/5 of maximum length
 *    If yes, use all LLM characters and fill the rest with keyword characters.
 * 4. If both lengths exceed their ideal proportions, take 2/5 of maximum length
 *    from keywords and 3/5 from LLM.
 */
@RequiredArgsConstructor
@Component
public class FillingVesselAlgorithm {

    private final AcaProperties acaProperties;

    private static final double KEYWORD_RATIO = 2.0 / 5.0;
    private static final double LLM_RATIO = 3.0 / 5.0;

    /**
     * Combines characters from LLM-generated text and keyword search results
     * according to the Filling Vessel Algorithm rules.
     *
     * @param llmChars    String containing LLM-generated characters
     * @param keywordChars String containing keyword search characters
     * @return A string of maximum characters combining both inputs
     */
    public String fillVessel(String llmChars, String keywordChars) {
        // If combined length is within limit, return the concatenation
        if (llmChars.length() + keywordChars.length() <= acaProperties.fillingVesselAlgorithmMaxLength()) {
            return llmChars + keywordChars;
        }

        int keywordIdealLength = (int) (acaProperties.fillingVesselAlgorithmMaxLength() * KEYWORD_RATIO);
        int llmIdealLength = (int) (acaProperties.fillingVesselAlgorithmMaxLength() * LLM_RATIO);

        // If keywords are insufficient, use all keywords and fill rest with LLM
        if (keywordChars.length() < keywordIdealLength) {
            return keywordChars + llmChars.substring(0, acaProperties.fillingVesselAlgorithmMaxLength() - keywordChars.length());
        }

        // If LLM chars are insufficient, use all LLM and fill rest with keywords
        if (llmChars.length() < llmIdealLength) {
            return llmChars + keywordChars.substring(0, acaProperties.fillingVesselAlgorithmMaxLength() - llmChars.length());
        }

        // If both exceed ideal lengths, use ideal proportions
        return keywordChars.substring(0, keywordIdealLength) +
                llmChars.substring(0, llmIdealLength);
    }

}

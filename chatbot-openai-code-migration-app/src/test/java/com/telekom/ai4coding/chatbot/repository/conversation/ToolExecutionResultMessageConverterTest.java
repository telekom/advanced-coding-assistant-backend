package com.telekom.ai4coding.chatbot.repository.conversation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.core.convert.TypeDescriptor;

import dev.langchain4j.data.message.ToolExecutionResultMessage;

public class ToolExecutionResultMessageConverterTest {
    private ToolExecutionResultMessageConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ToolExecutionResultMessageConverter();
    }

    @Test
    void testConvertToolExecutionResultMessageToValue() {
        ToolExecutionResultMessage message = new ToolExecutionResultMessage(
          "id", "toolName", "text");
        TypeDescriptor sourceType = TypeDescriptor.valueOf(ToolExecutionResultMessage.class);
        TypeDescriptor targetType = TypeDescriptor.valueOf(Value.class);

        Value result = (Value) converter.convert(message, sourceType, targetType);

        String[] expectedResult = {"id", "toolName", "text"};
        assertArrayEquals(expectedResult, result.asList().toArray(String[]::new));
    }

    @Test
    void testConvertValueToToolExecutionResultMessage() {
        String[] attributes = {"id", "toolName", "text"};
        Value value = Values.value(attributes);
        TypeDescriptor sourceType = TypeDescriptor.valueOf(Value.class);
        TypeDescriptor targetType = TypeDescriptor.valueOf(ToolExecutionResultMessage.class);

        ToolExecutionResultMessage result = (ToolExecutionResultMessage) converter.convert(value, sourceType, targetType);

        assertEquals("id", result.id());
        assertEquals("toolName", result.toolName());
        assertEquals("text", result.text());
    }
}

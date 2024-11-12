package com.telekom.ai4coding.chatbot.repository.conversation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.core.convert.TypeDescriptor;

import dev.langchain4j.agent.tool.ToolExecutionRequest;

public class ToolExecutionRequestConverterTest {
    private ToolExecutionRequestConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ToolExecutionRequestConverter();
    }

    @Test
    void testConvertToolExecutionRequestToValue() {
        ToolExecutionRequest request = ToolExecutionRequest.builder()
                .id("123")
                .name("testTool")
                .arguments("arg1")
                .build();
        TypeDescriptor sourceType = TypeDescriptor.valueOf(ToolExecutionRequest.class);
        TypeDescriptor targetType = TypeDescriptor.valueOf(Value.class);

        Value result = (Value) converter.convert(request, sourceType, targetType);

        assertEquals("123;testTool;arg1", result.asString());
    }

    @Test
    void testConvertValueToToolExecutionRequest() {
        // Arrange
        Value value = Values.value("123;testTool;arg1");
        TypeDescriptor sourceType = TypeDescriptor.valueOf(Value.class);
        TypeDescriptor targetType = TypeDescriptor.valueOf(ToolExecutionRequest.class);

        // Act
        ToolExecutionRequest result = (ToolExecutionRequest) converter.convert(value, sourceType, targetType);

        // Assert
        assertEquals("123", result.id());
        assertEquals("testTool", result.name());
        assertEquals("arg1", result.arguments());
    }
}

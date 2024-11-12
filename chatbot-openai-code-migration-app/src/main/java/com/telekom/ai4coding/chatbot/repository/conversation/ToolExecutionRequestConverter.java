package com.telekom.ai4coding.chatbot.repository.conversation;

import java.util.Set;
import java.util.HashSet;

import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import dev.langchain4j.agent.tool.ToolExecutionRequest;

public class ToolExecutionRequestConverter implements GenericConverter {

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> convertiblePairs = new HashSet<>();
		convertiblePairs.add(new ConvertiblePair(ToolExecutionRequest.class, Value.class));
		convertiblePairs.add(new ConvertiblePair(Value.class, ToolExecutionRequest.class));
		return convertiblePairs;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (ToolExecutionRequest.class.isAssignableFrom(sourceType.getType())) {
		ToolExecutionRequest toolExecutionRequest = (ToolExecutionRequest) source;
				String toolExecutionRequestAttributes = (
						toolExecutionRequest.id() + ";" +
					 	toolExecutionRequest.name() + ";" +
					 	toolExecutionRequest.arguments()
				);
				return Values.value(toolExecutionRequestAttributes);
		} else {
				String[] toolExecutionRequestAttributes = ((Value) source).asString().split(";");
				return ToolExecutionRequest.builder()
								.id(toolExecutionRequestAttributes[0])
								.name(toolExecutionRequestAttributes[1])
								.arguments(toolExecutionRequestAttributes[2])
								.build();
		}
	}
}

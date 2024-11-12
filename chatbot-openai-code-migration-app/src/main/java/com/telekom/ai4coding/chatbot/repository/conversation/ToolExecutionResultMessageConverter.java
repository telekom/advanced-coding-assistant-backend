package com.telekom.ai4coding.chatbot.repository.conversation;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import dev.langchain4j.data.message.ToolExecutionResultMessage;

public class ToolExecutionResultMessageConverter implements GenericConverter {

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> convertiblePairs = new HashSet<>();
		convertiblePairs.add(new ConvertiblePair(ToolExecutionResultMessage.class, Value.class));
		convertiblePairs.add(new ConvertiblePair(Value.class, ToolExecutionResultMessage.class));
		return convertiblePairs;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (ToolExecutionResultMessage.class.isAssignableFrom(sourceType.getType())) {
        ToolExecutionResultMessage toolExecutionResultMessage = (ToolExecutionResultMessage) source;
        String[] toolExecutionResultMessageArray = {
          toolExecutionResultMessage.id(),
          toolExecutionResultMessage.toolName(),
          toolExecutionResultMessage.text()
        };
				return Values.value(toolExecutionResultMessageArray); 
		} else {
      List<String> toolExecutionResultMessageArray = ((Value) source).asList(obj -> {
          String str = String.valueOf(obj);
          if (str.startsWith("\"") && str.endsWith("\"")) {
              return str.substring(1, str.length() - 1);
          }
          return str;
      });
				return new ToolExecutionResultMessage(
          toolExecutionResultMessageArray.get(0),
          toolExecutionResultMessageArray.get(1),
          toolExecutionResultMessageArray.get(2));
		}
	}
}

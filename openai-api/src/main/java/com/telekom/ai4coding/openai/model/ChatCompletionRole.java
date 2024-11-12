package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;

/**
 * The role of the author of a message
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum ChatCompletionRole {
  
  SYSTEM("system"),
  
  USER("user"),
  
  ASSISTANT("assistant"),
  
  TOOL("tool"),
  
  FUNCTION("function");

  private String value;

  ChatCompletionRole(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ChatCompletionRole fromValue(String value) {
    for (ChatCompletionRole b : ChatCompletionRole.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
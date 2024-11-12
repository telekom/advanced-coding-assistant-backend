package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * ChatCompletionNamedToolChoiceFunction
 */

@JsonTypeName("ChatCompletionNamedToolChoice_function")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionNamedToolChoiceFunction {

  private String name;

  public ChatCompletionNamedToolChoiceFunction() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionNamedToolChoiceFunction(String name) {
    this.name = name;
  }

  public ChatCompletionNamedToolChoiceFunction name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the function to call.
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "The name of the function to call.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionNamedToolChoiceFunction chatCompletionNamedToolChoiceFunction = (ChatCompletionNamedToolChoiceFunction) o;
    return Objects.equals(this.name, chatCompletionNamedToolChoiceFunction.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionNamedToolChoiceFunction {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
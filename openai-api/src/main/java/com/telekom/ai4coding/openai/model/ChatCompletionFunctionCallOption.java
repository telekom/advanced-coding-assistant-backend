package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * Specifying a particular function via &#x60;{\&quot;name\&quot;: \&quot;my_function\&quot;}&#x60; forces the model to call that function. 
 */

@Schema(name = "ChatCompletionFunctionCallOption", description = "Specifying a particular function via `{\"name\": \"my_function\"}` forces the model to call that function. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionFunctionCallOption implements CreateChatCompletionRequestFunctionCall {

  private String name;

  public ChatCompletionFunctionCallOption() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionFunctionCallOption(String name) {
    this.name = name;
  }

  public ChatCompletionFunctionCallOption name(String name) {
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
    ChatCompletionFunctionCallOption chatCompletionFunctionCallOption = (ChatCompletionFunctionCallOption) o;
    return Objects.equals(this.name, chatCompletionFunctionCallOption.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionFunctionCallOption {\n");
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
package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ChatCompletionFunctions
 * @deprecated
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionFunctions {

  private String description;

  private String name;

  @Valid
  private Map<String, Object> parameters = new HashMap<>();

  public ChatCompletionFunctions() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionFunctions(String name) {
    this.name = name;
  }

  public ChatCompletionFunctions description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A description of what the function does, used by the model to choose when and how to call the function.
   * @return description
  */
  
  @Schema(name = "description", description = "A description of what the function does, used by the model to choose when and how to call the function.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ChatCompletionFunctions name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the function to be called. Must be a-z, A-Z, 0-9, or contain underscores and dashes, with a maximum length of 64.
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "The name of the function to be called. Must be a-z, A-Z, 0-9, or contain underscores and dashes, with a maximum length of 64.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ChatCompletionFunctions parameters(Map<String, Object> parameters) {
    this.parameters = parameters;
    return this;
  }

  public ChatCompletionFunctions putParametersItem(String key, Object parametersItem) {
    if (this.parameters == null) {
      this.parameters = new HashMap<>();
    }
    this.parameters.put(key, parametersItem);
    return this;
  }

  /**
   * The parameters the functions accepts, described as a JSON Schema object. See the [guide](/docs/guides/text-generation/function-calling) for examples, and the [JSON Schema reference](https://json-schema.org/understanding-json-schema/) for documentation about the format.   Omitting `parameters` defines a function with an empty parameter list.
   * @return parameters
  */
  
  @Schema(name = "parameters", description = "The parameters the functions accepts, described as a JSON Schema object. See the [guide](/docs/guides/text-generation/function-calling) for examples, and the [JSON Schema reference](https://json-schema.org/understanding-json-schema/) for documentation about the format.   Omitting `parameters` defines a function with an empty parameter list.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("parameters")
  public Map<String, Object> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, Object> parameters) {
    this.parameters = parameters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionFunctions chatCompletionFunctions = (ChatCompletionFunctions) o;
    return Objects.equals(this.description, chatCompletionFunctions.description) &&
        Objects.equals(this.name, chatCompletionFunctions.name) &&
        Objects.equals(this.parameters, chatCompletionFunctions.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, name, parameters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionFunctions {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
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
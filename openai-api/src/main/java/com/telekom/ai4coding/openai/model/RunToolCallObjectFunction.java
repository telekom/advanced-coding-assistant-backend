package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * The function definition.
 */

@Schema(name = "RunToolCallObject_function", description = "The function definition.")
@JsonTypeName("RunToolCallObject_function")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunToolCallObjectFunction {

  private String name;

  private String arguments;

  public RunToolCallObjectFunction() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunToolCallObjectFunction(String name, String arguments) {
    this.name = name;
    this.arguments = arguments;
  }

  public RunToolCallObjectFunction name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the function.
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "The name of the function.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RunToolCallObjectFunction arguments(String arguments) {
    this.arguments = arguments;
    return this;
  }

  /**
   * The arguments that the model expects you to pass to the function.
   * @return arguments
  */
  @NotNull 
  @Schema(name = "arguments", description = "The arguments that the model expects you to pass to the function.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("arguments")
  public String getArguments() {
    return arguments;
  }

  public void setArguments(String arguments) {
    this.arguments = arguments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunToolCallObjectFunction runToolCallObjectFunction = (RunToolCallObjectFunction) o;
    return Objects.equals(this.name, runToolCallObjectFunction.name) &&
        Objects.equals(this.arguments, runToolCallObjectFunction.arguments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, arguments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunToolCallObjectFunction {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    arguments: ").append(toIndentedString(arguments)).append("\n");
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
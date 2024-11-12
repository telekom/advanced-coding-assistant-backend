package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * The definition of the function that was called.
 */

@Schema(name = "RunStepDetailsToolCallsFunctionObject_function", description = "The definition of the function that was called.")
@JsonTypeName("RunStepDetailsToolCallsFunctionObject_function")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepDetailsToolCallsFunctionObjectFunction {

  private String name;

  private String arguments;

  private JsonNullable<String> output = JsonNullable.<String>undefined();

  public RunStepDetailsToolCallsFunctionObjectFunction() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepDetailsToolCallsFunctionObjectFunction(String name, String arguments, String output) {
    this.name = name;
    this.arguments = arguments;
    this.output = JsonNullable.of(output);
  }

  public RunStepDetailsToolCallsFunctionObjectFunction name(String name) {
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

  public RunStepDetailsToolCallsFunctionObjectFunction arguments(String arguments) {
    this.arguments = arguments;
    return this;
  }

  /**
   * The arguments passed to the function.
   * @return arguments
  */
  @NotNull 
  @Schema(name = "arguments", description = "The arguments passed to the function.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("arguments")
  public String getArguments() {
    return arguments;
  }

  public void setArguments(String arguments) {
    this.arguments = arguments;
  }

  public RunStepDetailsToolCallsFunctionObjectFunction output(String output) {
    this.output = JsonNullable.of(output);
    return this;
  }

  /**
   * The output of the function. This will be `null` if the outputs have not been [submitted](/docs/api-reference/runs/submitToolOutputs) yet.
   * @return output
  */
  @NotNull 
  @Schema(name = "output", description = "The output of the function. This will be `null` if the outputs have not been [submitted](/docs/api-reference/runs/submitToolOutputs) yet.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("output")
  public JsonNullable<String> getOutput() {
    return output;
  }

  public void setOutput(JsonNullable<String> output) {
    this.output = output;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDetailsToolCallsFunctionObjectFunction runStepDetailsToolCallsFunctionObjectFunction = (RunStepDetailsToolCallsFunctionObjectFunction) o;
    return Objects.equals(this.name, runStepDetailsToolCallsFunctionObjectFunction.name) &&
        Objects.equals(this.arguments, runStepDetailsToolCallsFunctionObjectFunction.arguments) &&
        Objects.equals(this.output, runStepDetailsToolCallsFunctionObjectFunction.output);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, arguments, output);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDetailsToolCallsFunctionObjectFunction {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    arguments: ").append(toIndentedString(arguments)).append("\n");
    sb.append("    output: ").append(toIndentedString(output)).append("\n");
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
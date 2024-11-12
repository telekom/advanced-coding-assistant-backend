package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * The Code Interpreter tool call definition.
 */

@Schema(name = "RunStepDetailsToolCallsCodeObject_code_interpreter", description = "The Code Interpreter tool call definition.")
@JsonTypeName("RunStepDetailsToolCallsCodeObject_code_interpreter")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepDetailsToolCallsCodeObjectCodeInterpreter {

  private String input;

  @Valid
  private List<@Valid RunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsInner> outputs = new ArrayList<>();

  public RunStepDetailsToolCallsCodeObjectCodeInterpreter() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepDetailsToolCallsCodeObjectCodeInterpreter(String input, List<@Valid RunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsInner> outputs) {
    this.input = input;
    this.outputs = outputs;
  }

  public RunStepDetailsToolCallsCodeObjectCodeInterpreter input(String input) {
    this.input = input;
    return this;
  }

  /**
   * The input to the Code Interpreter tool call.
   * @return input
  */
  @NotNull 
  @Schema(name = "input", description = "The input to the Code Interpreter tool call.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("input")
  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public RunStepDetailsToolCallsCodeObjectCodeInterpreter outputs(List<@Valid RunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsInner> outputs) {
    this.outputs = outputs;
    return this;
  }

  public RunStepDetailsToolCallsCodeObjectCodeInterpreter addOutputsItem(RunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsInner outputsItem) {
    if (this.outputs == null) {
      this.outputs = new ArrayList<>();
    }
    this.outputs.add(outputsItem);
    return this;
  }

  /**
   * The outputs from the Code Interpreter tool call. Code Interpreter can output one or more items, including text (`logs`) or images (`image`). Each of these are represented by a different object type.
   * @return outputs
  */
  @NotNull @Valid 
  @Schema(name = "outputs", description = "The outputs from the Code Interpreter tool call. Code Interpreter can output one or more items, including text (`logs`) or images (`image`). Each of these are represented by a different object type.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("outputs")
  public List<@Valid RunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsInner> getOutputs() {
    return outputs;
  }

  public void setOutputs(List<@Valid RunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsInner> outputs) {
    this.outputs = outputs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDetailsToolCallsCodeObjectCodeInterpreter runStepDetailsToolCallsCodeObjectCodeInterpreter = (RunStepDetailsToolCallsCodeObjectCodeInterpreter) o;
    return Objects.equals(this.input, runStepDetailsToolCallsCodeObjectCodeInterpreter.input) &&
        Objects.equals(this.outputs, runStepDetailsToolCallsCodeObjectCodeInterpreter.outputs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input, outputs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDetailsToolCallsCodeObjectCodeInterpreter {\n");
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
    sb.append("    outputs: ").append(toIndentedString(outputs)).append("\n");
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
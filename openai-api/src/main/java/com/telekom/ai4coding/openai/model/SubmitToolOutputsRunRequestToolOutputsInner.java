package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * SubmitToolOutputsRunRequestToolOutputsInner
 */

@JsonTypeName("SubmitToolOutputsRunRequest_tool_outputs_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SubmitToolOutputsRunRequestToolOutputsInner {

  private String toolCallId;

  private String output;

  public SubmitToolOutputsRunRequestToolOutputsInner toolCallId(String toolCallId) {
    this.toolCallId = toolCallId;
    return this;
  }

  /**
   * The ID of the tool call in the `required_action` object within the run object the output is being submitted for.
   * @return toolCallId
  */
  
  @Schema(name = "tool_call_id", description = "The ID of the tool call in the `required_action` object within the run object the output is being submitted for.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tool_call_id")
  public String getToolCallId() {
    return toolCallId;
  }

  public void setToolCallId(String toolCallId) {
    this.toolCallId = toolCallId;
  }

  public SubmitToolOutputsRunRequestToolOutputsInner output(String output) {
    this.output = output;
    return this;
  }

  /**
   * The output of the tool call to be submitted to continue the run.
   * @return output
  */
  
  @Schema(name = "output", description = "The output of the tool call to be submitted to continue the run.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("output")
  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
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
    SubmitToolOutputsRunRequestToolOutputsInner submitToolOutputsRunRequestToolOutputsInner = (SubmitToolOutputsRunRequestToolOutputsInner) o;
    return Objects.equals(this.toolCallId, submitToolOutputsRunRequestToolOutputsInner.toolCallId) &&
        Objects.equals(this.output, submitToolOutputsRunRequestToolOutputsInner.output);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toolCallId, output);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubmitToolOutputsRunRequestToolOutputsInner {\n");
    sb.append("    toolCallId: ").append(toIndentedString(toolCallId)).append("\n");
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
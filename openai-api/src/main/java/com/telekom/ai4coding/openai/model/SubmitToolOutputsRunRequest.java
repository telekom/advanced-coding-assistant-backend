package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * SubmitToolOutputsRunRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SubmitToolOutputsRunRequest {

  @Valid
  private List<@Valid SubmitToolOutputsRunRequestToolOutputsInner> toolOutputs = new ArrayList<>();

  public SubmitToolOutputsRunRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SubmitToolOutputsRunRequest(List<@Valid SubmitToolOutputsRunRequestToolOutputsInner> toolOutputs) {
    this.toolOutputs = toolOutputs;
  }

  public SubmitToolOutputsRunRequest toolOutputs(List<@Valid SubmitToolOutputsRunRequestToolOutputsInner> toolOutputs) {
    this.toolOutputs = toolOutputs;
    return this;
  }

  public SubmitToolOutputsRunRequest addToolOutputsItem(SubmitToolOutputsRunRequestToolOutputsInner toolOutputsItem) {
    if (this.toolOutputs == null) {
      this.toolOutputs = new ArrayList<>();
    }
    this.toolOutputs.add(toolOutputsItem);
    return this;
  }

  /**
   * A list of tools for which the outputs are being submitted.
   * @return toolOutputs
  */
  @NotNull @Valid 
  @Schema(name = "tool_outputs", description = "A list of tools for which the outputs are being submitted.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tool_outputs")
  public List<@Valid SubmitToolOutputsRunRequestToolOutputsInner> getToolOutputs() {
    return toolOutputs;
  }

  public void setToolOutputs(List<@Valid SubmitToolOutputsRunRequestToolOutputsInner> toolOutputs) {
    this.toolOutputs = toolOutputs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubmitToolOutputsRunRequest submitToolOutputsRunRequest = (SubmitToolOutputsRunRequest) o;
    return Objects.equals(this.toolOutputs, submitToolOutputsRunRequest.toolOutputs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toolOutputs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubmitToolOutputsRunRequest {\n");
    sb.append("    toolOutputs: ").append(toIndentedString(toolOutputs)).append("\n");
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
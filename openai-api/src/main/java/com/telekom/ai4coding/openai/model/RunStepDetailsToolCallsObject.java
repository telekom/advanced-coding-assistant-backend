package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Details of the tool call.
 */

@Schema(name = "RunStepDetailsToolCallsObject", description = "Details of the tool call.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepDetailsToolCallsObject implements RunStepObjectStepDetails {

  /**
   * Always `tool_calls`.
   */
  public enum TypeEnum {
    TOOL_CALLS("tool_calls");

    private String value;

    TypeEnum(String value) {
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
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  @Valid
  private List<@Valid RunStepDetailsToolCallsObjectToolCallsInner> toolCalls = new ArrayList<>();

  public RunStepDetailsToolCallsObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepDetailsToolCallsObject(TypeEnum type, List<@Valid RunStepDetailsToolCallsObjectToolCallsInner> toolCalls) {
    this.type = type;
    this.toolCalls = toolCalls;
  }

  public RunStepDetailsToolCallsObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Always `tool_calls`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "Always `tool_calls`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunStepDetailsToolCallsObject toolCalls(List<@Valid RunStepDetailsToolCallsObjectToolCallsInner> toolCalls) {
    this.toolCalls = toolCalls;
    return this;
  }

  public RunStepDetailsToolCallsObject addToolCallsItem(RunStepDetailsToolCallsObjectToolCallsInner toolCallsItem) {
    if (this.toolCalls == null) {
      this.toolCalls = new ArrayList<>();
    }
    this.toolCalls.add(toolCallsItem);
    return this;
  }

  /**
   * An array of tool calls the run step was involved in. These can be associated with one of three types of tools: `code_interpreter`, `retrieval`, or `function`. 
   * @return toolCalls
  */
  @NotNull @Valid 
  @Schema(name = "tool_calls", description = "An array of tool calls the run step was involved in. These can be associated with one of three types of tools: `code_interpreter`, `retrieval`, or `function`. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tool_calls")
  public List<@Valid RunStepDetailsToolCallsObjectToolCallsInner> getToolCalls() {
    return toolCalls;
  }

  public void setToolCalls(List<@Valid RunStepDetailsToolCallsObjectToolCallsInner> toolCalls) {
    this.toolCalls = toolCalls;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDetailsToolCallsObject runStepDetailsToolCallsObject = (RunStepDetailsToolCallsObject) o;
    return Objects.equals(this.type, runStepDetailsToolCallsObject.type) &&
        Objects.equals(this.toolCalls, runStepDetailsToolCallsObject.toolCalls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, toolCalls);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDetailsToolCallsObject {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    toolCalls: ").append(toIndentedString(toolCalls)).append("\n");
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
package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Text output from the Code Interpreter tool call as part of a run step.
 */

@Schema(name = "RunStepDetailsToolCallsCodeOutputLogsObject", description = "Text output from the Code Interpreter tool call as part of a run step.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepDetailsToolCallsCodeOutputLogsObject implements RunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsInner {

  /**
   * Always `logs`.
   */
  public enum TypeEnum {
    LOGS("logs");

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

  private String logs;

  public RunStepDetailsToolCallsCodeOutputLogsObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepDetailsToolCallsCodeOutputLogsObject(TypeEnum type, String logs) {
    this.type = type;
    this.logs = logs;
  }

  public RunStepDetailsToolCallsCodeOutputLogsObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Always `logs`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "Always `logs`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunStepDetailsToolCallsCodeOutputLogsObject logs(String logs) {
    this.logs = logs;
    return this;
  }

  /**
   * The text output from the Code Interpreter tool call.
   * @return logs
  */
  @NotNull 
  @Schema(name = "logs", description = "The text output from the Code Interpreter tool call.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("logs")
  public String getLogs() {
    return logs;
  }

  public void setLogs(String logs) {
    this.logs = logs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDetailsToolCallsCodeOutputLogsObject runStepDetailsToolCallsCodeOutputLogsObject = (RunStepDetailsToolCallsCodeOutputLogsObject) o;
    return Objects.equals(this.type, runStepDetailsToolCallsCodeOutputLogsObject.type) &&
        Objects.equals(this.logs, runStepDetailsToolCallsCodeOutputLogsObject.logs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, logs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDetailsToolCallsCodeOutputLogsObject {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    logs: ").append(toIndentedString(logs)).append("\n");
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
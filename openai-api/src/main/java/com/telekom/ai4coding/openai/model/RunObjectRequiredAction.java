package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Details on the action required to continue the run. Will be &#x60;null&#x60; if no action is required.
 */

@Schema(name = "RunObject_required_action", description = "Details on the action required to continue the run. Will be `null` if no action is required.")
@JsonTypeName("RunObject_required_action")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunObjectRequiredAction {

  /**
   * For now, this is always `submit_tool_outputs`.
   */
  public enum TypeEnum {
    SUBMIT_TOOL_OUTPUTS("submit_tool_outputs");

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

  private RunObjectRequiredActionSubmitToolOutputs submitToolOutputs;

  public RunObjectRequiredAction() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunObjectRequiredAction(TypeEnum type, RunObjectRequiredActionSubmitToolOutputs submitToolOutputs) {
    this.type = type;
    this.submitToolOutputs = submitToolOutputs;
  }

  public RunObjectRequiredAction type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * For now, this is always `submit_tool_outputs`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "For now, this is always `submit_tool_outputs`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunObjectRequiredAction submitToolOutputs(RunObjectRequiredActionSubmitToolOutputs submitToolOutputs) {
    this.submitToolOutputs = submitToolOutputs;
    return this;
  }

  /**
   * Get submitToolOutputs
   * @return submitToolOutputs
  */
  @NotNull @Valid 
  @Schema(name = "submit_tool_outputs", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("submit_tool_outputs")
  public RunObjectRequiredActionSubmitToolOutputs getSubmitToolOutputs() {
    return submitToolOutputs;
  }

  public void setSubmitToolOutputs(RunObjectRequiredActionSubmitToolOutputs submitToolOutputs) {
    this.submitToolOutputs = submitToolOutputs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunObjectRequiredAction runObjectRequiredAction = (RunObjectRequiredAction) o;
    return Objects.equals(this.type, runObjectRequiredAction.type) &&
        Objects.equals(this.submitToolOutputs, runObjectRequiredAction.submitToolOutputs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, submitToolOutputs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunObjectRequiredAction {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    submitToolOutputs: ").append(toIndentedString(submitToolOutputs)).append("\n");
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
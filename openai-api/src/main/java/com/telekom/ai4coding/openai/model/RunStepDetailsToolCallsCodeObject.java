package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Details of the Code Interpreter tool call the run step was involved in.
 */

@Schema(name = "RunStepDetailsToolCallsCodeObject", description = "Details of the Code Interpreter tool call the run step was involved in.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepDetailsToolCallsCodeObject implements RunStepDetailsToolCallsObjectToolCallsInner {

  private String id;

  /**
   * The type of tool call. This is always going to be `code_interpreter` for this type of tool call.
   */
  public enum TypeEnum {
    CODE_INTERPRETER("code_interpreter");

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

  private RunStepDetailsToolCallsCodeObjectCodeInterpreter codeInterpreter;

  public RunStepDetailsToolCallsCodeObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepDetailsToolCallsCodeObject(String id, TypeEnum type, RunStepDetailsToolCallsCodeObjectCodeInterpreter codeInterpreter) {
    this.id = id;
    this.type = type;
    this.codeInterpreter = codeInterpreter;
  }

  public RunStepDetailsToolCallsCodeObject id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the tool call.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The ID of the tool call.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RunStepDetailsToolCallsCodeObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of tool call. This is always going to be `code_interpreter` for this type of tool call.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "The type of tool call. This is always going to be `code_interpreter` for this type of tool call.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunStepDetailsToolCallsCodeObject codeInterpreter(RunStepDetailsToolCallsCodeObjectCodeInterpreter codeInterpreter) {
    this.codeInterpreter = codeInterpreter;
    return this;
  }

  /**
   * Get codeInterpreter
   * @return codeInterpreter
  */
  @NotNull @Valid 
  @Schema(name = "code_interpreter", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code_interpreter")
  public RunStepDetailsToolCallsCodeObjectCodeInterpreter getCodeInterpreter() {
    return codeInterpreter;
  }

  public void setCodeInterpreter(RunStepDetailsToolCallsCodeObjectCodeInterpreter codeInterpreter) {
    this.codeInterpreter = codeInterpreter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDetailsToolCallsCodeObject runStepDetailsToolCallsCodeObject = (RunStepDetailsToolCallsCodeObject) o;
    return Objects.equals(this.id, runStepDetailsToolCallsCodeObject.id) &&
        Objects.equals(this.type, runStepDetailsToolCallsCodeObject.type) &&
        Objects.equals(this.codeInterpreter, runStepDetailsToolCallsCodeObject.codeInterpreter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, codeInterpreter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDetailsToolCallsCodeObject {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    codeInterpreter: ").append(toIndentedString(codeInterpreter)).append("\n");
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
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
 * Tool call objects
 */

@Schema(name = "RunToolCallObject", description = "Tool call objects")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunToolCallObject {

  private String id;

  /**
   * The type of tool call the output is required for. For now, this is always `function`.
   */
  public enum TypeEnum {
    FUNCTION("function");

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

  private RunToolCallObjectFunction function;

  public RunToolCallObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunToolCallObject(String id, TypeEnum type, RunToolCallObjectFunction function) {
    this.id = id;
    this.type = type;
    this.function = function;
  }

  public RunToolCallObject id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the tool call. This ID must be referenced when you submit the tool outputs in using the [Submit tool outputs to run](/docs/api-reference/runs/submitToolOutputs) endpoint.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The ID of the tool call. This ID must be referenced when you submit the tool outputs in using the [Submit tool outputs to run](/docs/api-reference/runs/submitToolOutputs) endpoint.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RunToolCallObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of tool call the output is required for. For now, this is always `function`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "The type of tool call the output is required for. For now, this is always `function`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunToolCallObject function(RunToolCallObjectFunction function) {
    this.function = function;
    return this;
  }

  /**
   * Get function
   * @return function
  */
  @NotNull @Valid 
  @Schema(name = "function", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("function")
  public RunToolCallObjectFunction getFunction() {
    return function;
  }

  public void setFunction(RunToolCallObjectFunction function) {
    this.function = function;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunToolCallObject runToolCallObject = (RunToolCallObject) o;
    return Objects.equals(this.id, runToolCallObject.id) &&
        Objects.equals(this.type, runToolCallObject.type) &&
        Objects.equals(this.function, runToolCallObject.function);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, function);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunToolCallObject {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    function: ").append(toIndentedString(function)).append("\n");
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
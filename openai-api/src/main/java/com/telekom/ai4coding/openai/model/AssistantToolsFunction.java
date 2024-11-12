package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * AssistantToolsFunction
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AssistantToolsFunction implements AssistantObjectToolsInner, CreateThreadAndRunRequestToolsInner {

  /**
   * The type of tool being defined: `function`
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

  private FunctionObject function;

  public AssistantToolsFunction() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AssistantToolsFunction(TypeEnum type, FunctionObject function) {
    this.type = type;
    this.function = function;
  }

  public AssistantToolsFunction type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of tool being defined: `function`
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "The type of tool being defined: `function`", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public AssistantToolsFunction function(FunctionObject function) {
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
  public FunctionObject getFunction() {
    return function;
  }

  public void setFunction(FunctionObject function) {
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
    AssistantToolsFunction assistantToolsFunction = (AssistantToolsFunction) o;
    return Objects.equals(this.type, assistantToolsFunction.type) &&
        Objects.equals(this.function, assistantToolsFunction.function);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, function);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssistantToolsFunction {\n");
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
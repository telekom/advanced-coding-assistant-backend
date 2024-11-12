package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * AssistantToolsRetrieval
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AssistantToolsRetrieval implements AssistantObjectToolsInner, CreateThreadAndRunRequestToolsInner {

  /**
   * The type of tool being defined: `retrieval`
   */
  public enum TypeEnum {
    RETRIEVAL("retrieval");

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

  public AssistantToolsRetrieval() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AssistantToolsRetrieval(TypeEnum type) {
    this.type = type;
  }

  public AssistantToolsRetrieval type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of tool being defined: `retrieval`
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "The type of tool being defined: `retrieval`", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssistantToolsRetrieval assistantToolsRetrieval = (AssistantToolsRetrieval) o;
    return Objects.equals(this.type, assistantToolsRetrieval.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssistantToolsRetrieval {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
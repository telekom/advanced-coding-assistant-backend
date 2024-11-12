package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * RunStepDetailsToolCallsRetrievalObject
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepDetailsToolCallsRetrievalObject implements RunStepDetailsToolCallsObjectToolCallsInner {

  private String id;

  /**
   * The type of tool call. This is always going to be `retrieval` for this type of tool call.
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

  private Object retrieval;

  public RunStepDetailsToolCallsRetrievalObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepDetailsToolCallsRetrievalObject(String id, TypeEnum type, Object retrieval) {
    this.id = id;
    this.type = type;
    this.retrieval = retrieval;
  }

  public RunStepDetailsToolCallsRetrievalObject id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the tool call object.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The ID of the tool call object.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RunStepDetailsToolCallsRetrievalObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of tool call. This is always going to be `retrieval` for this type of tool call.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "The type of tool call. This is always going to be `retrieval` for this type of tool call.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunStepDetailsToolCallsRetrievalObject retrieval(Object retrieval) {
    this.retrieval = retrieval;
    return this;
  }

  /**
   * For now, this is always going to be an empty object.
   * @return retrieval
  */
  @NotNull 
  @Schema(name = "retrieval", description = "For now, this is always going to be an empty object.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("retrieval")
  public Object getRetrieval() {
    return retrieval;
  }

  public void setRetrieval(Object retrieval) {
    this.retrieval = retrieval;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDetailsToolCallsRetrievalObject runStepDetailsToolCallsRetrievalObject = (RunStepDetailsToolCallsRetrievalObject) o;
    return Objects.equals(this.id, runStepDetailsToolCallsRetrievalObject.id) &&
        Objects.equals(this.type, runStepDetailsToolCallsRetrievalObject.type) &&
        Objects.equals(this.retrieval, runStepDetailsToolCallsRetrievalObject.retrieval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, retrieval);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDetailsToolCallsRetrievalObject {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    retrieval: ").append(toIndentedString(retrieval)).append("\n");
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
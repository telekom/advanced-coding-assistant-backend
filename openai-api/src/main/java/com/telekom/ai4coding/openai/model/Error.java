package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Objects;

/**
 * Error
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Error {

  private JsonNullable<String> code = JsonNullable.<String>undefined();

  private String message;

  private JsonNullable<String> param = JsonNullable.<String>undefined();

  private String type;

  public Error() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Error(String code, String message, String param, String type) {
    this.code = JsonNullable.of(code);
    this.message = message;
    this.param = JsonNullable.of(param);
    this.type = type;
  }

  public Error code(String code) {
    this.code = JsonNullable.of(code);
    return this;
  }

  /**
   * Get code
   * @return code
  */
  @NotNull 
  @Schema(name = "code", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public JsonNullable<String> getCode() {
    return code;
  }

  public void setCode(JsonNullable<String> code) {
    this.code = code;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @NotNull 
  @Schema(name = "message", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error param(String param) {
    this.param = JsonNullable.of(param);
    return this;
  }

  /**
   * Get param
   * @return param
  */
  @NotNull 
  @Schema(name = "param", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("param")
  public JsonNullable<String> getParam() {
    return param;
  }

  public void setParam(JsonNullable<String> param) {
    this.param = param;
  }

  public Error type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
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
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.message, error.message) &&
        Objects.equals(this.param, error.param) &&
        Objects.equals(this.type, error.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, param, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    param: ").append(toIndentedString(param)).append("\n");
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
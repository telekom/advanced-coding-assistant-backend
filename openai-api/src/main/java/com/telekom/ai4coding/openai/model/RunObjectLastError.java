package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * The last error associated with this run. Will be &#x60;null&#x60; if there are no errors.
 */

@Schema(name = "RunObject_last_error", description = "The last error associated with this run. Will be `null` if there are no errors.")
@JsonTypeName("RunObject_last_error")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunObjectLastError {

  /**
   * One of `server_error` or `rate_limit_exceeded`.
   */
  public enum CodeEnum {
    SERVER_ERROR("server_error"),
    
    RATE_LIMIT_EXCEEDED("rate_limit_exceeded");

    private String value;

    CodeEnum(String value) {
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
    public static CodeEnum fromValue(String value) {
      for (CodeEnum b : CodeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private CodeEnum code;

  private String message;

  public RunObjectLastError() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunObjectLastError(CodeEnum code, String message) {
    this.code = code;
    this.message = message;
  }

  public RunObjectLastError code(CodeEnum code) {
    this.code = code;
    return this;
  }

  /**
   * One of `server_error` or `rate_limit_exceeded`.
   * @return code
  */
  @NotNull 
  @Schema(name = "code", description = "One of `server_error` or `rate_limit_exceeded`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public CodeEnum getCode() {
    return code;
  }

  public void setCode(CodeEnum code) {
    this.code = code;
  }

  public RunObjectLastError message(String message) {
    this.message = message;
    return this;
  }

  /**
   * A human-readable description of the error.
   * @return message
  */
  @NotNull 
  @Schema(name = "message", description = "A human-readable description of the error.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunObjectLastError runObjectLastError = (RunObjectLastError) o;
    return Objects.equals(this.code, runObjectLastError.code) &&
        Objects.equals(this.message, runObjectLastError.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunObjectLastError {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
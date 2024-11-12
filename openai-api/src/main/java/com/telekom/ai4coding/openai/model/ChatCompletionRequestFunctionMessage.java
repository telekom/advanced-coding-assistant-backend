package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Objects;

/**
 * ChatCompletionRequestFunctionMessage
 * @deprecated
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionRequestFunctionMessage implements ChatCompletionRequestMessage {

  /**
   * The role of the messages author, in this case `function`.
   */
  public enum RoleEnum {
    FUNCTION("function");

    private String value;

    RoleEnum(String value) {
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
    public static RoleEnum fromValue(String value) {
      for (RoleEnum b : RoleEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private RoleEnum role;

  private JsonNullable<String> content = JsonNullable.<String>undefined();

  private String name;

  public ChatCompletionRequestFunctionMessage() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionRequestFunctionMessage(RoleEnum role, String content, String name) {
    this.role = role;
    this.content = JsonNullable.of(content);
    this.name = name;
  }

  public ChatCompletionRequestFunctionMessage role(RoleEnum role) {
    this.role = role;
    return this;
  }

  /**
   * The role of the messages author, in this case `function`.
   * @return role
  */
  @NotNull 
  @Schema(name = "role", description = "The role of the messages author, in this case `function`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("role")
  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public ChatCompletionRequestFunctionMessage content(String content) {
    this.content = JsonNullable.of(content);
    return this;
  }

  /**
   * The contents of the function message.
   * @return content
  */
  @NotNull 
  @Schema(name = "content", description = "The contents of the function message.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("content")
  public JsonNullable<String> getContent() {
    return content;
  }

  public void setContent(JsonNullable<String> content) {
    this.content = content;
  }

  public ChatCompletionRequestFunctionMessage name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the function to call.
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "The name of the function to call.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionRequestFunctionMessage chatCompletionRequestFunctionMessage = (ChatCompletionRequestFunctionMessage) o;
    return Objects.equals(this.role, chatCompletionRequestFunctionMessage.role) &&
        Objects.equals(this.content, chatCompletionRequestFunctionMessage.content) &&
        Objects.equals(this.name, chatCompletionRequestFunctionMessage.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, content, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionRequestFunctionMessage {\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
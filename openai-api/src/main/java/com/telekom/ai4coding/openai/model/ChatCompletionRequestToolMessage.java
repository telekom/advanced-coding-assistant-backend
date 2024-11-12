package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * ChatCompletionRequestToolMessage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionRequestToolMessage implements ChatCompletionRequestMessage {

  /**
   * The role of the messages author, in this case `tool`.
   */
  public enum RoleEnum {
    TOOL("tool");

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

  private String content;

  private String toolCallId;

  public ChatCompletionRequestToolMessage() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionRequestToolMessage(RoleEnum role, String content, String toolCallId) {
    this.role = role;
    this.content = content;
    this.toolCallId = toolCallId;
  }

  public ChatCompletionRequestToolMessage role(RoleEnum role) {
    this.role = role;
    return this;
  }

  /**
   * The role of the messages author, in this case `tool`.
   * @return role
  */
  @NotNull 
  @Schema(name = "role", description = "The role of the messages author, in this case `tool`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("role")
  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public ChatCompletionRequestToolMessage content(String content) {
    this.content = content;
    return this;
  }

  /**
   * The contents of the tool message.
   * @return content
  */
  @NotNull 
  @Schema(name = "content", description = "The contents of the tool message.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("content")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ChatCompletionRequestToolMessage toolCallId(String toolCallId) {
    this.toolCallId = toolCallId;
    return this;
  }

  /**
   * Tool call that this message is responding to.
   * @return toolCallId
  */
  @NotNull 
  @Schema(name = "tool_call_id", description = "Tool call that this message is responding to.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tool_call_id")
  public String getToolCallId() {
    return toolCallId;
  }

  public void setToolCallId(String toolCallId) {
    this.toolCallId = toolCallId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionRequestToolMessage chatCompletionRequestToolMessage = (ChatCompletionRequestToolMessage) o;
    return Objects.equals(this.role, chatCompletionRequestToolMessage.role) &&
        Objects.equals(this.content, chatCompletionRequestToolMessage.content) &&
        Objects.equals(this.toolCallId, chatCompletionRequestToolMessage.toolCallId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, content, toolCallId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionRequestToolMessage {\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    toolCallId: ").append(toIndentedString(toolCallId)).append("\n");
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
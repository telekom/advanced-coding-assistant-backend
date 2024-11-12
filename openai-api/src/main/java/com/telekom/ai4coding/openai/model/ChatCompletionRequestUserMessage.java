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
 * ChatCompletionRequestUserMessage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionRequestUserMessage implements ChatCompletionRequestMessage {

  private ChatCompletionRequestUserMessageContent content;

  /**
   * The role of the messages author, in this case `user`.
   */
  public enum RoleEnum {
    USER("user");

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

  private String name;

  public ChatCompletionRequestUserMessage() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionRequestUserMessage(ChatCompletionRequestUserMessageContent content, RoleEnum role) {
    this.content = content;
    this.role = role;
  }

  public ChatCompletionRequestUserMessage content(ChatCompletionRequestUserMessageContent content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @NotNull @Valid 
  @Schema(name = "content", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("content")
  public ChatCompletionRequestUserMessageContent getContent() {
    return content;
  }

  public void setContent(ChatCompletionRequestUserMessageContent content) {
    this.content = content;
  }

  public ChatCompletionRequestUserMessage role(RoleEnum role) {
    this.role = role;
    return this;
  }

  /**
   * The role of the messages author, in this case `user`.
   * @return role
  */
  @NotNull 
  @Schema(name = "role", description = "The role of the messages author, in this case `user`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("role")
  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public ChatCompletionRequestUserMessage name(String name) {
    this.name = name;
    return this;
  }

  /**
   * An optional name for the participant. Provides the model information to differentiate between participants of the same role.
   * @return name
  */
  
  @Schema(name = "name", description = "An optional name for the participant. Provides the model information to differentiate between participants of the same role.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    ChatCompletionRequestUserMessage chatCompletionRequestUserMessage = (ChatCompletionRequestUserMessage) o;
    return Objects.equals(this.content, chatCompletionRequestUserMessage.content) &&
        Objects.equals(this.role, chatCompletionRequestUserMessage.role) &&
        Objects.equals(this.name, chatCompletionRequestUserMessage.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, role, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionRequestUserMessage {\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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
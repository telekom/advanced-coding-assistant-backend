package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * ChatCompletionRequestMessageContentPartText
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionRequestMessageContentPartText implements ChatCompletionRequestMessageContentPart {

  /**
   * The type of the content part.
   */
  public enum TypeEnum {
    TEXT("text");

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

  private String text;

  public ChatCompletionRequestMessageContentPartText() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionRequestMessageContentPartText(TypeEnum type, String text) {
    this.type = type;
    this.text = text;
  }

  public ChatCompletionRequestMessageContentPartText type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of the content part.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "The type of the content part.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public ChatCompletionRequestMessageContentPartText text(String text) {
    this.text = text;
    return this;
  }

  /**
   * The text content.
   * @return text
  */
  @NotNull 
  @Schema(name = "text", description = "The text content.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionRequestMessageContentPartText chatCompletionRequestMessageContentPartText = (ChatCompletionRequestMessageContentPartText) o;
    return Objects.equals(this.type, chatCompletionRequestMessageContentPartText.type) &&
        Objects.equals(this.text, chatCompletionRequestMessageContentPartText.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionRequestMessageContentPartText {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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
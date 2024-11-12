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
 * The text content that is part of a message.
 */

@Schema(name = "MessageContentTextObject", description = "The text content that is part of a message.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MessageContentTextObject implements MessageObjectContentInner {

  /**
   * Always `text`.
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

  private MessageContentTextObjectText text;

  public MessageContentTextObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageContentTextObject(TypeEnum type, MessageContentTextObjectText text) {
    this.type = type;
    this.text = text;
  }

  public MessageContentTextObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Always `text`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "Always `text`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public MessageContentTextObject text(MessageContentTextObjectText text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
  */
  @NotNull @Valid 
  @Schema(name = "text", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("text")
  public MessageContentTextObjectText getText() {
    return text;
  }

  public void setText(MessageContentTextObjectText text) {
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
    MessageContentTextObject messageContentTextObject = (MessageContentTextObject) o;
    return Objects.equals(this.type, messageContentTextObject.type) &&
        Objects.equals(this.text, messageContentTextObject.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageContentTextObject {\n");
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
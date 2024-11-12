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
 * ChatCompletionRequestMessageContentPartImage
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ChatCompletionRequestMessageContentPartImage implements ChatCompletionRequestMessageContentPart {

  /**
   * The type of the content part.
   */
  public enum TypeEnum {
    IMAGE_URL("image_url");

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

  private ChatCompletionRequestMessageContentPartImageImageUrl imageUrl;

  public ChatCompletionRequestMessageContentPartImage() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ChatCompletionRequestMessageContentPartImage(TypeEnum type, ChatCompletionRequestMessageContentPartImageImageUrl imageUrl) {
    this.type = type;
    this.imageUrl = imageUrl;
  }

  public ChatCompletionRequestMessageContentPartImage type(TypeEnum type) {
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

  public ChatCompletionRequestMessageContentPartImage imageUrl(ChatCompletionRequestMessageContentPartImageImageUrl imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Get imageUrl
   * @return imageUrl
  */
  @NotNull @Valid 
  @Schema(name = "image_url", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("image_url")
  public ChatCompletionRequestMessageContentPartImageImageUrl getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(ChatCompletionRequestMessageContentPartImageImageUrl imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionRequestMessageContentPartImage chatCompletionRequestMessageContentPartImage = (ChatCompletionRequestMessageContentPartImage) o;
    return Objects.equals(this.type, chatCompletionRequestMessageContentPartImage.type) &&
        Objects.equals(this.imageUrl, chatCompletionRequestMessageContentPartImage.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, imageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionRequestMessageContentPartImage {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
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
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
 * References an image [File](/docs/api-reference/files) in the content of a message.
 */

@Schema(name = "MessageContentImageFileObject", description = "References an image [File](/docs/api-reference/files) in the content of a message.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MessageContentImageFileObject implements MessageObjectContentInner {

  /**
   * Always `image_file`.
   */
  public enum TypeEnum {
    IMAGE_FILE("image_file");

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

  private MessageContentImageFileObjectImageFile imageFile;

  public MessageContentImageFileObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageContentImageFileObject(TypeEnum type, MessageContentImageFileObjectImageFile imageFile) {
    this.type = type;
    this.imageFile = imageFile;
  }

  public MessageContentImageFileObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Always `image_file`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "Always `image_file`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public MessageContentImageFileObject imageFile(MessageContentImageFileObjectImageFile imageFile) {
    this.imageFile = imageFile;
    return this;
  }

  /**
   * Get imageFile
   * @return imageFile
  */
  @NotNull @Valid 
  @Schema(name = "image_file", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("image_file")
  public MessageContentImageFileObjectImageFile getImageFile() {
    return imageFile;
  }

  public void setImageFile(MessageContentImageFileObjectImageFile imageFile) {
    this.imageFile = imageFile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageContentImageFileObject messageContentImageFileObject = (MessageContentImageFileObject) o;
    return Objects.equals(this.type, messageContentImageFileObject.type) &&
        Objects.equals(this.imageFile, messageContentImageFileObject.imageFile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, imageFile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageContentImageFileObject {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    imageFile: ").append(toIndentedString(imageFile)).append("\n");
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
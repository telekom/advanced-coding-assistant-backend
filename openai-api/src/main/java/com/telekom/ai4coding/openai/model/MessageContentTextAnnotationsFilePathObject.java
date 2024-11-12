package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * A URL for the file that&#39;s generated when the assistant used the &#x60;code_interpreter&#x60; tool to generate a file.
 */

@Schema(name = "MessageContentTextAnnotationsFilePathObject", description = "A URL for the file that's generated when the assistant used the `code_interpreter` tool to generate a file.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MessageContentTextAnnotationsFilePathObject implements MessageContentTextObjectTextAnnotationsInner {

  /**
   * Always `file_path`.
   */
  public enum TypeEnum {
    FILE_PATH("file_path");

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

  private MessageContentTextAnnotationsFilePathObjectFilePath filePath;

  private Integer startIndex;

  private Integer endIndex;

  public MessageContentTextAnnotationsFilePathObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageContentTextAnnotationsFilePathObject(TypeEnum type, String text, MessageContentTextAnnotationsFilePathObjectFilePath filePath, Integer startIndex, Integer endIndex) {
    this.type = type;
    this.text = text;
    this.filePath = filePath;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  public MessageContentTextAnnotationsFilePathObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Always `file_path`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "Always `file_path`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public MessageContentTextAnnotationsFilePathObject text(String text) {
    this.text = text;
    return this;
  }

  /**
   * The text in the message content that needs to be replaced.
   * @return text
  */
  @NotNull 
  @Schema(name = "text", description = "The text in the message content that needs to be replaced.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public MessageContentTextAnnotationsFilePathObject filePath(MessageContentTextAnnotationsFilePathObjectFilePath filePath) {
    this.filePath = filePath;
    return this;
  }

  /**
   * Get filePath
   * @return filePath
  */
  @NotNull @Valid 
  @Schema(name = "file_path", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("file_path")
  public MessageContentTextAnnotationsFilePathObjectFilePath getFilePath() {
    return filePath;
  }

  public void setFilePath(MessageContentTextAnnotationsFilePathObjectFilePath filePath) {
    this.filePath = filePath;
  }

  public MessageContentTextAnnotationsFilePathObject startIndex(Integer startIndex) {
    this.startIndex = startIndex;
    return this;
  }

  /**
   * Get startIndex
   * minimum: 0
   * @return startIndex
  */
  @NotNull @Min(0) 
  @Schema(name = "start_index", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("start_index")
  public Integer getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(Integer startIndex) {
    this.startIndex = startIndex;
  }

  public MessageContentTextAnnotationsFilePathObject endIndex(Integer endIndex) {
    this.endIndex = endIndex;
    return this;
  }

  /**
   * Get endIndex
   * minimum: 0
   * @return endIndex
  */
  @NotNull @Min(0) 
  @Schema(name = "end_index", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("end_index")
  public Integer getEndIndex() {
    return endIndex;
  }

  public void setEndIndex(Integer endIndex) {
    this.endIndex = endIndex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageContentTextAnnotationsFilePathObject messageContentTextAnnotationsFilePathObject = (MessageContentTextAnnotationsFilePathObject) o;
    return Objects.equals(this.type, messageContentTextAnnotationsFilePathObject.type) &&
        Objects.equals(this.text, messageContentTextAnnotationsFilePathObject.text) &&
        Objects.equals(this.filePath, messageContentTextAnnotationsFilePathObject.filePath) &&
        Objects.equals(this.startIndex, messageContentTextAnnotationsFilePathObject.startIndex) &&
        Objects.equals(this.endIndex, messageContentTextAnnotationsFilePathObject.endIndex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, text, filePath, startIndex, endIndex);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageContentTextAnnotationsFilePathObject {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
    sb.append("    startIndex: ").append(toIndentedString(startIndex)).append("\n");
    sb.append("    endIndex: ").append(toIndentedString(endIndex)).append("\n");
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
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
 * A citation within the message that points to a specific quote from a specific File associated with the assistant or the message. Generated when the assistant uses the \&quot;retrieval\&quot; tool to search files.
 */

@Schema(name = "MessageContentTextAnnotationsFileCitationObject", description = "A citation within the message that points to a specific quote from a specific File associated with the assistant or the message. Generated when the assistant uses the \"retrieval\" tool to search files.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MessageContentTextAnnotationsFileCitationObject implements MessageContentTextObjectTextAnnotationsInner {

  /**
   * Always `file_citation`.
   */
  public enum TypeEnum {
    FILE_CITATION("file_citation");

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

  private MessageContentTextAnnotationsFileCitationObjectFileCitation fileCitation;

  private Integer startIndex;

  private Integer endIndex;

  public MessageContentTextAnnotationsFileCitationObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageContentTextAnnotationsFileCitationObject(TypeEnum type, String text, MessageContentTextAnnotationsFileCitationObjectFileCitation fileCitation, Integer startIndex, Integer endIndex) {
    this.type = type;
    this.text = text;
    this.fileCitation = fileCitation;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  public MessageContentTextAnnotationsFileCitationObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Always `file_citation`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "Always `file_citation`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public MessageContentTextAnnotationsFileCitationObject text(String text) {
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

  public MessageContentTextAnnotationsFileCitationObject fileCitation(MessageContentTextAnnotationsFileCitationObjectFileCitation fileCitation) {
    this.fileCitation = fileCitation;
    return this;
  }

  /**
   * Get fileCitation
   * @return fileCitation
  */
  @NotNull @Valid 
  @Schema(name = "file_citation", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("file_citation")
  public MessageContentTextAnnotationsFileCitationObjectFileCitation getFileCitation() {
    return fileCitation;
  }

  public void setFileCitation(MessageContentTextAnnotationsFileCitationObjectFileCitation fileCitation) {
    this.fileCitation = fileCitation;
  }

  public MessageContentTextAnnotationsFileCitationObject startIndex(Integer startIndex) {
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

  public MessageContentTextAnnotationsFileCitationObject endIndex(Integer endIndex) {
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
    MessageContentTextAnnotationsFileCitationObject messageContentTextAnnotationsFileCitationObject = (MessageContentTextAnnotationsFileCitationObject) o;
    return Objects.equals(this.type, messageContentTextAnnotationsFileCitationObject.type) &&
        Objects.equals(this.text, messageContentTextAnnotationsFileCitationObject.text) &&
        Objects.equals(this.fileCitation, messageContentTextAnnotationsFileCitationObject.fileCitation) &&
        Objects.equals(this.startIndex, messageContentTextAnnotationsFileCitationObject.startIndex) &&
        Objects.equals(this.endIndex, messageContentTextAnnotationsFileCitationObject.endIndex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, text, fileCitation, startIndex, endIndex);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageContentTextAnnotationsFileCitationObject {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    fileCitation: ").append(toIndentedString(fileCitation)).append("\n");
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
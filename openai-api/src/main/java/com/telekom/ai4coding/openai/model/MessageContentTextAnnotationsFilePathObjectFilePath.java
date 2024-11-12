package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * MessageContentTextAnnotationsFilePathObjectFilePath
 */

@JsonTypeName("MessageContentTextAnnotationsFilePathObject_file_path")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MessageContentTextAnnotationsFilePathObjectFilePath {

  private String fileId;

  public MessageContentTextAnnotationsFilePathObjectFilePath() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageContentTextAnnotationsFilePathObjectFilePath(String fileId) {
    this.fileId = fileId;
  }

  public MessageContentTextAnnotationsFilePathObjectFilePath fileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  /**
   * The ID of the file that was generated.
   * @return fileId
  */
  @NotNull 
  @Schema(name = "file_id", description = "The ID of the file that was generated.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("file_id")
  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageContentTextAnnotationsFilePathObjectFilePath messageContentTextAnnotationsFilePathObjectFilePath = (MessageContentTextAnnotationsFilePathObjectFilePath) o;
    return Objects.equals(this.fileId, messageContentTextAnnotationsFilePathObjectFilePath.fileId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageContentTextAnnotationsFilePathObjectFilePath {\n");
    sb.append("    fileId: ").append(toIndentedString(fileId)).append("\n");
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
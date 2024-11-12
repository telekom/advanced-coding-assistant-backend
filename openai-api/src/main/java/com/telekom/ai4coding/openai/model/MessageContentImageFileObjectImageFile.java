package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * MessageContentImageFileObjectImageFile
 */

@JsonTypeName("MessageContentImageFileObject_image_file")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MessageContentImageFileObjectImageFile {

  private String fileId;

  public MessageContentImageFileObjectImageFile() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageContentImageFileObjectImageFile(String fileId) {
    this.fileId = fileId;
  }

  public MessageContentImageFileObjectImageFile fileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  /**
   * The [File](/docs/api-reference/files) ID of the image in the message content.
   * @return fileId
  */
  @NotNull 
  @Schema(name = "file_id", description = "The [File](/docs/api-reference/files) ID of the image in the message content.", requiredMode = Schema.RequiredMode.REQUIRED)
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
    MessageContentImageFileObjectImageFile messageContentImageFileObjectImageFile = (MessageContentImageFileObjectImageFile) o;
    return Objects.equals(this.fileId, messageContentImageFileObjectImageFile.fileId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageContentImageFileObjectImageFile {\n");
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
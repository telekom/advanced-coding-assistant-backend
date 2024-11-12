package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * CreateAssistantFileRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateAssistantFileRequest {

  private String fileId;

  public CreateAssistantFileRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateAssistantFileRequest(String fileId) {
    this.fileId = fileId;
  }

  public CreateAssistantFileRequest fileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  /**
   * A [File](/docs/api-reference/files) ID (with `purpose=\"assistants\"`) that the assistant should use. Useful for tools like `retrieval` and `code_interpreter` that can access files.
   * @return fileId
  */
  @NotNull 
  @Schema(name = "file_id", description = "A [File](/docs/api-reference/files) ID (with `purpose=\"assistants\"`) that the assistant should use. Useful for tools like `retrieval` and `code_interpreter` that can access files.", requiredMode = Schema.RequiredMode.REQUIRED)
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
    CreateAssistantFileRequest createAssistantFileRequest = (CreateAssistantFileRequest) o;
    return Objects.equals(this.fileId, createAssistantFileRequest.fileId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateAssistantFileRequest {\n");
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
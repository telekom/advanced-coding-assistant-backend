package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * The &#x60;File&#x60; object represents a document that has been uploaded to OpenAI.
 */

@Schema(name = "OpenAIFile", description = "The `File` object represents a document that has been uploaded to OpenAI.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class OpenAIFile {

  private String id;

  private Integer bytes;

  private Integer createdAt;

  private String filename;

  /**
   * The object type, which is always `file`.
   */
  public enum ObjectEnum {
    FILE("file");

    private String value;

    ObjectEnum(String value) {
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
    public static ObjectEnum fromValue(String value) {
      for (ObjectEnum b : ObjectEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ObjectEnum _object;

  /**
   * The intended purpose of the file. Supported values are `fine-tune`, `fine-tune-results`, `assistants`, and `assistants_output`.
   */
  public enum PurposeEnum {
    FINE_TUNE("fine-tune"),
    
    FINE_TUNE_RESULTS("fine-tune-results"),
    
    ASSISTANTS("assistants"),
    
    ASSISTANTS_OUTPUT("assistants_output");

    private String value;

    PurposeEnum(String value) {
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
    public static PurposeEnum fromValue(String value) {
      for (PurposeEnum b : PurposeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private PurposeEnum purpose;

  /**
   * Deprecated. The current status of the file, which can be either `uploaded`, `processed`, or `error`.
   */
  public enum StatusEnum {
    UPLOADED("uploaded"),
    
    PROCESSED("processed"),
    
    ERROR("error");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @Deprecated
  private StatusEnum status;

  @Deprecated
  private String statusDetails;

  public OpenAIFile() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public OpenAIFile(String id, Integer bytes, Integer createdAt, String filename, ObjectEnum _object, PurposeEnum purpose, StatusEnum status) {
    this.id = id;
    this.bytes = bytes;
    this.createdAt = createdAt;
    this.filename = filename;
    this._object = _object;
    this.purpose = purpose;
    this.status = status;
  }

  public OpenAIFile id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The file identifier, which can be referenced in the API endpoints.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The file identifier, which can be referenced in the API endpoints.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public OpenAIFile bytes(Integer bytes) {
    this.bytes = bytes;
    return this;
  }

  /**
   * The size of the file, in bytes.
   * @return bytes
  */
  @NotNull 
  @Schema(name = "bytes", description = "The size of the file, in bytes.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("bytes")
  public Integer getBytes() {
    return bytes;
  }

  public void setBytes(Integer bytes) {
    this.bytes = bytes;
  }

  public OpenAIFile createdAt(Integer createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the file was created.
   * @return createdAt
  */
  @NotNull 
  @Schema(name = "created_at", description = "The Unix timestamp (in seconds) for when the file was created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created_at")
  public Integer getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }

  public OpenAIFile filename(String filename) {
    this.filename = filename;
    return this;
  }

  /**
   * The name of the file.
   * @return filename
  */
  @NotNull 
  @Schema(name = "filename", description = "The name of the file.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("filename")
  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public OpenAIFile _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always `file`.
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always `file`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  public OpenAIFile purpose(PurposeEnum purpose) {
    this.purpose = purpose;
    return this;
  }

  /**
   * The intended purpose of the file. Supported values are `fine-tune`, `fine-tune-results`, `assistants`, and `assistants_output`.
   * @return purpose
  */
  @NotNull 
  @Schema(name = "purpose", description = "The intended purpose of the file. Supported values are `fine-tune`, `fine-tune-results`, `assistants`, and `assistants_output`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("purpose")
  public PurposeEnum getPurpose() {
    return purpose;
  }

  public void setPurpose(PurposeEnum purpose) {
    this.purpose = purpose;
  }

  public OpenAIFile status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Deprecated. The current status of the file, which can be either `uploaded`, `processed`, or `error`.
   * @return status
   * @deprecated
  */
  @NotNull 
  @Schema(name = "status", description = "Deprecated. The current status of the file, which can be either `uploaded`, `processed`, or `error`.", deprecated = true, requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  @Deprecated
  public StatusEnum getStatus() {
    return status;
  }

  /**
   * @deprecated
  */
  @Deprecated
  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public OpenAIFile statusDetails(String statusDetails) {
    this.statusDetails = statusDetails;
    return this;
  }

  /**
   * Deprecated. For details on why a fine-tuning training file failed validation, see the `error` field on `fine_tuning.job`.
   * @return statusDetails
   * @deprecated
  */
  
  @Schema(name = "status_details", description = "Deprecated. For details on why a fine-tuning training file failed validation, see the `error` field on `fine_tuning.job`.", deprecated = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status_details")
  @Deprecated
  public String getStatusDetails() {
    return statusDetails;
  }

  /**
   * @deprecated
  */
  @Deprecated
  public void setStatusDetails(String statusDetails) {
    this.statusDetails = statusDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OpenAIFile openAIFile = (OpenAIFile) o;
    return Objects.equals(this.id, openAIFile.id) &&
        Objects.equals(this.bytes, openAIFile.bytes) &&
        Objects.equals(this.createdAt, openAIFile.createdAt) &&
        Objects.equals(this.filename, openAIFile.filename) &&
        Objects.equals(this._object, openAIFile._object) &&
        Objects.equals(this.purpose, openAIFile.purpose) &&
        Objects.equals(this.status, openAIFile.status) &&
        Objects.equals(this.statusDetails, openAIFile.statusDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bytes, createdAt, filename, _object, purpose, status, statusDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OpenAIFile {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    bytes: ").append(toIndentedString(bytes)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    purpose: ").append(toIndentedString(purpose)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    statusDetails: ").append(toIndentedString(statusDetails)).append("\n");
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
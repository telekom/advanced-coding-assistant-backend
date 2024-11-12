package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * A list of [Files](/docs/api-reference/files) attached to an &#x60;assistant&#x60;.
 */

@Schema(name = "AssistantFileObject", description = "A list of [Files](/docs/api-reference/files) attached to an `assistant`.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AssistantFileObject {

  private String id;

  /**
   * The object type, which is always `assistant.file`.
   */
  public enum ObjectEnum {
    ASSISTANT_FILE("assistant.file");

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

  private Integer createdAt;

  private String assistantId;

  public AssistantFileObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AssistantFileObject(String id, ObjectEnum _object, Integer createdAt, String assistantId) {
    this.id = id;
    this._object = _object;
    this.createdAt = createdAt;
    this.assistantId = assistantId;
  }

  public AssistantFileObject id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier, which can be referenced in API endpoints.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The identifier, which can be referenced in API endpoints.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AssistantFileObject _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always `assistant.file`.
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always `assistant.file`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  public AssistantFileObject createdAt(Integer createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the assistant file was created.
   * @return createdAt
  */
  @NotNull 
  @Schema(name = "created_at", description = "The Unix timestamp (in seconds) for when the assistant file was created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created_at")
  public Integer getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }

  public AssistantFileObject assistantId(String assistantId) {
    this.assistantId = assistantId;
    return this;
  }

  /**
   * The assistant ID that the file is attached to.
   * @return assistantId
  */
  @NotNull 
  @Schema(name = "assistant_id", description = "The assistant ID that the file is attached to.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assistant_id")
  public String getAssistantId() {
    return assistantId;
  }

  public void setAssistantId(String assistantId) {
    this.assistantId = assistantId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssistantFileObject assistantFileObject = (AssistantFileObject) o;
    return Objects.equals(this.id, assistantFileObject.id) &&
        Objects.equals(this._object, assistantFileObject._object) &&
        Objects.equals(this.createdAt, assistantFileObject.createdAt) &&
        Objects.equals(this.assistantId, assistantFileObject.assistantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, _object, createdAt, assistantId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssistantFileObject {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    assistantId: ").append(toIndentedString(assistantId)).append("\n");
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
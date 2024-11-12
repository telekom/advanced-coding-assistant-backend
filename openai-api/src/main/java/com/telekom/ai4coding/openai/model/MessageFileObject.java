package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * A list of files attached to a &#x60;message&#x60;.
 */

@Schema(name = "MessageFileObject", description = "A list of files attached to a `message`.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MessageFileObject {

  private String id;

  /**
   * The object type, which is always `thread.message.file`.
   */
  public enum ObjectEnum {
    THREAD_MESSAGE_FILE("thread.message.file");

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

  private String messageId;

  public MessageFileObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageFileObject(String id, ObjectEnum _object, Integer createdAt, String messageId) {
    this.id = id;
    this._object = _object;
    this.createdAt = createdAt;
    this.messageId = messageId;
  }

  public MessageFileObject id(String id) {
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

  public MessageFileObject _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always `thread.message.file`.
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always `thread.message.file`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  public MessageFileObject createdAt(Integer createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the message file was created.
   * @return createdAt
  */
  @NotNull 
  @Schema(name = "created_at", description = "The Unix timestamp (in seconds) for when the message file was created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created_at")
  public Integer getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }

  public MessageFileObject messageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  /**
   * The ID of the [message](/docs/api-reference/messages) that the [File](/docs/api-reference/files) is attached to.
   * @return messageId
  */
  @NotNull 
  @Schema(name = "message_id", description = "The ID of the [message](/docs/api-reference/messages) that the [File](/docs/api-reference/files) is attached to.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message_id")
  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageFileObject messageFileObject = (MessageFileObject) o;
    return Objects.equals(this.id, messageFileObject.id) &&
        Objects.equals(this._object, messageFileObject._object) &&
        Objects.equals(this.createdAt, messageFileObject.createdAt) &&
        Objects.equals(this.messageId, messageFileObject.messageId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, _object, createdAt, messageId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageFileObject {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
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
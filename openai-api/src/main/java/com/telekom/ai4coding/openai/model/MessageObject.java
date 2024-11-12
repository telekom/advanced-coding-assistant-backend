package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Represents a message within a [thread](/docs/api-reference/threads).
 */

@Schema(name = "MessageObject", description = "Represents a message within a [thread](/docs/api-reference/threads).")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MessageObject {

  private String id;

  /**
   * The object type, which is always `thread.message`.
   */
  public enum ObjectEnum {
    THREAD_MESSAGE("thread.message");

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

  private String threadId;

  /**
   * The entity that produced the message. One of `user` or `assistant`.
   */
  public enum RoleEnum {
    USER("user"),
    
    ASSISTANT("assistant");

    private String value;

    RoleEnum(String value) {
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
    public static RoleEnum fromValue(String value) {
      for (RoleEnum b : RoleEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private RoleEnum role;

  @Valid
  private List<@Valid MessageObjectContentInner> content = new ArrayList<>();

  private JsonNullable<String> assistantId = JsonNullable.<String>undefined();

  private JsonNullable<String> runId = JsonNullable.<String>undefined();

  @Valid
  private List<String> fileIds = new ArrayList<>();

  private JsonNullable<Object> metadata = JsonNullable.<Object>undefined();

  public MessageObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageObject(String id, ObjectEnum _object, Integer createdAt, String threadId, RoleEnum role, List<@Valid MessageObjectContentInner> content, String assistantId, String runId, List<String> fileIds, Object metadata) {
    this.id = id;
    this._object = _object;
    this.createdAt = createdAt;
    this.threadId = threadId;
    this.role = role;
    this.content = content;
    this.assistantId = JsonNullable.of(assistantId);
    this.runId = JsonNullable.of(runId);
    this.fileIds = fileIds;
    this.metadata = JsonNullable.of(metadata);
  }

  public MessageObject id(String id) {
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

  public MessageObject _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always `thread.message`.
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always `thread.message`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  public MessageObject createdAt(Integer createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the message was created.
   * @return createdAt
  */
  @NotNull 
  @Schema(name = "created_at", description = "The Unix timestamp (in seconds) for when the message was created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created_at")
  public Integer getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }

  public MessageObject threadId(String threadId) {
    this.threadId = threadId;
    return this;
  }

  /**
   * The [thread](/docs/api-reference/threads) ID that this message belongs to.
   * @return threadId
  */
  @NotNull 
  @Schema(name = "thread_id", description = "The [thread](/docs/api-reference/threads) ID that this message belongs to.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("thread_id")
  public String getThreadId() {
    return threadId;
  }

  public void setThreadId(String threadId) {
    this.threadId = threadId;
  }

  public MessageObject role(RoleEnum role) {
    this.role = role;
    return this;
  }

  /**
   * The entity that produced the message. One of `user` or `assistant`.
   * @return role
  */
  @NotNull 
  @Schema(name = "role", description = "The entity that produced the message. One of `user` or `assistant`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("role")
  public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public MessageObject content(List<@Valid MessageObjectContentInner> content) {
    this.content = content;
    return this;
  }

  public MessageObject addContentItem(MessageObjectContentInner contentItem) {
    if (this.content == null) {
      this.content = new ArrayList<>();
    }
    this.content.add(contentItem);
    return this;
  }

  /**
   * The content of the message in array of text and/or images.
   * @return content
  */
  @NotNull @Valid 
  @Schema(name = "content", description = "The content of the message in array of text and/or images.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("content")
  public List<@Valid MessageObjectContentInner> getContent() {
    return content;
  }

  public void setContent(List<@Valid MessageObjectContentInner> content) {
    this.content = content;
  }

  public MessageObject assistantId(String assistantId) {
    this.assistantId = JsonNullable.of(assistantId);
    return this;
  }

  /**
   * If applicable, the ID of the [assistant](/docs/api-reference/assistants) that authored this message.
   * @return assistantId
  */
  @NotNull 
  @Schema(name = "assistant_id", description = "If applicable, the ID of the [assistant](/docs/api-reference/assistants) that authored this message.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assistant_id")
  public JsonNullable<String> getAssistantId() {
    return assistantId;
  }

  public void setAssistantId(JsonNullable<String> assistantId) {
    this.assistantId = assistantId;
  }

  public MessageObject runId(String runId) {
    this.runId = JsonNullable.of(runId);
    return this;
  }

  /**
   * If applicable, the ID of the [run](/docs/api-reference/runs) associated with the authoring of this message.
   * @return runId
  */
  @NotNull 
  @Schema(name = "run_id", description = "If applicable, the ID of the [run](/docs/api-reference/runs) associated with the authoring of this message.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("run_id")
  public JsonNullable<String> getRunId() {
    return runId;
  }

  public void setRunId(JsonNullable<String> runId) {
    this.runId = runId;
  }

  public MessageObject fileIds(List<String> fileIds) {
    this.fileIds = fileIds;
    return this;
  }

  public MessageObject addFileIdsItem(String fileIdsItem) {
    if (this.fileIds == null) {
      this.fileIds = new ArrayList<>();
    }
    this.fileIds.add(fileIdsItem);
    return this;
  }

  /**
   * A list of [file](/docs/api-reference/files) IDs that the assistant should use. Useful for tools like retrieval and code_interpreter that can access files. A maximum of 10 files can be attached to a message.
   * @return fileIds
  */
  @NotNull @Size(max = 10) 
  @Schema(name = "file_ids", description = "A list of [file](/docs/api-reference/files) IDs that the assistant should use. Useful for tools like retrieval and code_interpreter that can access files. A maximum of 10 files can be attached to a message.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("file_ids")
  public List<String> getFileIds() {
    return fileIds;
  }

  public void setFileIds(List<String> fileIds) {
    this.fileIds = fileIds;
  }

  public MessageObject metadata(Object metadata) {
    this.metadata = JsonNullable.of(metadata);
    return this;
  }

  /**
   * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. 
   * @return metadata
  */
  @NotNull 
  @Schema(name = "metadata", description = "Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("metadata")
  public JsonNullable<Object> getMetadata() {
    return metadata;
  }

  public void setMetadata(JsonNullable<Object> metadata) {
    this.metadata = metadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageObject messageObject = (MessageObject) o;
    return Objects.equals(this.id, messageObject.id) &&
        Objects.equals(this._object, messageObject._object) &&
        Objects.equals(this.createdAt, messageObject.createdAt) &&
        Objects.equals(this.threadId, messageObject.threadId) &&
        Objects.equals(this.role, messageObject.role) &&
        Objects.equals(this.content, messageObject.content) &&
        Objects.equals(this.assistantId, messageObject.assistantId) &&
        Objects.equals(this.runId, messageObject.runId) &&
        Objects.equals(this.fileIds, messageObject.fileIds) &&
        Objects.equals(this.metadata, messageObject.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, _object, createdAt, threadId, role, content, assistantId, runId, fileIds, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageObject {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    threadId: ").append(toIndentedString(threadId)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    assistantId: ").append(toIndentedString(assistantId)).append("\n");
    sb.append("    runId: ").append(toIndentedString(runId)).append("\n");
    sb.append("    fileIds: ").append(toIndentedString(fileIds)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
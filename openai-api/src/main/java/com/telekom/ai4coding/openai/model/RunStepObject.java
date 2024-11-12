package com.telekom.ai4coding.openai.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * Represents a step in execution of a run. 
 */

@Schema(name = "RunStepObject", description = "Represents a step in execution of a run. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RunStepObject {

  private String id;

  /**
   * The object type, which is always `thread.run.step`.
   */
  public enum ObjectEnum {
    THREAD_RUN_STEP("thread.run.step");

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

  private String threadId;

  private String runId;

  /**
   * The type of run step, which can be either `message_creation` or `tool_calls`.
   */
  public enum TypeEnum {
    MESSAGE_CREATION("message_creation"),
    
    TOOL_CALLS("tool_calls");

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

  /**
   * The status of the run step, which can be either `in_progress`, `cancelled`, `failed`, `completed`, or `expired`.
   */
  public enum StatusEnum {
    IN_PROGRESS("in_progress"),
    
    CANCELLED("cancelled"),
    
    FAILED("failed"),
    
    COMPLETED("completed"),
    
    EXPIRED("expired");

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

  private StatusEnum status;

  private RunStepObjectStepDetails stepDetails;

  private JsonNullable<RunStepObjectLastError> lastError = JsonNullable.<RunStepObjectLastError>undefined();

  private JsonNullable<Integer> expiredAt = JsonNullable.<Integer>undefined();

  private JsonNullable<Integer> cancelledAt = JsonNullable.<Integer>undefined();

  private JsonNullable<Integer> failedAt = JsonNullable.<Integer>undefined();

  private JsonNullable<Integer> completedAt = JsonNullable.<Integer>undefined();

  private JsonNullable<Object> metadata = JsonNullable.<Object>undefined();

  private JsonNullable<RunStepCompletionUsage> usage = JsonNullable.<RunStepCompletionUsage>undefined();

  public RunStepObject() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RunStepObject(String id, ObjectEnum _object, Integer createdAt, String assistantId, String threadId, String runId, TypeEnum type, StatusEnum status, RunStepObjectStepDetails stepDetails, RunStepObjectLastError lastError, Integer expiredAt, Integer cancelledAt, Integer failedAt, Integer completedAt, Object metadata, RunStepCompletionUsage usage) {
    this.id = id;
    this._object = _object;
    this.createdAt = createdAt;
    this.assistantId = assistantId;
    this.threadId = threadId;
    this.runId = runId;
    this.type = type;
    this.status = status;
    this.stepDetails = stepDetails;
    this.lastError = JsonNullable.of(lastError);
    this.expiredAt = JsonNullable.of(expiredAt);
    this.cancelledAt = JsonNullable.of(cancelledAt);
    this.failedAt = JsonNullable.of(failedAt);
    this.completedAt = JsonNullable.of(completedAt);
    this.metadata = JsonNullable.of(metadata);
    this.usage = JsonNullable.of(usage);
  }

  public RunStepObject id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the run step, which can be referenced in API endpoints.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The identifier of the run step, which can be referenced in API endpoints.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RunStepObject _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always `thread.run.step`.
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always `thread.run.step`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  public RunStepObject createdAt(Integer createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the run step was created.
   * @return createdAt
  */
  @NotNull 
  @Schema(name = "created_at", description = "The Unix timestamp (in seconds) for when the run step was created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created_at")
  public Integer getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }

  public RunStepObject assistantId(String assistantId) {
    this.assistantId = assistantId;
    return this;
  }

  /**
   * The ID of the [assistant](/docs/api-reference/assistants) associated with the run step.
   * @return assistantId
  */
  @NotNull 
  @Schema(name = "assistant_id", description = "The ID of the [assistant](/docs/api-reference/assistants) associated with the run step.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assistant_id")
  public String getAssistantId() {
    return assistantId;
  }

  public void setAssistantId(String assistantId) {
    this.assistantId = assistantId;
  }

  public RunStepObject threadId(String threadId) {
    this.threadId = threadId;
    return this;
  }

  /**
   * The ID of the [thread](/docs/api-reference/threads) that was run.
   * @return threadId
  */
  @NotNull 
  @Schema(name = "thread_id", description = "The ID of the [thread](/docs/api-reference/threads) that was run.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("thread_id")
  public String getThreadId() {
    return threadId;
  }

  public void setThreadId(String threadId) {
    this.threadId = threadId;
  }

  public RunStepObject runId(String runId) {
    this.runId = runId;
    return this;
  }

  /**
   * The ID of the [run](/docs/api-reference/runs) that this run step is a part of.
   * @return runId
  */
  @NotNull 
  @Schema(name = "run_id", description = "The ID of the [run](/docs/api-reference/runs) that this run step is a part of.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("run_id")
  public String getRunId() {
    return runId;
  }

  public void setRunId(String runId) {
    this.runId = runId;
  }

  public RunStepObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of run step, which can be either `message_creation` or `tool_calls`.
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "The type of run step, which can be either `message_creation` or `tool_calls`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunStepObject status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * The status of the run step, which can be either `in_progress`, `cancelled`, `failed`, `completed`, or `expired`.
   * @return status
  */
  @NotNull 
  @Schema(name = "status", description = "The status of the run step, which can be either `in_progress`, `cancelled`, `failed`, `completed`, or `expired`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public RunStepObject stepDetails(RunStepObjectStepDetails stepDetails) {
    this.stepDetails = stepDetails;
    return this;
  }

  /**
   * Get stepDetails
   * @return stepDetails
  */
  @NotNull @Valid 
  @Schema(name = "step_details", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("step_details")
  public RunStepObjectStepDetails getStepDetails() {
    return stepDetails;
  }

  public void setStepDetails(RunStepObjectStepDetails stepDetails) {
    this.stepDetails = stepDetails;
  }

  public RunStepObject lastError(RunStepObjectLastError lastError) {
    this.lastError = JsonNullable.of(lastError);
    return this;
  }

  /**
   * Get lastError
   * @return lastError
  */
  @NotNull @Valid 
  @Schema(name = "last_error", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("last_error")
  public JsonNullable<RunStepObjectLastError> getLastError() {
    return lastError;
  }

  public void setLastError(JsonNullable<RunStepObjectLastError> lastError) {
    this.lastError = lastError;
  }

  public RunStepObject expiredAt(Integer expiredAt) {
    this.expiredAt = JsonNullable.of(expiredAt);
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the run step expired. A step is considered expired if the parent run is expired.
   * @return expiredAt
  */
  @NotNull 
  @Schema(name = "expired_at", description = "The Unix timestamp (in seconds) for when the run step expired. A step is considered expired if the parent run is expired.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("expired_at")
  public JsonNullable<Integer> getExpiredAt() {
    return expiredAt;
  }

  public void setExpiredAt(JsonNullable<Integer> expiredAt) {
    this.expiredAt = expiredAt;
  }

  public RunStepObject cancelledAt(Integer cancelledAt) {
    this.cancelledAt = JsonNullable.of(cancelledAt);
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the run step was cancelled.
   * @return cancelledAt
  */
  @NotNull 
  @Schema(name = "cancelled_at", description = "The Unix timestamp (in seconds) for when the run step was cancelled.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cancelled_at")
  public JsonNullable<Integer> getCancelledAt() {
    return cancelledAt;
  }

  public void setCancelledAt(JsonNullable<Integer> cancelledAt) {
    this.cancelledAt = cancelledAt;
  }

  public RunStepObject failedAt(Integer failedAt) {
    this.failedAt = JsonNullable.of(failedAt);
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the run step failed.
   * @return failedAt
  */
  @NotNull 
  @Schema(name = "failed_at", description = "The Unix timestamp (in seconds) for when the run step failed.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("failed_at")
  public JsonNullable<Integer> getFailedAt() {
    return failedAt;
  }

  public void setFailedAt(JsonNullable<Integer> failedAt) {
    this.failedAt = failedAt;
  }

  public RunStepObject completedAt(Integer completedAt) {
    this.completedAt = JsonNullable.of(completedAt);
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the run step completed.
   * @return completedAt
  */
  @NotNull 
  @Schema(name = "completed_at", description = "The Unix timestamp (in seconds) for when the run step completed.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("completed_at")
  public JsonNullable<Integer> getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(JsonNullable<Integer> completedAt) {
    this.completedAt = completedAt;
  }

  public RunStepObject metadata(Object metadata) {
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

  public RunStepObject usage(RunStepCompletionUsage usage) {
    this.usage = JsonNullable.of(usage);
    return this;
  }

  /**
   * Get usage
   * @return usage
  */
  @NotNull @Valid 
  @Schema(name = "usage", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("usage")
  public JsonNullable<RunStepCompletionUsage> getUsage() {
    return usage;
  }

  public void setUsage(JsonNullable<RunStepCompletionUsage> usage) {
    this.usage = usage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepObject runStepObject = (RunStepObject) o;
    return Objects.equals(this.id, runStepObject.id) &&
        Objects.equals(this._object, runStepObject._object) &&
        Objects.equals(this.createdAt, runStepObject.createdAt) &&
        Objects.equals(this.assistantId, runStepObject.assistantId) &&
        Objects.equals(this.threadId, runStepObject.threadId) &&
        Objects.equals(this.runId, runStepObject.runId) &&
        Objects.equals(this.type, runStepObject.type) &&
        Objects.equals(this.status, runStepObject.status) &&
        Objects.equals(this.stepDetails, runStepObject.stepDetails) &&
        Objects.equals(this.lastError, runStepObject.lastError) &&
        Objects.equals(this.expiredAt, runStepObject.expiredAt) &&
        Objects.equals(this.cancelledAt, runStepObject.cancelledAt) &&
        Objects.equals(this.failedAt, runStepObject.failedAt) &&
        Objects.equals(this.completedAt, runStepObject.completedAt) &&
        Objects.equals(this.metadata, runStepObject.metadata) &&
        Objects.equals(this.usage, runStepObject.usage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, _object, createdAt, assistantId, threadId, runId, type, status, stepDetails, lastError, expiredAt, cancelledAt, failedAt, completedAt, metadata, usage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepObject {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    assistantId: ").append(toIndentedString(assistantId)).append("\n");
    sb.append("    threadId: ").append(toIndentedString(threadId)).append("\n");
    sb.append("    runId: ").append(toIndentedString(runId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    stepDetails: ").append(toIndentedString(stepDetails)).append("\n");
    sb.append("    lastError: ").append(toIndentedString(lastError)).append("\n");
    sb.append("    expiredAt: ").append(toIndentedString(expiredAt)).append("\n");
    sb.append("    cancelledAt: ").append(toIndentedString(cancelledAt)).append("\n");
    sb.append("    failedAt: ").append(toIndentedString(failedAt)).append("\n");
    sb.append("    completedAt: ").append(toIndentedString(completedAt)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
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
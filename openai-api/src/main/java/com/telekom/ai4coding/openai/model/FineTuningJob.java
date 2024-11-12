package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The &#x60;fine_tuning.job&#x60; object represents a fine-tuning job that has been created through the API. 
 */

@Schema(name = "FineTuningJob", description = "The `fine_tuning.job` object represents a fine-tuning job that has been created through the API. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class FineTuningJob {

  private String id;

  private Integer createdAt;

  private JsonNullable<FineTuningJobError> error = JsonNullable.<FineTuningJobError>undefined();

  private JsonNullable<String> fineTunedModel = JsonNullable.<String>undefined();

  private JsonNullable<Integer> finishedAt = JsonNullable.<Integer>undefined();

  private FineTuningJobHyperparameters hyperparameters;

  private String model;

  /**
   * The object type, which is always \"fine_tuning.job\".
   */
  public enum ObjectEnum {
    FINE_TUNING_JOB("fine_tuning.job");

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

  private String organizationId;

  @Valid
  private List<String> resultFiles = new ArrayList<>();

  /**
   * The current status of the fine-tuning job, which can be either `validating_files`, `queued`, `running`, `succeeded`, `failed`, or `cancelled`.
   */
  public enum StatusEnum {
    VALIDATING_FILES("validating_files"),
    
    QUEUED("queued"),
    
    RUNNING("running"),
    
    SUCCEEDED("succeeded"),
    
    FAILED("failed"),
    
    CANCELLED("cancelled");

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

  private JsonNullable<Integer> trainedTokens = JsonNullable.<Integer>undefined();

  private String trainingFile;

  private JsonNullable<String> validationFile = JsonNullable.<String>undefined();

  public FineTuningJob() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FineTuningJob(String id, Integer createdAt, FineTuningJobError error, String fineTunedModel, Integer finishedAt, FineTuningJobHyperparameters hyperparameters, String model, ObjectEnum _object, String organizationId, List<String> resultFiles, StatusEnum status, Integer trainedTokens, String trainingFile, String validationFile) {
    this.id = id;
    this.createdAt = createdAt;
    this.error = JsonNullable.of(error);
    this.fineTunedModel = JsonNullable.of(fineTunedModel);
    this.finishedAt = JsonNullable.of(finishedAt);
    this.hyperparameters = hyperparameters;
    this.model = model;
    this._object = _object;
    this.organizationId = organizationId;
    this.resultFiles = resultFiles;
    this.status = status;
    this.trainedTokens = JsonNullable.of(trainedTokens);
    this.trainingFile = trainingFile;
    this.validationFile = JsonNullable.of(validationFile);
  }

  public FineTuningJob id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The object identifier, which can be referenced in the API endpoints.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The object identifier, which can be referenced in the API endpoints.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public FineTuningJob createdAt(Integer createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the fine-tuning job was created.
   * @return createdAt
  */
  @NotNull 
  @Schema(name = "created_at", description = "The Unix timestamp (in seconds) for when the fine-tuning job was created.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created_at")
  public Integer getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }

  public FineTuningJob error(FineTuningJobError error) {
    this.error = JsonNullable.of(error);
    return this;
  }

  /**
   * Get error
   * @return error
  */
  @NotNull @Valid 
  @Schema(name = "error", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("error")
  public JsonNullable<FineTuningJobError> getError() {
    return error;
  }

  public void setError(JsonNullable<FineTuningJobError> error) {
    this.error = error;
  }

  public FineTuningJob fineTunedModel(String fineTunedModel) {
    this.fineTunedModel = JsonNullable.of(fineTunedModel);
    return this;
  }

  /**
   * The name of the fine-tuned model that is being created. The value will be null if the fine-tuning job is still running.
   * @return fineTunedModel
  */
  @NotNull 
  @Schema(name = "fine_tuned_model", description = "The name of the fine-tuned model that is being created. The value will be null if the fine-tuning job is still running.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("fine_tuned_model")
  public JsonNullable<String> getFineTunedModel() {
    return fineTunedModel;
  }

  public void setFineTunedModel(JsonNullable<String> fineTunedModel) {
    this.fineTunedModel = fineTunedModel;
  }

  public FineTuningJob finishedAt(Integer finishedAt) {
    this.finishedAt = JsonNullable.of(finishedAt);
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the fine-tuning job was finished. The value will be null if the fine-tuning job is still running.
   * @return finishedAt
  */
  @NotNull 
  @Schema(name = "finished_at", description = "The Unix timestamp (in seconds) for when the fine-tuning job was finished. The value will be null if the fine-tuning job is still running.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("finished_at")
  public JsonNullable<Integer> getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(JsonNullable<Integer> finishedAt) {
    this.finishedAt = finishedAt;
  }

  public FineTuningJob hyperparameters(FineTuningJobHyperparameters hyperparameters) {
    this.hyperparameters = hyperparameters;
    return this;
  }

  /**
   * Get hyperparameters
   * @return hyperparameters
  */
  @NotNull @Valid 
  @Schema(name = "hyperparameters", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hyperparameters")
  public FineTuningJobHyperparameters getHyperparameters() {
    return hyperparameters;
  }

  public void setHyperparameters(FineTuningJobHyperparameters hyperparameters) {
    this.hyperparameters = hyperparameters;
  }

  public FineTuningJob model(String model) {
    this.model = model;
    return this;
  }

  /**
   * The base model that is being fine-tuned.
   * @return model
  */
  @NotNull 
  @Schema(name = "model", description = "The base model that is being fine-tuned.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("model")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public FineTuningJob _object(ObjectEnum _object) {
    this._object = _object;
    return this;
  }

  /**
   * The object type, which is always \"fine_tuning.job\".
   * @return _object
  */
  @NotNull 
  @Schema(name = "object", description = "The object type, which is always \"fine_tuning.job\".", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("object")
  public ObjectEnum getObject() {
    return _object;
  }

  public void setObject(ObjectEnum _object) {
    this._object = _object;
  }

  public FineTuningJob organizationId(String organizationId) {
    this.organizationId = organizationId;
    return this;
  }

  /**
   * The organization that owns the fine-tuning job.
   * @return organizationId
  */
  @NotNull 
  @Schema(name = "organization_id", description = "The organization that owns the fine-tuning job.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("organization_id")
  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

  public FineTuningJob resultFiles(List<String> resultFiles) {
    this.resultFiles = resultFiles;
    return this;
  }

  public FineTuningJob addResultFilesItem(String resultFilesItem) {
    if (this.resultFiles == null) {
      this.resultFiles = new ArrayList<>();
    }
    this.resultFiles.add(resultFilesItem);
    return this;
  }

  /**
   * The compiled results file ID(s) for the fine-tuning job. You can retrieve the results with the [Files API](/docs/api-reference/files/retrieve-contents).
   * @return resultFiles
  */
  @NotNull 
  @Schema(name = "result_files", description = "The compiled results file ID(s) for the fine-tuning job. You can retrieve the results with the [Files API](/docs/api-reference/files/retrieve-contents).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("result_files")
  public List<String> getResultFiles() {
    return resultFiles;
  }

  public void setResultFiles(List<String> resultFiles) {
    this.resultFiles = resultFiles;
  }

  public FineTuningJob status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * The current status of the fine-tuning job, which can be either `validating_files`, `queued`, `running`, `succeeded`, `failed`, or `cancelled`.
   * @return status
  */
  @NotNull 
  @Schema(name = "status", description = "The current status of the fine-tuning job, which can be either `validating_files`, `queued`, `running`, `succeeded`, `failed`, or `cancelled`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public FineTuningJob trainedTokens(Integer trainedTokens) {
    this.trainedTokens = JsonNullable.of(trainedTokens);
    return this;
  }

  /**
   * The total number of billable tokens processed by this fine-tuning job. The value will be null if the fine-tuning job is still running.
   * @return trainedTokens
  */
  @NotNull 
  @Schema(name = "trained_tokens", description = "The total number of billable tokens processed by this fine-tuning job. The value will be null if the fine-tuning job is still running.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("trained_tokens")
  public JsonNullable<Integer> getTrainedTokens() {
    return trainedTokens;
  }

  public void setTrainedTokens(JsonNullable<Integer> trainedTokens) {
    this.trainedTokens = trainedTokens;
  }

  public FineTuningJob trainingFile(String trainingFile) {
    this.trainingFile = trainingFile;
    return this;
  }

  /**
   * The file ID used for training. You can retrieve the training data with the [Files API](/docs/api-reference/files/retrieve-contents).
   * @return trainingFile
  */
  @NotNull 
  @Schema(name = "training_file", description = "The file ID used for training. You can retrieve the training data with the [Files API](/docs/api-reference/files/retrieve-contents).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("training_file")
  public String getTrainingFile() {
    return trainingFile;
  }

  public void setTrainingFile(String trainingFile) {
    this.trainingFile = trainingFile;
  }

  public FineTuningJob validationFile(String validationFile) {
    this.validationFile = JsonNullable.of(validationFile);
    return this;
  }

  /**
   * The file ID used for validation. You can retrieve the validation results with the [Files API](/docs/api-reference/files/retrieve-contents).
   * @return validationFile
  */
  @NotNull 
  @Schema(name = "validation_file", description = "The file ID used for validation. You can retrieve the validation results with the [Files API](/docs/api-reference/files/retrieve-contents).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("validation_file")
  public JsonNullable<String> getValidationFile() {
    return validationFile;
  }

  public void setValidationFile(JsonNullable<String> validationFile) {
    this.validationFile = validationFile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FineTuningJob fineTuningJob = (FineTuningJob) o;
    return Objects.equals(this.id, fineTuningJob.id) &&
        Objects.equals(this.createdAt, fineTuningJob.createdAt) &&
        Objects.equals(this.error, fineTuningJob.error) &&
        Objects.equals(this.fineTunedModel, fineTuningJob.fineTunedModel) &&
        Objects.equals(this.finishedAt, fineTuningJob.finishedAt) &&
        Objects.equals(this.hyperparameters, fineTuningJob.hyperparameters) &&
        Objects.equals(this.model, fineTuningJob.model) &&
        Objects.equals(this._object, fineTuningJob._object) &&
        Objects.equals(this.organizationId, fineTuningJob.organizationId) &&
        Objects.equals(this.resultFiles, fineTuningJob.resultFiles) &&
        Objects.equals(this.status, fineTuningJob.status) &&
        Objects.equals(this.trainedTokens, fineTuningJob.trainedTokens) &&
        Objects.equals(this.trainingFile, fineTuningJob.trainingFile) &&
        Objects.equals(this.validationFile, fineTuningJob.validationFile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, error, fineTunedModel, finishedAt, hyperparameters, model, _object, organizationId, resultFiles, status, trainedTokens, trainingFile, validationFile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FineTuningJob {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    fineTunedModel: ").append(toIndentedString(fineTunedModel)).append("\n");
    sb.append("    finishedAt: ").append(toIndentedString(finishedAt)).append("\n");
    sb.append("    hyperparameters: ").append(toIndentedString(hyperparameters)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    _object: ").append(toIndentedString(_object)).append("\n");
    sb.append("    organizationId: ").append(toIndentedString(organizationId)).append("\n");
    sb.append("    resultFiles: ").append(toIndentedString(resultFiles)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    trainedTokens: ").append(toIndentedString(trainedTokens)).append("\n");
    sb.append("    trainingFile: ").append(toIndentedString(trainingFile)).append("\n");
    sb.append("    validationFile: ").append(toIndentedString(validationFile)).append("\n");
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
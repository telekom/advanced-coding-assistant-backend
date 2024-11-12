package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * CreateFineTuningJobRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateFineTuningJobRequest {

  private CreateFineTuningJobRequestModel model;

  private String trainingFile;

  private CreateFineTuningJobRequestHyperparameters hyperparameters;

  private JsonNullable<@Size(min = 1, max = 40) String> suffix = JsonNullable.<String>undefined();

  private JsonNullable<String> validationFile = JsonNullable.<String>undefined();

  public CreateFineTuningJobRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateFineTuningJobRequest(CreateFineTuningJobRequestModel model, String trainingFile) {
    this.model = model;
    this.trainingFile = trainingFile;
  }

  public CreateFineTuningJobRequest model(CreateFineTuningJobRequestModel model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  @NotNull @Valid 
  @Schema(name = "model", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("model")
  public CreateFineTuningJobRequestModel getModel() {
    return model;
  }

  public void setModel(CreateFineTuningJobRequestModel model) {
    this.model = model;
  }

  public CreateFineTuningJobRequest trainingFile(String trainingFile) {
    this.trainingFile = trainingFile;
    return this;
  }

  /**
   * The ID of an uploaded file that contains training data.  See [upload file](/docs/api-reference/files/upload) for how to upload a file.  Your dataset must be formatted as a JSONL file. Additionally, you must upload your file with the purpose `fine-tune`.  See the [fine-tuning guide](/docs/guides/fine-tuning) for more details. 
   * @return trainingFile
  */
  @NotNull 
  @Schema(name = "training_file", example = "file-abc123", description = "The ID of an uploaded file that contains training data.  See [upload file](/docs/api-reference/files/upload) for how to upload a file.  Your dataset must be formatted as a JSONL file. Additionally, you must upload your file with the purpose `fine-tune`.  See the [fine-tuning guide](/docs/guides/fine-tuning) for more details. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("training_file")
  public String getTrainingFile() {
    return trainingFile;
  }

  public void setTrainingFile(String trainingFile) {
    this.trainingFile = trainingFile;
  }

  public CreateFineTuningJobRequest hyperparameters(CreateFineTuningJobRequestHyperparameters hyperparameters) {
    this.hyperparameters = hyperparameters;
    return this;
  }

  /**
   * Get hyperparameters
   * @return hyperparameters
  */
  @Valid 
  @Schema(name = "hyperparameters", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hyperparameters")
  public CreateFineTuningJobRequestHyperparameters getHyperparameters() {
    return hyperparameters;
  }

  public void setHyperparameters(CreateFineTuningJobRequestHyperparameters hyperparameters) {
    this.hyperparameters = hyperparameters;
  }

  public CreateFineTuningJobRequest suffix(String suffix) {
    this.suffix = JsonNullable.of(suffix);
    return this;
  }

  /**
   * A string of up to 18 characters that will be added to your fine-tuned model name.  For example, a `suffix` of \"custom-model-name\" would produce a model name like `ft:gpt-3.5-turbo:openai:custom-model-name:7p4lURel`. 
   * @return suffix
  */
  @Size(min = 1, max = 40) 
  @Schema(name = "suffix", description = "A string of up to 18 characters that will be added to your fine-tuned model name.  For example, a `suffix` of \"custom-model-name\" would produce a model name like `ft:gpt-3.5-turbo:openai:custom-model-name:7p4lURel`. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("suffix")
  public JsonNullable<@Size(min = 1, max = 40) String> getSuffix() {
    return suffix;
  }

  public void setSuffix(JsonNullable<String> suffix) {
    this.suffix = suffix;
  }

  public CreateFineTuningJobRequest validationFile(String validationFile) {
    this.validationFile = JsonNullable.of(validationFile);
    return this;
  }

  /**
   * The ID of an uploaded file that contains validation data.  If you provide this file, the data is used to generate validation metrics periodically during fine-tuning. These metrics can be viewed in the fine-tuning results file. The same data should not be present in both train and validation files.  Your dataset must be formatted as a JSONL file. You must upload your file with the purpose `fine-tune`.  See the [fine-tuning guide](/docs/guides/fine-tuning) for more details. 
   * @return validationFile
  */
  
  @Schema(name = "validation_file", example = "file-abc123", description = "The ID of an uploaded file that contains validation data.  If you provide this file, the data is used to generate validation metrics periodically during fine-tuning. These metrics can be viewed in the fine-tuning results file. The same data should not be present in both train and validation files.  Your dataset must be formatted as a JSONL file. You must upload your file with the purpose `fine-tune`.  See the [fine-tuning guide](/docs/guides/fine-tuning) for more details. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    CreateFineTuningJobRequest createFineTuningJobRequest = (CreateFineTuningJobRequest) o;
    return Objects.equals(this.model, createFineTuningJobRequest.model) &&
        Objects.equals(this.trainingFile, createFineTuningJobRequest.trainingFile) &&
        Objects.equals(this.hyperparameters, createFineTuningJobRequest.hyperparameters) &&
        equalsNullable(this.suffix, createFineTuningJobRequest.suffix) &&
        equalsNullable(this.validationFile, createFineTuningJobRequest.validationFile);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, trainingFile, hyperparameters, hashCodeNullable(suffix), hashCodeNullable(validationFile));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateFineTuningJobRequest {\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    trainingFile: ").append(toIndentedString(trainingFile)).append("\n");
    sb.append("    hyperparameters: ").append(toIndentedString(hyperparameters)).append("\n");
    sb.append("    suffix: ").append(toIndentedString(suffix)).append("\n");
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
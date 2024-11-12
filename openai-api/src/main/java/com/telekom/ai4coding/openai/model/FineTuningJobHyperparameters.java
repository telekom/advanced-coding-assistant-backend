package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * The hyperparameters used for the fine-tuning job. See the [fine-tuning guide](/docs/guides/fine-tuning) for more details.
 */

@Schema(name = "FineTuningJob_hyperparameters", description = "The hyperparameters used for the fine-tuning job. See the [fine-tuning guide](/docs/guides/fine-tuning) for more details.")
@JsonTypeName("FineTuningJob_hyperparameters")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class FineTuningJobHyperparameters {

//  private FineTuningJobHyperparametersNEpochs nEpochs = auto;
  private FineTuningJobHyperparametersNEpochs nEpochs = null;

  public FineTuningJobHyperparameters() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FineTuningJobHyperparameters(FineTuningJobHyperparametersNEpochs nEpochs) {
    this.nEpochs = nEpochs;
  }

  public FineTuningJobHyperparameters nEpochs(FineTuningJobHyperparametersNEpochs nEpochs) {
    this.nEpochs = nEpochs;
    return this;
  }

  /**
   * Get nEpochs
   * @return nEpochs
  */
  @NotNull @Valid 
  @Schema(name = "n_epochs", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("n_epochs")
  public FineTuningJobHyperparametersNEpochs getnEpochs() {
    return nEpochs;
  }

  public void setnEpochs(FineTuningJobHyperparametersNEpochs nEpochs) {
    this.nEpochs = nEpochs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FineTuningJobHyperparameters fineTuningJobHyperparameters = (FineTuningJobHyperparameters) o;
    return Objects.equals(this.nEpochs, fineTuningJobHyperparameters.nEpochs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nEpochs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FineTuningJobHyperparameters {\n");
    sb.append("    nEpochs: ").append(toIndentedString(nEpochs)).append("\n");
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
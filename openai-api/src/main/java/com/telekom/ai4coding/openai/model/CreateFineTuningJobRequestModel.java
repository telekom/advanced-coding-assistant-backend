package com.telekom.ai4coding.openai.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * The name of the model to fine-tune. You can select one of the [supported models](/docs/guides/fine-tuning/what-models-can-be-fine-tuned). 
 */

@Schema(name = "CreateFineTuningJobRequest_model", description = "The name of the model to fine-tune. You can select one of the [supported models](/docs/guides/fine-tuning/what-models-can-be-fine-tuned). ")
@JsonTypeName("CreateFineTuningJobRequest_model")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateFineTuningJobRequestModel {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateFineTuningJobRequestModel {\n");
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